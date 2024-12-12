import java.util.Random;
import java.util.Scanner;

public class TestFindTheThimble {

    public static int[] playGameTestMode(int playerScore, int computerScore) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.println("Test Mode - Welcome to Find the Thimble!");

        // Choose best out of value
        int rounds = getBestOutOfNumber(scanner);
        System.out.println("Best out of " + rounds + " rounds.");

        int userPoints = 0;
        int computerPoints = 0;

        // Repeat until best out of value reached
        for (int i = 0; i < rounds; i++) {
            System.out.println("Round " + (i + 1) + " of " + rounds);

            System.out.print("Choose a hand to hide the thimble (L or R): ");
            String userChoice = scanner.nextLine();

            while (!(userChoice.equals("L") || userChoice.equals("R"))) {
                System.out.print("Invalid input. Please choose 'L' for left or 'R' for right: ");
                userChoice = scanner.nextLine();
            }

            // Test Mode: Show where the thimble is hidden (helpful for debugging)
            System.out.println("Test Mode: The thimble is in hand: " + userChoice);

            // Computer guesses randomly
            String computerGuess = random.nextBoolean() ? "L" : "R";
            System.out.println("Computer guesses: " + computerGuess);

            // Test Mode: Show the computer's guess for testing purposes
            System.out.println("Test Mode: The computer guessed " + computerGuess);

            // Check if the computer guessed correctly
            if (userChoice.equals(computerGuess)) {
                computerPoints++;
                System.out.println("Computer wins this round!");
            } else {
                userPoints++;
                System.out.println("You win this round!");
            }

            System.out.println("Current Score - You: " + userPoints + " | Computer: " + computerPoints);
        }

        // Announce the winner
        System.out.println("\nGame Over!");
        if (userPoints > computerPoints) {
            System.out.println("You win!");
        } else if (computerPoints > userPoints) {
            System.out.println("Computer wins the game!");
        } else {
            System.out.println("The game is a tie!");
        }

        // Return the updated scores
        System.out.println("Final Scores - Player: " + userPoints + " | Computer: " + computerPoints);
        return new int[]{userPoints, computerPoints};
    }

    // Method to handle "best out of" number input
    private static int getBestOutOfNumber(Scanner scanner) {
        int rounds;
        while (true) {
            System.out.print("Enter a 'best out of' number (odd number): ");
            if (scanner.hasNextInt()) {
                rounds = scanner.nextInt();
                if (rounds % 2 != 0) {
                    scanner.nextLine(); // consume the remaining newline
                    return rounds;
                } else {
                    System.out.println("Please enter a valid odd number.");
                }
            } else {
                System.out.println("Invalid input. Please enter an odd number.");
                scanner.next(); // consume invalid input
            }
        }
    }

    public static void main(String[] args) {
        // Start the game in Test Mode
        playGameTestMode(0, 0); // Pass initial scores as 0
    }
}
