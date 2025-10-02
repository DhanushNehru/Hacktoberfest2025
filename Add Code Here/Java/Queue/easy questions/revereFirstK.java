import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class revereFirstK {
    public static Queue<Integer> ReversefirstK(Queue<Integer> qu, int k) {
        Stack<Integer> st = new Stack<>();
        Stack<Integer> stt = new Stack<>();
        int i = 1;
        while (!qu.isEmpty()) {
            st.push(qu.remove());
        }
        int x = st.size();
        while (i <= x - k) {
            stt.push(st.pop());
            i++;
        }
        while (!st.empty()) {
            qu.add(st.pop());
        }
        while (!stt.empty()) {
            qu.add(stt.pop());
        }
        return qu;
    }

    public static void main(String[] args) {
        Queue<Integer> qu = new LinkedList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the 5 no. vmro ");
        for (int i = 0; i < 5; i++) {
            int x = sc.nextInt();
            qu.add(x);
        }
        System.out.println(qu);
        qu = ReversefirstK(qu, 3);
        System.out.println("Queue with reversed k element is : ");
        System.out.println(qu);
    }
}
