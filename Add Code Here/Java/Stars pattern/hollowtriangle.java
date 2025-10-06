public class hollowtriangle {
    public static void hollow(int n , int a){
        for(int i=1;i<=n;i++){
            for(int j=1;j<=a;j++){
                if( i==1 || i==n ||j==1|| j==a ){
                    System.out.print("*");
                }
                else{
                    System.out.print(" ");
                }
            } System.out.println("");
        }
    }
    public static void main(String[] args){
        hollow(5, 6);
        
    }
}