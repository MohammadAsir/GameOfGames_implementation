import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Get initial scores for the player and the computer
        System.out.println("Welcome to the Guess the Number Game!");

        int pScore = 0;
        int cScore = 0;

      //handle exceptions after entering score
        System.out.print("Enter your initial score: ");
        try {
            pScore = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Defaulting your score to 0.");
            pScore = 0;
            scanner.next(); 
        }
        
        //handle exceptions after entering score
        System.out.print("Enter computer's initial score: ");
        try {
            cScore = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Defaulting computer's score to 0.");
            cScore = 0;
            scanner.next(); 
        }

        boolean play = true;

        while (play) {

            System.out.println("\nCurrent Scores|| Player: " + pScore + " | Computer: " + cScore);
            System.out.println("Please specify a range for the number guessing!");
            System.out.print("Lower limit: ");
            int lowerLimit = scanner.nextInt();

            System.out.print("Upper limit: ");
            int upperLimit = scanner.nextInt();

            if (upperLimit <= lowerLimit) {
                System.out.println("Invalid range. Upper limit must be greater than the lower limit.");
                continue;
            }

            int numToGuess = random.nextInt(upperLimit - lowerLimit + 1) + lowerLimit;
            int maxAttempts = (upperLimit - lowerLimit + 1) / 2;
            System.out.println("You have " + maxAttempts + " attempts to guess the number!");

            int attempts = 0;
            boolean CorrectGuess = false;

            while (attempts < maxAttempts) {
                System.out.print("Guess a number between " + lowerLimit + " and " + upperLimit + ": ");
                int guess;

                try {
                    guess = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.next(); 
                    continue;
                }

                // Validate the guess range
                if (guess < lowerLimit || guess > upperLimit) {
                    System.out.println("Invalid input. Please enter a number between " + lowerLimit + " and " + upperLimit + ".");
                    continue;
                }

                attempts++;
                if (guess == numToGuess) {
                    System.out.println("You Won!");
                    CorrectGuess = true;
                    pScore++; 
                    break;
                } else if (guess < numToGuess) {
                    System.out.println("Incorrect! The number is higher.");
                } else {
                    System.out.println("Incorrect! The number is lower.");
                }

                System.out.println("You have " + (maxAttempts - attempts) + " attempts left.");
            }

            if (!CorrectGuess) {
                System.out.println("Game Over! The correct number was " + numToGuess);
                cScore++; 
            }

            System.out.println("Current Scores - Player: " + pScore + " | Computer: " + cScore);

            
            System.out.print("Do you want to play again? (yes/no): ");
            String replay = scanner.next().toLowerCase();

            if (replay.equals("no")) {
                play = false;
                if (pScore > cScore) {
                    System.out.println("Congratulations! You won the game with a score of " + pScore + " to " + cScore);
                } else if (cScore > pScore) {
                    System.out.println("Sorry, the computer won with a score of " + cScore + " to " + pScore);
                } else {
                    System.out.println("It's a tie! Both scored " + pScore + ".");
                }
                System.out.println("Thanks for playing!");
            }
        }

        scanner.close(); 
    }
}
