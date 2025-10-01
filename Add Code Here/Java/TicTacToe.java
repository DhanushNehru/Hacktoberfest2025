import java.util.*;

public class TicTacToe {
    public static void setBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public static void printBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
                if (j != 2)
                    System.out.print('|');
            }
            System.out.println();
            if (i != 2)
                System.out.println("--------");
        }
    }

    public static boolean checkWinner(char[][] board, int x, int y, char mark) {
        for (int j = 0; j < board.length; j++) {
            if (board[x][j] != mark) {
                break;
            }
            if (j == board.length - 1)
                return true;
        }
        for (int j = 0; j < board.length; j++) {
            if (board[j][y] != mark) {
                break;
            }
            if (j == board.length - 1)
                return true;
        }
        if ((x == 0 && y == 0) || (x == 0 && y == 2) || (x == 2 && y == 0) || (x == 2 && y == 2)
                || (x == 1 && y == 1)) {
            for (int j = 0; j < board.length; j++) {
                if (board[j][j] != mark) {
                    break;
                }
                if (j == board.length - 1)
                    return true;
            }
            int j = 2;
            for (int i = 0; i < board.length; i++) {
                if (board[i][j] != mark) {
                    break;
                }
                j--;
                if (i == board.length - 1)
                    return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        char[][] board = new char[3][3];
        setBoard(board);
        boolean gameOver = false;
        int player = 1;
        char mark = 'X';
        int p1 = 0;
        int p2 = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("The game is started..........");
        printBoard(board);
        while (!gameOver) {
            if (p1 == 5 && p2 == 4) {
                System.out.println("Match tied!!!");
                break;
            }
            if (player == 1) {
                System.out.println("Player " + player + "'s Move : ");
            } else {
                System.out.println("Player " + player + "'s Move : ");
            }
            int x = sc.nextInt();
            int y = sc.nextInt();
            if (x < 0 || y < 0 || x > 2 || y > 2 || (board[x][y] == 'X' || board[x][y] == 'O')) {
                System.out.println("Invalid Move! Please do a right Move : ");
                continue;
            } else {
                if (player == 1) {
                    board[x][y] = 'X';
                    mark = 'X';
                    p1++;
                } else {
                    board[x][y] = 'O';
                    mark = 'O';
                    p2++;
                }
            }
            gameOver = checkWinner(board, x, y, mark);
            printBoard(board);
            if (gameOver) {
                System.out.println("Game is Over !!!");
                System.out.println("Player " + player + " is Winner.");
                break;
            }
            if (player == 1) {
                player = 2;
            } else {
                player = 1;
            }
        }
    }
}
