package progressive.collections;

import java.util.Stack;

/**
 * @author Seva Nardin
 * Противоположность классу Queue
 */
public class StackExample {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        // все что добавили последним то и выводится
        stack.push( 3 );
        stack.push( 2 );
        stack.push( 1 );

        System.out.println( stack.peek() );
        // достает последний элемент
//        System.out.println(stack.pop());
        // пока стэк не пустой выводим на экран
        while (!stack.empty()) {
            System.out.println(stack.pop());
        }


    }
}
