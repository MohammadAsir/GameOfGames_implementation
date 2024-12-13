import java.util.Random;

public class EvenOddGameTest {
    private static String playerInput;
    private static String computerInput;
    private static int playerScore = 0;
    private static int computerScore = 0;
    private static int rounds;

    public static int[] startGameTestMode(int totalPlayerScore, int totalComputerScore) {
        System.out.println("Test Mode - Welcome to the Even-Odd Game!");

        // Fixed number of rounds for test mode (e.g., 3 rounds)
        rounds = 3;
        System.out.println("Rounds: " + rounds);

        // Set player choice for testing
        playerInput = "odd";
        computerInput = "even";
        System.out.println("Player chooses: " + playerInput + ". Computer is: " + computerInput);

        for (int i = 1; i <= rounds; i++) {
            System.out.println("\nRound " + i);
            int playerFingers = playerFingersTest();
            int computerFingers = computerFingersTest();

            System.out.println("[Test Mode] Player revealed: " + playerFingers);
            System.out.println("[Test Mode] Computer revealed: " + computerFingers);

            int sum = playerFingers + computerFingers;
            boolean playerWon = isOdd(sum) == playerInput.equals("odd");

            System.out.println("The sum is " + sum + " (" + (isOdd(sum) ? "Odd" : "Even") + ").");
            if (playerWon) {
                System.out.println("Player wins this round!");
                playerScore++;
            } else {
                System.out.println("Computer wins this round!");
                computerScore++;
            }

            updateScores();
        }

        displayWinner();

        // Update total scores
        totalPlayerScore += playerScore;
        totalComputerScore += computerScore;

        resetGame();

        System.out.println("Test Mode: Game over.");
        return new int[]{totalPlayerScore, totalComputerScore};
    }

    private static int playerFingersTest() {
        // Fixed number for player fingers (e.g., always 3 for testing)
        return 3;
    }

    private static int computerFingersTest() {
        // Random number for computer fingers
        Random random = new Random();
        return random.nextInt(5) + 1;
    }

    private static boolean isOdd(int sum) {
        return sum % 2 != 0;
    }

    private static void updateScores() {
        System.out.println("Scoreboard:");
        System.out.println("Player: " + playerScore + " | Computer: " + computerScore);
    }

    private static void displayWinner() {
        System.out.println("Game Over!");
        if (playerScore > computerScore) {
            System.out.println("Player wins the game!");
        } else if (computerScore > playerScore) {
            System.out.println("Computer wins the game!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    private static void resetGame() {
        playerScore = 0;
        computerScore = 0;
    }

    public static void main(String[] args) {
        startGameTestMode(0, 0);
    }
}
