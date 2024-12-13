import java.util.Scanner;

public class GameOfGamesTestMode {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int playerWins = 0;
        int computerWins = 0;

        System.out.println("Test Mode - Welcome to the Game of Games!");

        // Game loop for Test Mode
        while (true) {
            System.out.println("\nSelect a game to play (Test Mode):");
            System.out.println("1. Test Find the Thimble");
            System.out.println("2. Test Coin Flip");
            System.out.println("3. Test Guess the Number");
            System.out.println("4. Test Even and Odd");
            System.out.println("5. Test Red Thread");
            System.out.println("6. Quit");

            String input = scanner.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(input); // Convert string input to integer
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                continue; // If input is not a valid number, continue to the next iteration
            }

            if (choice == 6) {
                break;  // Exit the game loop
            }

            // Based on the user's choice, call the respective test mode game method
            switch (choice) {
                case 1:
                    int[] findTheThimbleScores = FindTheThimbleTest.playGameTestMode(playerWins, computerWins);
                    playerWins = findTheThimbleScores[0];
                    computerWins = findTheThimbleScores[1];
                    break;
                case 2:
                    int[] coinFlipScores = CoinFlip_Test.playCoinFlipGameTestMode(playerWins, computerWins);
                    playerWins = coinFlipScores[0];
                    computerWins = coinFlipScores[1];
                    break;
                case 3:
                    int[] guessTheNumberScores = GuessTheNumberTest.playGuessTheNumberTestMode(playerWins, computerWins);
                    playerWins = guessTheNumberScores[0];
                    computerWins = guessTheNumberScores[1];
                    break;
                case 4:
                    System.out.println("Test Even and Odd is not implemented yet.");
                    break;
//                case 5:
//                    int[] redThreadScores = RedThreadGameTest.playGameTestMode(playerWins, computerWins);
//                    playerWins = redThreadScores[0];
//                    computerWins = redThreadScores[1];
//                    break;
                default:
                    System.out.println("Invalid choice, please select again.");
            }

            System.out.println("Current score - Player: " + playerWins + " | Computer: " + computerWins);
        }

        // After the game loop ends, determine the overall winner
        if (playerWins > computerWins) {
            System.out.println("Player wins with " + playerWins + " wins!");
        } else if (computerWins > playerWins) {
            System.out.println("Computer wins with " + computerWins + " wins!");
        } else {
            System.out.println("It's a tie with " + playerWins + " wins each!");
        }

        scanner.close();
    }
}
