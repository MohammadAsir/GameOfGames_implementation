import java.util.Scanner;
import java.util.Random;

public class FindTheThimble {

    public static void playGame() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.println("Welcome to Find the Thimble!");

        // chooses best out of value
        int rounds = getBestOutOfNumber(scanner);
        System.out.println(" Best out of " + rounds + " rounds.");

        int userPoints = 0;
        int computerPoints = 0;

        //repeat until best out of value reached
        for (int i = 0; i < rounds; i++) {
            System.out.println("Round " + (i + 1) + " of " + rounds);

            System.out.print("Choose a hand to hide the thimble (L or R): ");
            String userChoice = scanner.nextLine();

            while (!(userChoice.equals("L") || userChoice.equals("R"))) {
                System.out.print("Invalid input. Please choose 'L' for left or 'R' for right: ");
                userChoice = scanner.nextLine();
            }

            // Computer guesses randomly
            String computerGuess = random.nextBoolean() ? "L" : "R";
            System.out.println("Computer guesses: " + computerGuess);

            //check the computer guess
            if (userChoice.equals(computerGuess)) {
                computerPoints++;
                System.out.println("Computer wins this round!");
            } else {
                userPoints++;
                System.out.println("You win this round!");
            }


            System.out.println("Current Score - You: " + userPoints + " | Computer: " + computerPoints);
        }

        // announce the winner
        System.out.println("\nGame Over!");
        if (userPoints > computerPoints) {
            System.out.println("You win!");
        } else if (computerPoints > userPoints) {
            System.out.println("Computer wins the game!");
        } else {
            System.out.println("The game is a tie!");
        }

        //
        String replayChoice;
        do {
            System.out.print("\nDo you want to play again? (yes/no): ");
            replayChoice = scanner.nextLine().toLowerCase();
            if (!(replayChoice.equals("yes") || replayChoice.equals("no"))) {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        } while (!(replayChoice.equals("yes") || replayChoice.equals("no")));

        if (replayChoice.equals("yes")) {
            playGame();
        } else {
            System.out.println("Thanks for playing!");
            // Return to main menu could be implemented here
        }
    }

    // Method to handle "best out of" number input
    private static int getBestOutOfNumber(Scanner scanner) {
        int rounds;
        while (true) {
            System.out.print("Enter a 'best out of' number (odd number): ");
            if (scanner.hasNextInt()) {
                rounds = scanner.nextInt();
                if (rounds % 2 != 0) {
                    scanner.nextLine(); // consume the remaining newline
                    return rounds;
                } else {
                    System.out.println("Please enter a valid odd number.");
                }
            } else {
                System.out.println("Invalid input. Please enter an odd number.");
                scanner.next(); // consume invalid input
            }
        }
    }

    public static void main(String[] args) {
        playGame();
    }
}
