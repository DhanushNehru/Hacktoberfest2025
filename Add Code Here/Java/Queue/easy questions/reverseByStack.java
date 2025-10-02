import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class reverseByStack {
    public static Queue<Integer> Reverse(Queue<Integer> qu) {
        Stack<Integer> st = new Stack<>();
        while (!qu.isEmpty()) {
            st.push(qu.remove());
        }
        while (!st.empty()) {
            qu.add(st.pop());
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
        qu = Reverse(qu);
        System.out.println("reversed form is : ");
        System.out.println(qu);
    }
}
