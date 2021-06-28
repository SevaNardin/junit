import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**
 * @author nardinvn
 * Код с реального проекта - тестирование бэкэнда
 * Тесты на проверку функционала актов
 */

public class Diff {
    private Subject subject ;
    private Subject subjectJP ;
    private Long agentContractJPId ;
    private static Operdoc operdoc ;
    private static AgentCommission ac00 ;
    private static JdbcFactory staff = TestBackEnd.getStaff() ;
    private static final String finCode = "YARSL";
    private static final Logger logger = LogManager.getLogger( ActIT.class ) ;
    private static Instant contractDate;
    private static Contract contract;
    private static String docNum;
    private static Subject signer ;
    private static final Long deptId = 3032L ;
    private final String intTest = MainContext.getParam( "integration.test" );    // вкл/выкл интеграционных тестов
    private String contractNumber ;
    private Long agentContractId ;
    private static DiffAdd diffAdd;

    @BeforeEach
    void init() {
        try {
            //создание субъекта
            subject = new PhysicalPerson( "Гурулькин", "Семён", "Васильевич", "1980-01-01", 0, "990011223344" );
            // дата договора - текущая минус 10 дней
            contractDate = Instant.now().minus( 10, ChronoUnit.DAYS ) ;
            // insurance contract number
            contractNumber = "001/407/" + subject.getSubjectId() ;
            // создание АД
            agentContractId = TestBackEnd.createAgentContract( subject.getSubjectId(), 100L, "ac-" + subject.getSubjectId(), 3032L );
            // подписант
            signer = new PhysicalPerson( "GENERATE", "Signer", "Signer", "1980-01-01", 1 );
            // создание субъекта ЮЛ
            subjectJP = new JuridicalPerson();
            // создание АД ЮЛ
            agentContractJPId = TestBackEnd.createAgentContract( subjectJP.getSubjectId(), 107L, "ac-" + subjectJP.getSubjectId(), deptId, signer.getSubjectId() );
            // номер договора
            docNum = "od-" + subject.getSubjectId() ;
            // дата контракта
            contractDate = Instant.now().minus( 10, ChronoUnit.DAYS ) ;
            // контракт
            contract = new Contract( contractNumber, "407", contractDate, agentContractId, 5L );
        } catch( SQLException e ) {
            Assertions.fail( e.getMessage() );
        }

    }

    @AfterEach
    void freeAct() {
        // удаление акта из буфера автообработки
        if( Objects.nonNull( operdoc ) ) {
            TestHelper.delOperdocFromBuffer( operdoc.getOperdocId() );
        } else if( Objects.nonNull( diffAdd ) ) {
            TestHelper.delOperdocFromBuffer( diffAdd.getDiffId() );
        }

        try {
            staff.testRollback();
        } catch( SQLException e ) {
            Assertions.fail( e.getMessage() ) ;
        }

    }

    @Tag( "gate-test" )
    @ParameterizedTest( name = "Test - {0}" )
    @ValueSource( strings = { "act", "prototype" } )
    @DisplayName( "Проверка смены статуса акта и прототипа" )
    void checkChangeActProtypeStatus( String docType ) {
        logger.debug( "checkChangeActProtypeStatus" );

        try {
            // если параметр не "on" тест прерывается
            Assumptions.assumeTrue( intTest.equals( "on" ), "Прерывание теста: integration.test != on" );
            // создание акта
            operdoc = new Operdoc( agentContractJPId, docNum, docType, 3 ) ;
            // добавляем строку Акта
            operdoc.addRow( BigDecimal.valueOf( 5000 ), contractNumber, BigDecimal.valueOf( 10 ), 1L, 0L, BigDecimal.valueOf( 500 ), null ) ;
            // добываем экземпляр
            ac00 = operdoc.getAgentCommission().get( 0 ) ;
            // график
            contract.addPremium( 1L, BigDecimal.valueOf( 5000 ) );
            // добавляем объект страхования
            Contract.addInsuranceObject( contract.getContractId(), contract.getVariantId(), BigDecimal.valueOf( 5000 ) );

            // создаем платеж
            Payment payment = new Payment( finCode, contractDate, BigDecimal.valueOf( 5000 )

                    , "Оплата (" + operdoc.getOperdocId() + ")"

                    , "Оплата договора " + contract.getContractNumber() ) ;

            // создаем разноску платежа
            Long payId = payment.paymentDistrib( contract.getPremium(0 ).getDocumentId(), BigDecimal.valueOf( 5000 ) ) ;
            // сцепка
            ac00.createLink( contract.getVariantId(), payId, contract.getPremium( 0 ).getPremiumId().getPremiumNumber(), contract.getContractId(), contract.getContractNumber() ) ;
            // делаем отражение КВ
            Operdoc.kvImage( operdoc.getOperdocId(), docType, "AUTO_DISTRIB_FICTPAY" );

            for( Long stateId : Arrays.asList( 4L, 5L, 6L ) ) {
                // смена статуса д-та
                ApiResourceTest.changeDocStatusAW( operdoc.getOperdocId(), stateId, docType );
                // проверка интеграции
                assertEquals( stateId.equals( 6L ) ? 7L : stateId, operdoc.getStatus( operdoc.getOperdocId() ), "Ошибка: д-т автоматически не перешел в Обработан!" );

                if( stateId.equals( 4L ) || stateId.equals( 5L ) ) {

                    assertEquals( stateId, operdoc.getStatus( operdoc.getOperdocId() ), "Ошибка: неверный статус д-та!" );

                }

            }

        } catch( SQLException | JsonProcessingException e ) {

            Assertions.fail( e.getMessage() );

        }

    }

