public class MAXSUM{

    public static void Max_sum_subarray(int[] numbers) {
        int current_sum=0;
        int max_sum=Integer.MIN_VALUE;

        for (int i = 0; i < numbers.length; i++) {
            int start = i;
            for (int j = i; j < numbers.length; j++) {
                int end = j;
                current_sum=0;
                for (int k = start; k <= end; k++) {
                    current_sum+=numbers[k];
                }
                if (max_sum<current_sum){
                    max_sum=current_sum;
                }
            }
            
        } System.out.println("Max sum:"+max_sum);
    }
    public static void main(String[]args){
        int a[]={12,41,85,44,56,35,2,4,1,5,8};
        int b[]={1,2,3};
        Max_sum_subarray(a);
        Max_sum_subarray(b);
    }
}
