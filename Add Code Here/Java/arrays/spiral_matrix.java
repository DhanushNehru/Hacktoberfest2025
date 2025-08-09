public class spiral_matrix {

    public static void spiral_code(int n[][]) {
        int startrow = 0;
        int startcol = 0;
        int endrow = n.length - 1;
        int endcol = n[0].length - 1;

        while (startrow <= endrow && startcol <= endcol) {
            // top
            for (int i = startcol; i <= endcol; i++) {
                System.out.print(n[startrow][i] + " ");
            }
            // right
            for (int j = startrow + 1; j <= endrow; j++) {
                System.out.print(n[j][endcol] + " ");
            }
            // bottom
            for (int j = endcol - 1; j >= startcol; j--) {
                if (startrow == endrow) {
                    break;
                }
                System.out.print(n[endrow][j] + " ");
            }
            // left
            for (int i = endrow - 1; i > startrow; i--) {
                if (startcol == endcol) {
                    break;
                }
                System.out.print(n[i][startcol] + " ");
            }

            startcol++;
            startrow++;
            endcol--;
            endrow--;
        }
    }

    public static void main(String[] args) {
        int n[][] = {
            {1, 3, 4},
            {4, 5, 6},
            {8, 9, 7},
            {1, 3, 4}
        };
        spiral_code(n);
    }
}
