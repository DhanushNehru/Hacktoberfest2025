
public class calculator {
    //fuction with same name but different parameters
    public static int sum(int a, int b){
        return a+b;
    }
    public static int sum(int a, int b, int c){
        return a+b+c;
    }
    // fuctions with same name but different datatype
    public static float sum(float a, float b){
        return a+b;
    }
    public static void main(String[] args) {
        int sum1= sum(1,3);
        int sum2 =sum(1,2,3);
        float sum3 = sum(4.6f , 4.4f); 
        System.out.println("sum1:"+sum1);
        System.out.println("sum2:"+sum2);
        System.out.println("sum3:"+sum3);
        
    }
}
