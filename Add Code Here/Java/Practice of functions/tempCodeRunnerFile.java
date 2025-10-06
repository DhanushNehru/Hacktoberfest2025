public class primeinrange {

    public static void primerange(int n){
        for(int i=2;i<=n;i++){
            if(isPrime(i)) {
                System.out.print(i+"");
            }
        }System.out.println("");

    }
    public static void main(String [] args){
        primerange(50);

    }
}
