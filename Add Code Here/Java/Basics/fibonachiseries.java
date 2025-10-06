//fibonachi series in java
public class fibonachiseries{
    public static void main(String[]args){
        //initialize 3 variables
        int n1,n2,n3,i,count=10;
        n1=0;
        n2=1;
        System.out.print(n1+" "+n2);
        //using for loop here the value of the i=2 because we already defined n1 and n2         
        for(i=2;i<=count;++i){    
                n3=n1+n2;    
                System.out.print(" "+n3);    
                n1=n2;    
                n2=n3;    
                }    
    }
}
