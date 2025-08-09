import java.util.Random;
import java.util.Scanner;

public class SnakeAndLadder {
    private static final int BOARD_SIZE = 100;
    private static final int[] board = new int[BOARD_SIZE + 1];
    private static final Random random = new Random();

    public static void main(String[] args) {
        // Setup snakes and ladders
        setupBoard();

        // Start the game
        playGame();
    }

    private static void setupBoard() {
        // Define snakes
        board[16] = 6;
        board[47] = 26;
        board[49] = 11;
        board[56] = 53;
        board[62] = 19;
        board[64] = 60;
        board[87] = 24;
        board[93] = 73;
        board[95] = 75;
        board[98] = 78;

        // Define ladders
        board[1] = 38;
        board[4] = 14;
        board[9] = 31;
        board[21] = 42;
        board[28] = 84;
        board[36] = 44;
        board[51] = 67;
        board[71] = 91;
        board[80] = 100;
    }

    private static void playGame() {
        Scanner scanner = new Scanner(System.in);
        int player1Position = 0;
        int player2Position = 0;
        int currentPlayer = 1;

        while (player1Position < BOARD_SIZE && player2Position < BOARD_SIZE) {
            System.out.println("Player " + currentPlayer + "'s turn.");
            System.out.print("Press Enter to roll the die...");
            scanner.nextLine();

            int diceRoll = rollDice();
            System.out.println("You rolled a " + diceRoll + ".");
            if (currentPlayer == 1) {
                player1Position = movePlayer(player1Position, diceRoll);
                System.out.println("Player 1 is now on square " + player1Position);
                currentPlayer = 2;
            } else {
                player2Position = movePlayer(player2Position, diceRoll);
                System.out.println("Player 2 is now on square " + player2Position);
                currentPlayer = 1;
            }
            System.out.println();
        }

        if (player1Position >= BOARD_SIZE) {
            System.out.println("Player 1 wins!");
        } else {
            System.out.println("Player 2 wins!");
        }

        scanner.close();
    }

    private static int rollDice() {
        return random.nextInt(6) + 1;
    }

    private static int movePlayer(int position, int diceRoll) {
        position += diceRoll;
        if (position > BOARD_SIZE) {
            position = BOARD_SIZE; // No overshooting
        }
        // Check for snakes or ladders
        if (board[position] != 0) {
            System.out.println("Moved from " + position + " to " + board[position]);
            position = board[position];
        }
        return position;
    }
}
