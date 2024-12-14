import java.util.Random;
import java.util.Scanner;

public class EvenOddGameTest {

    public static int[] startGameTestMode(int totalPlayerScore, int totalComputerScore) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.println("Test Mode - Welcome to the Even-Odd Game!");

        // Loop to get a valid number of rounds (must be odd)
        while (true) {
            System.out.println("How many rounds would you like to play? (must be an odd integer): ");
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Enter a valid integer.");
                scanner.next();
                continue;
            }

            int inputRounds = scanner.nextInt();
            if (inputRounds % 2 == 0) {
                System.out.println("Invalid input! Enter an odd number.");
            } else {
                rounds = inputRounds;
                break;
            }
        }

        // Get player's choice: odd or even
        System.out.println("Please choose: Odd or Even");
        scanner.nextLine(); // Consume newline
        String choice = scanner.nextLine().trim().toLowerCase();

        // Validate the player's choice
        while (!choice.equals("odd") && !choice.equals("even")) {
            System.out.println("Invalid choice. Choose 'Odd' or 'Even': ");
            choice = scanner.nextLine().trim().toLowerCase();
        }
        choices(choice);

        // Main game loop for the specified number of rounds
        for (int i = 1; i <= rounds; i++) {
            System.out.println("\nRound " + i);

            // Get player and computer's number of fingers
            int playerFingers = playerFingersTestMode(scanner);
            int computerFingers = computerFingersTestMode(random);

            System.out.println("Test Mode: Player revealed: " + playerFingers);
            System.out.println("Test Mode: Computer revealed: " + computerFingers);

            // Calculate the sum and determine the winner of the round
            int sum = playerFingers + computerFingers;
            boolean playerWon = isOdd(sum) == playerInput.equals("odd");

            System.out.println("Test Mode: The sum is " + sum + " (" + (isOdd(sum) ? "Odd" : "Even") + ").");
            if (playerWon) {
                System.out.println("Player wins this round!");
                playerScore++;
            } else {
                System.out.println("Computer wins this round!");
                computerScore++;
            }

            // Display updated scores
            updateScores();
        }

        // Announce the winner of the game
        displayWinner();

        // Update total scores across sessions
        totalPlayerScore += playerScore;
        totalComputerScore += computerScore;

        // Reset the game state for a new session
        resetGame();

        // Prompt user to replay the game
        System.out.println("Would you like to play again? (yes/no): ");
        String replay = scanner.nextLine().trim().toLowerCase();
        if (replay.equals("yes")) {
            return startGameTestMode(totalPlayerScore, totalComputerScore);
        } else {
            System.out.println("Test Mode: Thank you for playing! Goodbye.");
            return new int[]{totalPlayerScore, totalComputerScore}; // Return the updated total scores
        }
    }

    // Helper method for player fingers in test mode
    private static int playerFingersTestMode(Scanner scanner) {
        System.out.println("Enter a number of fingers to reveal (1-5): ");
        int fingers = scanner.nextInt();
        while (fingers < 1 || fingers > 5) {
            System.out.println("Invalid input. Please enter a number between 1 and 5: ");
            fingers = scanner.nextInt();
        }
        return fingers;
    }

    // Helper method for computer fingers in test mode
    private static int computerFingersTestMode(Random random) {
        int fingers = random.nextInt(5) + 1;
        System.out.println("Test Mode: Computer randomly reveals " + fingers);
        return fingers;
    }

    public static void main(String[] args) {
        startGameTestMode(0, 0);
    }
}
