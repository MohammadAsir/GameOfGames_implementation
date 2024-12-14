import java.util.Scanner;
import java.util.Random;

public class EvenOddGame {

    // Fields to store player and computer choices, scores, and number of rounds
    private static String playerInput;
    private static String computerInput;
    private static int playerScore = 0;
    private static int computerScore = 0;
    private static int rounds;

    public static int[] startGame(int totalPlayerScore, int totalComputerScore) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Even-Odd Game!");

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
        scanner.nextLine();
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
            int playerFingers = playerFingers();
            int computerFingers = computerFingers();

            System.out.println("You revealed: " + playerFingers);
            System.out.println("Computer revealed: " + computerFingers);

            // Calculate the sum and determine the winner of the round
            int sum = playerFingers + computerFingers;
            boolean playerWon = isOdd(sum) == playerInput.equals("odd");

            System.out.println("The sum is " + sum + " (" + (isOdd(sum) ? "Odd" : "Even") + ").");
            if (playerWon) {
                System.out.println("You win this round!");
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
            return startGame(totalPlayerScore, totalComputerScore);
        } else {
            System.out.println("Thank you for playing! Goodbye.");
            return new int[]{totalPlayerScore, totalComputerScore}; // Return the updated total scores
        }
    }

    /**
     * Assigns choices for player and computer based on the player's input.
     *
     * @param playerInput Player's choice ("odd" or "even").
     */
    private static void choices(String playerInput) {
        EvenOddGame.playerInput = playerInput;
        computerInput = playerInput.equals("odd") ? "even" : "odd";
        System.out.println("You chose " + EvenOddGame.playerInput + ". Computer is " + computerInput + ".");
    }

    /**
     * Prompts the player to input a valid number of fingers (1-5).
     *
     * @return Player's chosen number of fingers.
     */
    private static int playerFingers() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number of fingers to reveal (1-5): ");
        int fingers = scanner.nextInt();
        while (fingers < 1 || fingers > 5) {
            System.out.println("Invalid input. Please enter a number between 1 and 5: ");
            fingers = scanner.nextInt();
        }
        return fingers;
    }

    /**
     * Generates a random number of fingers (1-5) for the computer.
     *
     * @return Computer's randomly chosen number of fingers.
     */
    private static int computerFingers() {
        Random random = new Random();
        return random.nextInt(5) + 1;
    }

    /**
     * Determines if a given number is odd.
     *
     * @param sum The number to evaluate.
     * @return True if the number is odd, false otherwise.
     */
    private static boolean isOdd(int sum) {
        return sum % 2 != 0;
    }

    /**
     * Displays the current scores for both player and computer.
     */
    private static void updateScores() {
        System.out.println("Scoreboard:");
        System.out.println("You: " + playerScore + " | Computer: " + computerScore);
    }

    /**
     * Announces the overall winner based on scores.
     */
    private static void displayWinner() {
        System.out.println("Game Over!");
        if (playerScore > computerScore) {
            System.out.println("Congratulations! You are the winner!");
        } else if (computerScore > playerScore) {
            System.out.println("Computer wins the game!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    /**
     * Resets the scores for the next game session.
     */
    private static void resetGame() {
        playerScore = 0;
        computerScore = 0;
    }

    /**
     * Main method to initiate the game.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        startGame(0, 0);
    }
}
