import java.util.Scanner;

/**
 * @author Seva Nardin
 */
public class HackRank {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("================================");
        for(int i=0;i<3;i++)
        {
            String s1=sc.next();
            int x=sc.nextInt();
            //Complete this line

            String s2 = null ;

            if( x == 100 ) {
                s2 = "           ";
            } else if( x == 65 ) {
                s2 = "            0";
            } else if( x == 50 ) {
                s2 = "         0";
            }

            System.out.println( s1 + s2 + x ) ;
        }
        System.out.println("================================");

    }


}

