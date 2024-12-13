import java.util.Scanner;

public class GameOfGames {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int playerWins = 0;
        int computerWins = 0;

        // Ask user if they want to play in Test Mode or Normal Mode
        System.out.println("Welcome to the Game of Games!");
        System.out.println("Do you want to play in Test Mode? (yes/no): ");
        String testModeChoice = scanner.nextLine().toLowerCase();

        boolean isTestMode = testModeChoice.equals("yes");

        // Game loop
        while (true) {
            System.out.println("\nSelect a game to play:");
            System.out.println("1. Find the Thimble");
            System.out.println("2. Coin Flip");
            System.out.println("3. Guess The Number");
            System.out.println("4. Even and Odd");
            System.out.println("5. Red Thread");
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

            // Based on the user's choice, call the respective game method
            switch (choice) {
                case 1:
                    if (isTestMode) {
                        System.out.println("Test mode");
                        TestFindTheThimble.playGameTestMode(playerWins, computerWins);
                    } else {
                        int[] findTheThimbleScores = FindTheThimble.playGame(playerWins, computerWins);
                        playerWins = findTheThimbleScores[0];
                        computerWins = findTheThimbleScores[1];
                    }
                    break;
                case 2:
                    if (isTestMode) {
                        System.out.println("Test mode");
                        CoinFlip_Test.playCoinFlipGameTestMode(playerWins, computerWins);
                    } else {
                        int[] coinFlipScores = CoinFlipGame.playCoinFlipGame(playerWins, computerWins);
                        playerWins = coinFlipScores[0];
                        computerWins = coinFlipScores[1];
                    }
                    break;
                case 3:
                    if (isTestMode) {
                        System.out.println("Test mode");
                        GuessTheNumberTest.playGuessTheNumberTestMode(playerWins, computerWins);
                    } else {
                        int[] guessTheNumberScores = GuessTheNumber.playGame(playerWins, computerWins);
                        playerWins = guessTheNumberScores[0];
                        computerWins = guessTheNumberScores[1];
                    }
                    break;
//                case 4:
//                    if (isTestMode) {
//                        TestEvenAndOdd.playGameTestMode(playerWins, computerWins);
//                    } else {
//                        int[] evenAndOddScores = EvenAndOdd.playGame(playerWins, computerWins);
//                        playerWins = evenAndOddScores[0];
//                        computerWins = evenAndOddScores[1];
//                    }
//                    break;
                case 5:
                    if (isTestMode) {
                        RedThreadGameTest.playGame(playerWins, computerWins);
                    } else {
                        int[] redThreadScores = RedThreadGame.playGame(playerWins, computerWins);
                        playerWins = redThreadScores[0];
                        computerWins = redThreadScores[1];
                    }
                    break;
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
