import java.util.Scanner;
import java.util.Random;

public class CoinFlip_Test {

    /**
     * This method simulates a coin flip game in Test Mode.
     * It provides additional information (cheat mode) about the coin flip outcome.
     * It returns the updated player and computer scores after the series.
     *
     * @param initialPlayerScore Initial player score.
     * @param initialComputerScore Initial computer score.
     * @return Updated player and computer scores.
     */
    public static int[] playCoinFlipGameTestMode(int initialPlayerScore, int initialComputerScore) {
        // Parse total scores from command-line arguments
        int totalPlayerScore = initialPlayerScore;
        int totalComputerScore = initialComputerScore;

        // Initialize Scanner for user input and Random for coin flips
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        boolean keepPlaying = true; // Flag to control the game loop
        while (keepPlaying) {
            // Get the length of the series and calculate how many wins are needed
            int gameLength = getGameLength(scanner);
            int gamesToWin = calculateGamesToWin(gameLength);

            // Reset scores for the current series
            int playerWins = 0;
            int computerWins = 0;

            System.out.println("\nYou chose a best out of " + gameLength + " game!");

            // Play the series until one player reaches the required number of wins
            while (playerWins < gamesToWin && computerWins < gamesToWin) {
                System.out.println("\nFlipping the coin...");

                // Randomly determine the coin flip result ("H" for heads, "T" for tails)
                String coinSide = random.nextBoolean() ? "H" : "T";

                // Test Mode: Show the coin flip result (cheat mode)
                System.out.println("[Cheat Mode] Computer flipped: " + coinSide);

                // Prompt the user for their guess and validate it
                String playerGuess;
                while (true) {
                    System.out.print("Guess heads or tails (H/T): ");
                    playerGuess = scanner.next().trim().toUpperCase(); // Convert input to uppercase
                    if (playerGuess.equals("H") || playerGuess.equals("T")) {
                        break; // Valid input
                    }
                    System.out.println("Invalid input! Please enter 'H' for heads or 'T' for tails.");
                }

                // Check if the player's guess matches the coin flip
                if (playerGuess.equals(coinSide)) {
                    playerWins++; // Increment player wins if they guessed correctly
                    System.out.println("It is " + (coinSide.equals("H") ? "heads" : "tails") + ": You Won!");
                } else {
                    computerWins++; // Increment computer wins if the player guessed incorrectly
                    System.out.println("It is " + (coinSide.equals("H") ? "heads" : "tails") + ": You Lost!");
                }

                // Display the current series score
                System.out.println("The score is now Player: " + playerWins + " - Computer: " + computerWins);
            }

            // Determine and display the series winner
            if (playerWins > computerWins) {
                System.out.println("\nYou won the series " + playerWins + "-" + computerWins + "!");
                totalPlayerScore++; // Increment the total player score
            } else {
                System.out.println("\nYou lost the series " + computerWins + "-" + playerWins + "!");
                totalComputerScore++; // Increment the total computer score
            }

            // Display the updated total scores
            System.out.println("Updated total scores - Player: " + totalPlayerScore + " | Computer: " + totalComputerScore);

            // Ask the player if they want to play another series
            System.out.print("Would you like to play another series? (Y/N): ");
            String playAgain = scanner.next();
            if (playAgain.equalsIgnoreCase("N")) {
                System.out.println("Returning to main menu");
                keepPlaying = false; // Exit the game loop
            }
        }

        // Return the updated total scores
        return new int[] { totalPlayerScore, totalComputerScore };
    }

    /**
     * Prompts the user for the length of the series (1, 3, 5, 7) and returns a valid integer.
     */
    private static int getGameLength(Scanner scanner) {
        while (true) {
            // Prompt the user for the series length
            System.out.print("How long of a series would you like to play? 1, 3, 5, 7: ");
            if (scanner.hasNextInt()) { // Check if input is an integer
                int gameLength = scanner.nextInt();
                // Validate that the series length is one of the allowed values
                if (gameLength == 1 || gameLength == 3 || gameLength == 5 || gameLength == 7) {
                    System.out.println("You chose a best out of " + gameLength + " game!");
                    return gameLength;
                } else {
                    System.out.println("Invalid number: Try again");
                }
            } else {
                scanner.next(); // Clear invalid input
                System.out.println("Invalid number: Try again");
            }
        }
    }

    /**
     * Given a valid "best of" series length, calculates how many wins are needed to win the series.
     * For example, best of 3 requires 2 wins, best of 5 requires 3 wins, etc.
     */
    private static int calculateGamesToWin(int gameLength) {
        // Calculate the number of wins needed (half the series length rounded up)
        return (gameLength + 1) / 2;
    }
}
