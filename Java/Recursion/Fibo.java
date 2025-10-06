package kunalTeaches.Recursion;

public class Fibo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println( fib(10));

	}
	static int fib(int n) {
		//base condition
		if(n==0) return 0;
		if(n==1) return 1;
		
		//recurence relation
		return fib(n-1) + fib(n-2);
	}

}
