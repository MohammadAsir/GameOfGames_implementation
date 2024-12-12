import java.util.Random;
import java.util.Scanner;

public class ReadThreadGame 
{
    private int total;
    private int position;
    private boolean player1Turn = true;
    private boolean gameActive = true;

    public void startGame() 
    {
        initializeGame();
        Scanner scanner = new Scanner(System.in);

        while (gameActive) 
        {
            displayGameState();
            if (player1Turn) 
            {
                System.out.println("Player 1, pick threads?");
                int threads = scanner.nextInt();

                while (threads > total / 2 || threads <= 0) 
                {
                    System.out.println("Invalid number! You can only pick up to half of the remaining threads. Try again:");
                    threads = scanner.nextInt();
                }

                drawThreads(threads);
            } 
            else 
            {
                int threads = computerPick();
                System.out.println("Computer picks " + threads + " threads.");
                drawThreads(threads);
            }

            if (isRedThreadDrawn()) 
            {
                System.out.println((player1Turn ? "Player 1" : "Computer") + " drew the red thread!");
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

    private int computerPick() 
    {
        Random random = new Random();
        int maxThreads = Math.max(1, total / 2); 
        return random.nextInt(maxThreads)+1;
    }

    private void handleGameEnd(boolean redThreadDrawn) 
    {
        gameActive = false;
        if (redThreadDrawn) 
        {
            System.out.println("Congratulations! " + (player1Turn ? "Player 1" : "Player 2") + " wins!");
        }
        System.out.println("Would you like to play again? (yes/no)");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine().toLowerCase();
        if (choice.equals("yes")) 
        {
            resetGame();
            startGame();
        } else {
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
        ReadThreadGame game = new ReadThreadGame();
        game.startGame();
    }
}
