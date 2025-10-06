package Java;

import java.util.Random;
import java.util.Scanner;
import java.nio.file.*;

public class Roulette {

    int[] roulette = new int[5];
    Scanner sc = new Scanner(System.in);
    Random rand = new Random();

    public Roulette() {
        for (int i = 0; i < 5; i++) {
            roulette[i] = rand.nextInt(5) + 1; // Numbers from 1 to 5
        }
    }

    public void play() {
        System.out.println("Guess a number between 1 and 5:");
        int guess = sc.nextInt();

        boolean won = false;
        for (int number : roulette) {
            if (number == guess) {
                won = true;
                break;
            }
        }

        if (won) {
            System.out.println("You won!");
        } else {
            System.out.println("You lost! Deleting the file...");
            try {
                Files.deleteIfExists(Paths.get("Roulette.java"));
                System.out.println("File deleted.");
            } catch (Exception e) {
                System.out.println("Failed to delete the file.");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Roulette game = new Roulette();
        game.play();
    }
}