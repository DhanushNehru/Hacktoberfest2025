import java.util.ArrayList;

/**
 * The Maze class provides methods to find paths in a grid-based maze,
 * including diagonal movements, and counts of paths from the top-left
 * corner to the bottom-right corner of the grid.
 */
public class Maze {

    public static void main(String[] args) {
        // Example usage of the maze pathfinding methods
        int[][] route = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0},
        };

        System.out.println("Diagonal Paths: " + arrDiagonalPaths("", 0, 0, route, 1));
    }

    /**
     * Finds all paths from the top-left corner to the bottom-right corner
     * of the grid, allowing diagonal, vertical, and horizontal movements.
     *
     * @param path The current path as a string.
     * @param r The current row position.
     * @param c The current column position.
     * @param arr The grid representing the maze.
     * @param level The current level of the path.
     * @return A list of all possible paths.
     */
    public static ArrayList<String> arrDiagonalPaths(String path, int r, int c, int[][] arr, int level) {
        ArrayList<String> paths = new ArrayList<>();
        int mr = arr.length;
        int mc = arr[0].length;

        // Check for valid cell
        if (arr[r][c] != 0) return paths;

        // Base case: reached the bottom-right corner
        if (r == mr - 1 && c == mc - 1) {
            arr[r][c] = level;  // Mark the cell with the current level
            printMaze(arr);      // Print the current maze state
            arr[r][c] = 0;      // Reset the cell
            paths.add(path);
            return paths;
        }

        arr[r][c] = level;  // Mark the cell as visited

        // Move diagonally if within bounds
        if (r < mr - 1 && c < mc - 1) {
            paths.addAll(arrDiagonalPaths(path + 'd', r + 1, c + 1, arr, level + 1));
        }

        // Move down
        if (r < mr - 1) {
            paths.addAll(arrDiagonalPaths(path + 'D', r + 1, c, arr, level + 1));
        }

        // Move right
        if (c < mc - 1) {
            paths.addAll(arrDiagonalPaths(path + 'R', r, c + 1, arr, level + 1));
        }

        // Move up
        if (r > 0) {
            paths.addAll(arrDiagonalPaths(path + 'U', r - 1, c, arr, level + 1));
        }

        // Move left
        if (c > 0) {
            paths.addAll(arrDiagonalPaths(path + 'L', r, c - 1, arr, level + 1));
        }

        arr[r][c] = 0;  // Backtrack: unmark the cell

        return paths;
    }

    /**
     * Prints the current state of the maze.
     *
     * @param arr The maze grid.
     */
    private static void printMaze(int[][] arr) {
        for (int[] nums : arr) {
            for (int num : nums) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Other path-finding methods below

    public static ArrayList<String> diagonalPaths(String path, int r, int c, int mr, int mc) {
        ArrayList<String> paths = new ArrayList<>();
        if (r == mr - 1 && c == mc - 1) {
            paths.add(path);
            return paths;
        }

        // Move diagonally if within bounds
        if (r < mr - 1 && c < mc - 1) {
            paths.addAll(diagonalPaths(path + 'd', r + 1, c + 1, mr, mc));
        }

        // Move right if within bounds
        if (r < mr - 1) {
            paths.addAll(diagonalPaths(path + 'R', r + 1, c, mr, mc));
        }

        // Move down if within bounds
        if (c < mc - 1) {
            paths.addAll(diagonalPaths(path + 'D', r, c + 1, mr, mc));
        }

        return paths;
    }

    public static ArrayList<String> allPaths(String path, int r, int c) {
        ArrayList<String> paths = new ArrayList<>();
        if (r == 1 && c == 1) {
            paths.add(path);
            return paths;
        }

        // Explore all paths
        if (r > 1) paths.addAll(allPaths(path + 'R', r - 1, c));
        if (c > 1) paths.addAll(allPaths(path + 'D', r, c - 1));
        return paths;
    }

    public static void paths(String path, int r, int c) {
        if (r == 1 && c == 1) {
            System.out.println(path);
            return;
        }

        // Explore paths
        if (r > 1) paths(path + 'R', r - 1, c);
        if (c > 1) paths(path + 'D', r, c - 1);
    }

    public static int count(int r, int c, int count) {
        if (r == 1 && c == 1) {
            count++;
            return count;
        }
        int left = 0, right = 0;

        if (r > 1) {
            left = count(r - 1, c, count);
        }
        if (c > 1) {
            right = count(r, c - 1, count);
        }
        return left + right;
    }

    public static int countAlt(int r, int c, int count) {
        if (r == 1 || c == 1) {
            count++;
            return count;
        }
        int left = countAlt(r - 1, c, count);
        int right = countAlt(r, c - 1, count);

        return left + right;
    }
}