    private static Stream<Arguments> changeServiceActType() {

        return Stream.of(
                Arguments.of( "act", "serv" ),
                Arguments.of( "act", "inspect" ),
                Arguments.of( "act", "check" ),
                Arguments.of( "prototype", "serv" ),
                Arguments.of( "prototype", "inspect" ),
                Arguments.of( "prototype", "check" )
        );
    }

    private static Stream<Arguments> changeDocTypeCardUrlscanUrl() {
        return Stream.of(
                Arguments.of( "act", "http://opentext.vesta.ru/OTCS/llisapi.dll/open/" + TestHelper.getRndNumber(), "http://opentext.vesta.ru/OTCS/llisapi.dll/open/" + TestHelper.getRndNumber() ),
                Arguments.of( "act", "http://opentext.vesta.ru/OTCS/llisapi.dll/open/" + TestHelper.getRndNumber(), null ),
                Arguments.of( "prototype", "http://opentext.vesta.ru/OTCS/llisapi.dll/open/" + TestHelper.getRndNumber(), "http://opentext.vesta.ru/OTCS/llisapi.dll/open/" + TestHelper.getRndNumber() ),
                Arguments.of( "prototype", "http://opentext.vesta.ru/OTCS/llisapi.dll/open/" + TestHelper.getRndNumber(), null )
        );
    }

    @Tag( "alfaworks-test" )
    @ParameterizedTest( name = "{index} => docType = {0} , cardUrl = {1}, scanUrl = {2}" )
    @MethodSource( "changeDocTypeCardUrlscanUrl" )
    @DisplayName( "FB-763: Сохранение РК и Скан для актов/прототипов в СЭД" )
    void saveRkScanForActPrototypeInSed( String docType, String cardUrl, String scanUrl ) {
        logger.debug( "saveRkScanForActPrototypeInSed" );
        try {
            // создание акта
            operdoc = new Operdoc( agentContractJPId, "od-" + subjectJP.getSubjectId(), docType, 3 ) ;
            // добавляем строку Акта
            operdoc.addRow( BigDecimal.valueOf( 1000 ), contractNumber, BigDecimal.valueOf( 10 ), 1L, 0L, BigDecimal.valueOf( 100 ), null ) ;
            // вызов эндпоинта
            ApiResourceTest.saveRkScanInForActPrototype( operdoc.getOperdocId(), cardUrl, scanUrl );

            String resultCardUrl, resultScanUrl;

            resultCardUrl = staff.query( " select del.link_url from document_external_link del where del.source_table_id = ? and del.link_type = ? "
                    , ps -> {
                        ps.setLong( 1, operdoc.getOperdocId() );
                        ps.setString( 2, "card" ) ;
                    }
                    , rs -> rs.getString( "link_url" )

            );

            resultScanUrl = staff.query( " select del.link_url from document_external_link del where del.source_table_id = ? and del.link_type = ? "
                    , ps -> {
                        ps.setLong( 1, operdoc.getOperdocId() );
                        ps.setString( 2, "scan" ) ;
                    }
                    , rs -> rs.getString( "link_url" )
            );

            Assertions.assertAll( "Контроль заполнения полей: ",
                    () -> assertEquals( cardUrl, resultCardUrl , "Ошибка поле card пустое!" ),
                    () -> assertEquals( scanUrl, resultScanUrl, "Ошибка поле scan пустое!" )

            );

        } catch( SQLException e ) {
            Assertions.fail( e.getMessage() );
        }

    }

}
