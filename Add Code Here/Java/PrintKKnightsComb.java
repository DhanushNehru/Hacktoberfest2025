public class PrintKKnightsComb {
    static boolean IsSafe(char[][] arr,int n,int r,int c){
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
static void PrintKnights(char[][] arr,int n,int r,int c){
    if(r>=n){
        for(int i = 0;i<n;i++){
            for(int j = 0;j<n;j++){
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
        return;
    }
    if(c>=n) {
        PrintKnights(arr, n, r + 1, 0);
        return;
    }
    PrintKnights(arr,n,r,c+1);//op1 if we don't want to place at current position..........
    if(IsSafe(arr,n,r,c)){//if safe........if want to place at current position.............
        arr[r][c] = 'K';//if safe then place knight.............
        PrintKnights(arr,n,r,c+1);
        arr[r][c] = '.';//backtracking........
    }
}
public static void main(String[] args) {
    int n = 3;
    char[][] arr = new char[n][n];
    for(int i = 0;i<n;i++){
        for(int j = 0;j<n;j++){
            arr[i][j] = '.';
        }
    }
    PrintKnights(arr,n,0,0);
}
}
