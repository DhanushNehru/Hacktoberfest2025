import java.util.LinkedList;
import java.util.Queue;

public class PrintElem {
    public static void PrintVmro(Queue<Integer> qu) {
        Queue<Integer> nqu = new LinkedList<>();
        while (!qu.isEmpty()) {
            System.out.print(qu.peek() + " ");
            nqu.add(qu.remove());
        }
        while (!nqu.isEmpty()) {
            qu.add(nqu.remove());
        }
    }

    public static void main(String[] args) {
        Queue<Integer> qu = new LinkedList<>();
        qu.add(1);
        qu.add(2);
        qu.add(3);
        qu.add(4);
        qu.add(5);
        qu.add(6);
        PrintVmro(qu);
        System.out.println(qu);
    }
}