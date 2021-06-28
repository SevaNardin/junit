import com.codeborne.selenide.SelenideElement;
import com.google.api.client.util.store.DataStore;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.Assert.assertTrue;

/**
 * Код реального проекта - тестирование UI
 * Тестирование интерфейса модуля "Поиск актов"
 * Поиск док-тов: Акты, Прототипы, АР, СЗ
 * */

@OpenMainPage
@ExtendWith( DataStoreExtension.class )
public class ActIT {
    private Operdoc operdoc ;
    private Subject subject ;
    private Subject subjectJP;
    private Subject signer;
    private Long agentContractId ;
    private String docNum ;
    private String rootNumber ;
    private final Long deptId = 3032L ;
    private static final JdbcFactory staff = TestBackEnd.getStaff() ;
    private FactPayment fp ;
    private static final String finCode = "YARSL" ;
    private Long agentContractJPId ;
    private String acn ;
    private String agentContractNumber ;
    private String paymentsDate ;
    private Contract contract ;
    private String contractNumber ;

    SearchForActsPage sfaPage = new SearchForActsPage();
    ActRowCardPage arcPage = new ActRowCardPage();

    @BeforeEach
    void beforeProc( DataStore dataStore ) {
        DataStoreAct ds = (DataStoreAct) dataStore;
        // для нахождения пользователя в админке / полный доступ -1
        ds.setAccessType(1L);
        try {
            ds.addAccess();
            // создание субъекта
            subject = new PhysicalPerson( "Покрышкина", "Валентина", "Николаевна", "1980-01-01", 1 );
            // номер АД
            agentContractNumber = "acn-" + subject.getSubjectId() ;
            // создание АД
            agentContractId = TestBackEnd.createAgentContract( subject.getSubjectId(), 100L, agentContractNumber, deptId );
            // подписант
            signer = new PhysicalPerson( "GENERATE", "Signer", "Signer", "1980-01-01", 1 );
            // создание субъекта ЮЛ и Брокер
            subjectJP = new JuridicalPerson();
            // создание АД ЮЛ
            // номер АД
            acn = "ac-" + subject.getSubjectId();
            agentContractJPId = TestBackEnd.createAgentContract( subjectJP.getSubjectId(), 107L, acn, deptId, signer.getSubjectId() );
            // номер договора
            docNum = "od-" + subject.getSubjectId();
            // номер контракта
            rootNumber = "AAA/407/" + subject.getSubjectId();
            // дата контракта
            Instant contractDate = Instant.now().minus( 10, ChronoUnit.DAYS ) ;
            // insurance contract number
            contractNumber = "001/" + subject.getSubjectId() ;
            // контракт
            contract = new Contract( contractNumber, "407", contractDate, agentContractId, 5L );

        } catch ( SQLException e ) {
            Assertions.fail( e ) ;
        }
        // общие операции для всех тестов класса
        ds.addOperation(1223L);
        ds.grantAllOperations();
    }

    @AfterEach
    void afterProc( DataStore dataStore ) {
        DataStoreAct ds = (DataStoreAct) dataStore;
        ds.removeAccess();
        ds.revokeAllOperations();
    }

    @Nested
    @DisplayName("FB-470 - Доступ к вкладке 'Ошибки в Акте' для пользователей AW")
    class TestFB470 {

        @Test
        @ScriptParam(scriptType = "act", operations = {OP1421})
        @DisplayName("Вкладка 'Ошибки в Акте' доступна для пользователей AW с полным доуступом")
        void ErrorsInTheActTabAvailableForAWUsersFullAccess(DataStore dataStore) {
            DataStoreAct ds = (DataStoreAct) dataStore;
            Long operdocId = ds.getOperdocId();

            try {
                ds.setAccessType(1L);
                ds.addAccess();
                // ui - открываем Параметры поиска актов
                sfaPage.searchAndOpen(operdocId);
                $("#Tab").shouldBe(exist, Duration.ofSeconds(6));
                // поиск вкладки Ошибки в акте
                boolean tabExists = false;

                for (SelenideElement x : sfaPage.listTabs)
                    if (x.getText().contains("Ошибки в акте")) {
                        x.click();
                        tabExists = true;
                    }
                // проверка доступа к вкладке Ошибки
                assertTrue(tabExists);
            } catch (SQLException e) {
                Assertions.fail(e);
            } finally {
                ds.removeAccess();
            }
        }
    }
}
