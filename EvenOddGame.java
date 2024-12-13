import java.util.Scanner;
import java.util.Random;

public class EvenOddGame 
{
    private String playerInput;
    private String computerInput;
    private int playerScore = 0;
    private int computerScore = 0;
    private int rounds;


    public void startGame() 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Even-Odd Game!");

        while (true) 
        {
            System.out.println("How many rounds would you like to play? (must be an odd integer): ");
            if (!scanner.hasNextInt()) 
            { 
                System.out.println("Invalid input! enter a valid integer.");
                scanner.next(); 
                continue; 
            }
    
            int inputRounds = scanner.nextInt();
            if (inputRounds % 2 == 0) 
            {
                System.out.println("Invalid input! enter an odd number.");
            } 
            else 
            {
                rounds = inputRounds; 
                break; 
            }
        }
    
        System.out.println("Please choose: Odd or Even");
        scanner.nextLine(); 
        String choice = scanner.nextLine().trim().toLowerCase();

        while (!choice.equals("odd") && !choice.equals("even")) 
        {
            System.out.println("Invalid choice. choose 'Odd' or 'Even': ");
            choice = scanner.nextLine().trim().toLowerCase();
        }
        choices(choice);

        for (int i = 1; i <= rounds; i++) 
        {
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
            } 
            else 
            {
                System.out.println("Computer wins this round!");
                computerScore++;
            }

            updateScores();
        }

        displayWinner();
        resetGame();
        System.out.println("Would you like to play again? (yes/no): ");
        String replay = scanner.nextLine().trim().toLowerCase();
        if (replay.equals("yes")) 
        {
            startGame();
        } else 
        {
            System.out.println("Thank you for playing! Goodbye.");
        }
        scanner.close();
    }

    private void choices(String playerInput) {
        this.playerInput = playerInput;
        this.computerInput = playerInput.equals("odd") ? "even" : "odd";
        System.out.println("You chose " + this.playerInput + ". Computer is " + this.computerInput + ".");
    }

    private int playerFingers() 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number of fingers to reveal (1-5): ");
        int fingers = scanner.nextInt();
        while (fingers < 1 || fingers > 5) 
        {
            System.out.println("Invalid input. Please enter a number between 1 and 5: ");
            fingers = scanner.nextInt();
        }
        return fingers;
    }

    private int computerFingers() 
    {
        Random random = new Random();
        return random.nextInt(5) + 1; 
    }

    private boolean isOdd(int sum) 
    {
        return sum % 2 != 0;
    }

    private void updateScores() 
    {
        System.out.println("Scoreboard:");
        System.out.println("You: " + playerScore + " | Computer: " + computerScore);
    }

    private void displayWinner() 
    {
        System.out.println("Game Over!");
        System.out.println(" ");
        if (playerScore > computerScore) 
        {
            System.out.println("Congratulations! You are the winner!");
        } 
        else if (computerScore > playerScore) 
        {
            System.out.println("Computer wins the game!");
        } 
    }

    private void resetGame() 
    {
        playerScore = 0;
        computerScore = 0;
    }

    public static void main(String[] args) 
    {
        EvenOddGame game = new EvenOddGame();
        game.startGame();
    }
}
