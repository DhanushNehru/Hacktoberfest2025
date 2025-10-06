package kunalTeaches.Recursion;

public class Factorial {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int ans=fact(5);
		System.out.println(ans);

	}
	static int fact(int n){
		if(n<=1) return n;
		
		return n*fact(n-1);
	}
}
