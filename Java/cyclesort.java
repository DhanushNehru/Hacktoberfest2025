import java.util.Scanner;

public class cyclesort
 {
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the number of elements");
        int n=sc.nextInt();
        int arr[]=new int[n];
        System.out.println("Enter elements in array:");
        for(int i=0;i<n;i++)
        {
            arr[i]=sc.nextInt();
        }

        int i=0;
        while(i<arr.length)
        {
            int correct=arr[i]-1;
            if(arr[i]!=arr[correct])
            {
                int temp=arr[i];
                arr[i]=arr[correct];
                arr[correct]=temp;
            }
            else{
                i++;
            }
        }

        for(int j=0;j<arr.length;j++)
        {
            System.out.print(arr[j]+" ");
        }
        System.out.println();

        sc.close();
    }
}
