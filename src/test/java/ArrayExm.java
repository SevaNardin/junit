import java.util.HashSet;
import java.util.Set;

/**
 * @author Seva Nardin
 */
public class ArrayExm {

    public static void main(String args[]) {
        // массив
        String[] values  = new String[] {"ноль", "один", "два", "три", "три", "два", "четыри" };

        Set set = new HashSet();

        for(int i=0; i< values.length; i++) {
            // упорядычиваем значения
            if(!set.add( values[ i ]) ) {
                // выводим одинаковые значения
                System.out.println( "Повторяющиеся значения: " + i );
            }
        }
    }

}
