import java.util.Random;
import java.util.Scanner;

public class ReadThreadGame 
{
    private int total;
    private int position;
    private boolean player1Turn = true;
    private boolean gameActive = true;
    private int playerScore;
    private int computerScore;

    /**
     * This method plays the Red Thread game given initial player and computer scores.
     * It updates these scores based on the game's outcome and returns the new totals.
     *
     * @param initialPlayerScore the initial score of the player
     * @param initialComputerScore the initial score of the computer
     * @return an array of two integers: {updatedPlayerScore, updatedComputerScore}
     */
    public static int[] playRedThreadGame(int initialPlayerScore, int initialComputerScore)
    {
        ReadThreadGame game = new ReadThreadGame();
        game.setScores(initialPlayerScore, initialComputerScore);
        game.startGame();
        return new int[] { game.playerScore, game.computerScore };
    }

    public void setScores(int playerScore, int computerScore) {
        this.playerScore = playerScore;
        this.computerScore = computerScore;
    }

    public void startGame() 
    {
        initializeGame();
        Scanner scanner = new Scanner(System.in);

        while (gameActive) 
        {
            displayGameState();
            System.out.println((player1Turn ? "Player" : "Computer") + ", pick how many threads?");
            int threads = scanner.nextInt();

            while (threads > total / 2 || threads <= 0) 
            {
                System.out.println("Invalid number! You can only pick up to half of the remaining threads. Try again:");
                threads = scanner.nextInt();
            }

            drawThreads(threads);
            if (isRedThreadDrawn()) 
            {
                System.out.println((player1Turn ? "Player" : "Computer") + " drew the red thread!");
                handleGameEnd(true);
                break;
            }

            switchTurns();
        }

        scanner.close();
    }

    private void initializeGame() 
    {
        Random random = new Random();
        total = random.nextInt(40) + 11; 
        position = random.nextInt(total + 1); 
        System.out.println(total + " threads in the box.");
    }

    private void drawThreads(int threads) 
    {
        total -= threads;
        System.out.println(threads + " threads drawn. Remaining threads: " + total);
    }

    private boolean isRedThreadDrawn() 
    {
        return total < position;
    }

    private void switchTurns() 
    {
        player1Turn = !player1Turn;
    }

    private void displayGameState() 
    {
        System.out.println("Current threads left: " + total);
    }

    private void handleGameEnd(boolean redThreadDrawn) 
    {
        gameActive = false;
        Scanner scanner = new Scanner(System.in);
        if (redThreadDrawn) 
        {
            if (player1Turn) {
                playerScore++;
            } else {
                computerScore++;
            }
            System.out.println("Congratulations! " + (player1Turn ? "Player" : "Computer") + " wins!");
        }

        System.out.println("Would you like to play again? (yes/no)");
        String choice = scanner.nextLine().toLowerCase();
        if (choice.equals("yes")) 
        {
            resetGame();
            startGame();
        } 
        else 
        {
            System.out.println("Final Scores: ");
            System.out.println("Player: " + playerScore);
            System.out.println("Computer: " + computerScore);
            System.out.println("Thank you for playing!");
        }
    }

    private void resetGame() 
    {
        player1Turn = true;
        gameActive = true;
    }

    public static void main(String[] args) 
    {
        if (args.length < 2) {
            System.out.println("Please provide initial player and computer scores as command line arguments.");
            return;
        }

        int initialPlayerScore = Integer.parseInt(args[0]);
        int initialComputerScore = Integer.parseInt(args[1]);

        int[] updatedScores = playRedThreadGame(initialPlayerScore, initialComputerScore);
        // After the game ends, the updated scores have already been displayed.
        // If needed, they are also available in updatedScores.
    }
}
