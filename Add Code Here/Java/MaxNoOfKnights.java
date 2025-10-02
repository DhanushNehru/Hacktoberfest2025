public class MaxNoOfKnights {
    public static boolean IsSafe(char[][] arr,int n,int r,int c){
        int i = r+2;
        int j = c+1;
        if(i<n&&j<n){
            if(arr[i][j]=='K')return false;
        }
        i = r+2;
        j = c-1;
        if(i<n&&j>=0){
            if(arr[i][j]=='K')return false;
        }
        i = r-2;
        j = c+1;
        if(i>=0&&j<n){
            if(arr[i][j]=='K')return false;
        }
        i = r-2;
        j = c-1;
        if(i>=0&&j>=0){
            if(arr[i][j]=='K')return false;
        }
        i = r-1;
        j = c+2;
        if(i>=0&&j<n){
            if(arr[i][j]=='K')return false;
        }
        i = r+1;
        j = c+2;
        if(i<n&&j<n){
            if(arr[i][j]=='K')return false;
        }
        i = r-1;
        j = c-2;
        if(i>=0&&j>=0){
            if(arr[i][j]=='K')return false;
        }
        i = r+1;
        j = c-2;
        if(i<n&&j>=0){
            if(arr[i][j]=='K')return false;
        }
        return true;
    }
    static int MaxNoKnights(char[][] arr,int n,int r,int c){
        if(r>=n){
            return 0;
        }
        if(c==n) {
            c = 0;
            return MaxNoKnights(arr, n, r + 1, c);
        }
        int count = 0;
            if(IsSafe(arr,n,r,c)){//if safe........
                arr[r][c] = 'K';//if safe then place knight..............
                count += 1;
                count += MaxNoKnights(arr,n,r,c+1);//check on another column
    ;            arr[r][c] = '.';//backtracking..........
            }
        return Math.max(count,MaxNoKnights(arr,n,r,c+1));//return the max from them.......
    }
    public static void main(String[] args) {
        int n = 3;
        char[][] arr = new char[n][n];
        for(int i = 0;i<n;i++){
            for(int j = 0;j<n;j++){
                arr[i][j] = '.';
            }
        }
        System.out.println(MaxNoKnights(arr,n,0,0));
    }
}
