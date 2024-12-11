import java.util.Random;
import java.util.Scanner;

public class FindTheThimble {

    public static int[] playGame(int playerScore, int computerScore) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.println("Welcome to Find the Thimble!");

        // Chooses best out of value
        int rounds = getBestOutOfNumber(scanner);
        System.out.println("Best out of " + rounds + " rounds.");

        int userPoints = 0;
        int computerPoints = 0;

        // Repeat until best out of value is reached
        for (int i = 0; i < rounds; i++) {
            System.out.println("Round " + (i + 1) + " of " + rounds);

            System.out.print("Choose a hand to hide the thimble (L or R): ");
            String userChoice = scanner.nextLine();

            while (!(userChoice.equals("L") || userChoice.equals("R"))) {
                System.out.print("Invalid input. Please choose 'L' for left or 'R' for right: ");
                userChoice = scanner.nextLine();
            }

            // Computer guesses randomly
            String computerGuess = random.nextBoolean() ? "L" : "R";
            System.out.println("Computer guesses: " + computerGuess);

            // Check the computer's guess
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

        // Update the scores
        playerScore += userPoints;
        computerScore += computerPoints;

        // Ask if the user wants to play again or return to the menu
        String replayChoice;
        do {
            System.out.print("\nDo you want to play again? (yes/no): ");
            replayChoice = scanner.nextLine().toLowerCase();
            if (!(replayChoice.equals("yes") || replayChoice.equals("no"))) {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        } while (!(replayChoice.equals("yes") || replayChoice.equals("no")));

        // If the player chooses "yes", restart the game, otherwise return to Game of Games menu
        if (replayChoice.equals("yes")) {
            return playGame(playerScore, computerScore); // Recursively call playGame with updated scores
        } else {
            System.out.println("Returning to Game of Games menu...");
            return new int[] {playerScore, computerScore}; // Return to Game of Games menu
        }
    }

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
}
