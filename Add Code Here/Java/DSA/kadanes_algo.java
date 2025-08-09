
public class kadanes_algo {
    public static void main(String[] args) {
    int n=4;
    int arr[]=new int[n];
    arr[0]=-1;
    arr[1]=5;
    arr[2]=6;
    arr[3]=4;
    int mx = Integer.MIN_VALUE;
    int sum =0;
    
    for(int i=0;i<n;i++){
        sum+=arr[i];
        if(sum>mx){
            mx=sum;
        }
        if(sum<0){
            sum=0;
        }
    }
    System.out.println("The max sum: "+sum);
}
}