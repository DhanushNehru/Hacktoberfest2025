package kunalTeaches.Recursion;

public class Sum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int ans = sum(5);
		System.out.println(ans);

	}
	static int sum(int n) {
		if(n<=1) {     			// try removing this condition and see the code wont work
			return 1;
		}
		
		return n + sum(n-1);
	}

}
