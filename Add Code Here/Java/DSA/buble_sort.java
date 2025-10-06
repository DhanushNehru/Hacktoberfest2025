
import java.util.Scanner;

public class buble_sort {

    public static void sort(int n[]){
        for(int turn=0; turn<n.length-1; turn++){
            for(int j=0;j<n.length-1-turn;j++){
                if(n[j]>n[j+1]){
                    int temp=n[j];
                    n[j]=n[j+1];
                    n[j+1]=temp;
                }

            }
        }
    }
    public static void printarr(int n[]){
        
        for(int i=0;i<n.length;i++){
            System.out.print(n[i]+" ");
        }System.out.println("");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count =5;
        int n[]={5,8,9,6,34,77,88};
// take input from the user
        // System.out.println("Enter your Array:");
        // int n[]= new int[count];
        // for (int i=0;i<count;i++){
        //     n[i]=sc.nextInt();
        // }
        // System.out.print(Arrays.toString(n));
        // System.out.println("");
        sort(n);
        printarr(n);


    }
}
