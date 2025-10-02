public class RatinmazPrintPath {
    public static void Ratmaz(int i, int j, int n, int m, String s) {// this question prints the total rights and downs
                                                                     // taken to reach the destination
        if (i > n || j > m) {
            return; // If out of bounds, return
        }
        if (i == n && j == m) {
            System.out.print(s + " ");
            return; // Reached destination, print the directions and return
        }
        Ratmaz(i, j + 1, n, m, s + 'R');// if going to right
        Ratmaz(i + 1, j, n, m, s + 'D');// if going to down
    }

    public static void Rat(int[][] mat) {
        Ratmaz(0, 0, mat.length - 1, mat[0].length - 1, "");// helper function
    }

    public static void main(String[] args) {
        int[][] arr = { { 1, 2 }, { 1, 2 }, { 1, 2 } };
        Rat(arr);
    }
}