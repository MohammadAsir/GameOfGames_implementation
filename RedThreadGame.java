import java.util.Random;
import java.util.Scanner;

public class RedThreadGame {
    private static int total;
    private static int position;
    private static boolean player1Turn = true;
    private static boolean gameActive = true;

    public static int[] playGame(int playerScore, int computerScore) {
        initializeGame();
        Scanner scanner = new Scanner(System.in);

        while (gameActive) {
            displayGameState();
            if (player1Turn) {
                System.out.println("Player 1, pick threads?");
                int threads = getValidInput(scanner);

                while (threads > total / 2 || threads <= 0) {
                    System.out.println("Invalid number! You can only pick up to half of the remaining threads. Try again:");
                    threads = getValidInput(scanner);
                }

                drawThreads(threads);
            } else {
                int threads = computerPick();
                System.out.println("Computer picks " + threads + " threads.");
                drawThreads(threads);
            }

            if (isRedThreadDrawn()) {
                System.out.println((player1Turn ? "Player 1" : "Computer") + " drew the red thread!");
                if (player1Turn) {
                    playerScore++;
                } else {
                    computerScore++;
                }
                handleGameEnd(true);
                break;
            }

            switchTurns();
        }
        return new int[]{playerScore, computerScore}; // Return updated scores
    }

    private static void initializeGame() {
        Random random = new Random();
        total = random.nextInt(40) + 11; // Total threads between 11 and 50
        position = random.nextInt(total + 1); // Position of the red thread
        System.out.println(total + " threads in the box.");
    }

    private static void drawThreads(int threads) {
        total -= threads;
        System.out.println(threads + " threads drawn. Remaining threads: " + total);
    }

    private static boolean isRedThreadDrawn() {
        return total < position;
    }

    private static void switchTurns() {
        player1Turn = !player1Turn;
    }

    private static void displayGameState() {
        System.out.println("Current threads left: " + total);
    }

    private static int computerPick() {
        Random random = new Random();
        int maxThreads = Math.max(1, total / 2); // Computer picks up to half the remaining threads
        return random.nextInt(maxThreads) + 1;
    }

    private static void handleGameEnd(boolean redThreadDrawn) {
        gameActive = false;
        if (redThreadDrawn) {
            System.out.println("Congratulations! " + (player1Turn ? "Player 1" : "Computer") + " wins!");
        }
        System.out.println("Game Over!");
    }

    private static int getValidInput(Scanner scanner) {
        int input = -1;
        while (true) {
            try {
                System.out.print("Enter a valid number: ");
                input = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid integer.");
                scanner.next(); // Clear the invalid input
            }
        }
        return input;
    }
}
