package kunalTeaches.Recursion;
import java.util.*;

public class printNo {

	public static void printNum(int n) {
		//B.C.
		if(n == 0) return;
		//Induction
		printNum(n-1);
		//Hypothesis
		System.out.println(n);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		printNum(n);
		sc.close();		
	}
}