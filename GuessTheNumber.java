import java.util.Scanner;
import java.util.Random;
public class GuessTheNumber {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Welcome to the Guess the Number Game!");
        boolean play = true;

        while(play){
            System.out.println("Please specify a range! ");
            System.out.println("Lower limit: ");
            int LowerLim = scanner.nextInt();

            System.out.println("Upper limit: ");
            int UpperLim = scanner.nextInt();

            //Calculating the maximum attempts

            int maximumAttempts = (UpperLim - LowerLim +1) / 2;
            System.out.println("You have " + maximumAttempts +" attempts to guess the number!");

            int numberToGuess = random.nextInt(UpperLim - LowerLim +1) + LowerLim;

            int attempts = 0;
            boolean guessedCorrectly = false;

            while (attempts < maximumAttempts) {
                System.out.println("Guess a number between " + LowerLim + " and " + UpperLim + ": ");
                int guess = scanner.nextInt();

                if (guess == numberToGuess) {
                    System.out.println("Correct!! You guessed the number!");
                    guessedCorrectly = true;
                    break;
                } else {
                    System.out.println(" Wrong guess!! Try again!");
                }
                attempts++;

                System.out.println("You have " + (maximumAttempts - attempts) + " left.");
            }
            if(!guessedCorrectly){
                System.out.println("You ran out of attempts! The correct number was " + numberToGuess);
            }
            System.out.println("Do you want to play again? (yes/no): ");
            String answer = scanner.next();

            if (answer.equalsIgnoreCase("yes")){
                play = true;
            }
            else {
                play = false;
                System.out.println("Thanks for playing!");
            }
        }
        scanner.close();


    }
}
