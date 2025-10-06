import java.util.ArrayList;
import java.util.List;

public class NQueensAndNKnights {

    public static void main(String[] args) {
        int n = 8; // Adjust the board size as needed

        // N-Queens
        List<List<Integer>> nQueensSolutions = solveNQueens(n);
        System.out.println("N-Queens solutions:");
        for (List<Integer> solution : nQueensSolutions) {
            printSolution(solution);
        }

        // N-Knights
        List<List<Integer>> nKnightsSolutions = solveNKnights(n);
        System.out.println("N-Knights solutions:");
        for (List<Integer> solution : nKnightsSolutions) {
            printSolution(solution);
        }
    }

    // N-Queens code (same as before)

    // N-Knights code (same as before)

    // ... (rest of the code for both N-Queens and N-Knights)
}
