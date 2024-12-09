import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Welcome to the Guess the Number Game!");
        boolean play = true;

        while (play) {

            System.out.println("Please specify a range!");
            System.out.print("Lower limit: ");
            int lowerLimit = scanner.nextInt();

            System.out.print("Upper limit: ");
            int upperLimit = scanner.nextInt();

            if (upperLimit <= lowerLimit) {
                System.out.println("Invalid range. Upper limit must be greater than the lower limit.");
                continue;
            }

            int numberToGuess = random.nextInt(upperLimit - lowerLimit + 1) + lowerLimit;
            int maxAttempts = (upperLimit - lowerLimit + 1) / 2;
            System.out.println("You have " + maxAttempts + " attempts to guess the number!");


            int attempts = 0;
            boolean guessedCorrectly = false;

            while (attempts < maxAttempts) {
                System.out.print("Guess a number between " + lowerLimit + " and " + upperLimit + ": ");
                int guess;

                try {
                    guess = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.next(); // Clear invalid input
                    continue;
                }

                // Validate the guess range
                if (guess < lowerLimit || guess > upperLimit) {
                    System.out.println("Invalid input. Please enter a number between " + lowerLimit + " and " + upperLimit + ".");
                    continue;
                }

                attempts++;
                if (guess == numberToGuess) {
                    System.out.println("You Won!");
                    guessedCorrectly = true;
                    break;
                } else if (guess < numberToGuess) {
                    System.out.println("Incorrect! The number is higher.");

                } else {
                    System.out.println("Incorrect! The number is lower.");
                }

                System.out.println("You have " + (maxAttempts - attempts) + " attempts left.");
            }

            if (!guessedCorrectly) {
                System.out.println("Game Over! The correct number was " + numberToGuess);
            }

            System.out.print("Do you want to play again? (yes/no): ");

            String replay = scanner.next().toLowerCase();

            if (replay.equals("no")) {
                play = false;
                System.out.println("Thanks for playing!");
            }
        }
        scanner.close();
    }
}
