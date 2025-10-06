public class TransposeOfMatrix {

    public static void Transpose(int[][] arr){
        int n=arr.length;
        int m=arr[0].length;
        int [][] Trans = new int[m][n];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                Trans[i][j]=arr[j][i];
            }
        }
        System.out.println(" Given Array:");
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println("");
        }
        System.out.println(" Transpose Array:");
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                System.out.print(Trans[i][j]+" ");
            }
            System.out.println("");
        }
    }
    public static void main(String[] args) {
        int[][] arr={ 
            {1,2,3},
            {4,5,2},
            {7,8,9}};
            Transpose(arr);
    }
}
