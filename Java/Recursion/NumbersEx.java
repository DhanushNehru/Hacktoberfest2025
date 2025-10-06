package kunalTeaches.Recursion;
//print first n numbers using recursion

public class NumbersEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		print(1);

	}
	static void print(int n) {
		if(n==6) {
			return ;
		}
		System.out.println(n);
		//tail recursion
		//last function call
		print(n+1);
	}

}
