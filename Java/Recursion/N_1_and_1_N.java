package kunalTeaches.Recursion;

public class N_1_and_1_N {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		funBoth(5);

	}
	static void funBoth(int n) {
		if (n==0) {
			return ;
		}
		System.out.println(n);
		funBoth(n-1);
		System.out.println(n);
		
	}

}



