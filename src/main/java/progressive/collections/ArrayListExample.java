package progressive.collections;

import java.util.ArrayList;

/**
 * @author Seva Nardin
 */
public class ArrayListExample {

    public static void main(String[] args) {

        // Динамический массив ArrayList - его можно расширять
        // обычный массив этого не даст сделать - java.lang.ArrayIndexOutOfBoundsException
//        int[] x = new int[3];
//        for( int i = 0 ; i < 4; i++  ) { x[i] = 1; }

        // создаем объект класса ArrayList
        ArrayList<Integer> arrayList = new ArrayList<>();
        // добавим сто элементов в массив
        for( int i = 0; i < 100; i++ ) {
            arrayList.add(i);
        }
        // у всех классов ArrayList есть метод toString
        System.out.println(arrayList);
        System.out.println(arrayList.get(0));
        System.out.println(arrayList.get(99));
        System.out.println(arrayList.size());
        // Прохождение по элементам ArrayList
        for( int i = 0 ; i < arrayList.size() ; i++ ) {
            System.out.println( arrayList.get(i) );
        }
        // еще способ forEach
        for( Integer x : arrayList ) { System.out.println( x ); }

        // Внутри Arraylist есть обычный массив в случае его заполнения создается новый массив
        // и в этот массив копируются элементы старого массива таким образом достигается функ-ал динамического массива
        // методы add и get работают быстрее чем  у LinkedList

    }
}
