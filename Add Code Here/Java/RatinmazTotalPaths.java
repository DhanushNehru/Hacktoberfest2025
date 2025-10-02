public class RatinmazTotalPaths {
    public static int Ratmaz(int i, int j, int n, int m) {// this question prints the total rights and downs
        // taken to reach the destination
        if (i > n || j > m) {
            return 0; // If out of bounds, return
        }
        if (i == n && j == m) {
            return 1; // Reached destination, print the directions and return
        }
        int right = Ratmaz(i, j + 1, n, m);// if going to right
        int down = Ratmaz(i + 1, j, n, m);// if going to down
        int total = right + down;
        return total;
    }

    public static int Rat(int[][] mat) {
        return Ratmaz(0, 0, mat.length - 1, mat[0].length - 1);// helper function
    }

    public static void main(String[] args) {
        int[][] arr = { { 1, 2, 3, 4 }, { 1, 2, 3, 4 }, { 1, 2, 3, 4 } };
        System.out.println(Rat(arr));
    }
}
