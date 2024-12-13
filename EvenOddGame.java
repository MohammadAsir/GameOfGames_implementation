import java.util.Scanner;
import java.util.Random;

public class EvenOddGame {
    private static String playerInput;
    private static String computerInput;
    private static int playerScore = 0;
    private static int computerScore = 0;
    private static int rounds;

    public static int[] startGame(int totalPlayerScore, int totalComputerScore) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Even-Odd Game!");

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

        System.out.println("Please choose: Odd or Even");
        scanner.nextLine();
        String choice = scanner.nextLine().trim().toLowerCase();

        while (!choice.equals("odd") && !choice.equals("even")) {
            System.out.println("Invalid choice. Choose 'Odd' or 'Even': ");
            choice = scanner.nextLine().trim().toLowerCase();
        }
        choices(choice);

        for (int i = 1; i <= rounds; i++) {
            System.out.println("\nRound " + i);
            int playerFingers = playerFingers();
            int computerFingers = computerFingers();

            System.out.println("You revealed: " + playerFingers);
            System.out.println("Computer revealed: " + computerFingers);

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

            updateScores();
        }

        displayWinner();

        // Update total scores
        totalPlayerScore += playerScore;
        totalComputerScore += computerScore;

        resetGame();

        System.out.println("Would you like to play again? (yes/no): ");
        String replay = scanner.nextLine().trim().toLowerCase();
        if (replay.equals("yes")) {
            return startGame(totalPlayerScore, totalComputerScore);
        } else {
            System.out.println("Thank you for playing! Goodbye.");
            return new int[]{totalPlayerScore, totalComputerScore}; // Return the updated total scores
        }
    }

    private static void choices(String playerInput) {
        EvenOddGame.playerInput = playerInput;
        computerInput = playerInput.equals("odd") ? "even" : "odd";
        System.out.println("You chose " + EvenOddGame.playerInput + ". Computer is " + computerInput + ".");
    }

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

    private static int computerFingers() {
        Random random = new Random();
        return random.nextInt(5) + 1;
    }

    private static boolean isOdd(int sum) {
        return sum % 2 != 0;
    }

    private static void updateScores() {
        System.out.println("Scoreboard:");
        System.out.println("You: " + playerScore + " | Computer: " + computerScore);
    }

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

    private static void resetGame() {
        playerScore = 0;
        computerScore = 0;
    }

    public static void main(String[] args) {
        startGame(0, 0);
    }
}
