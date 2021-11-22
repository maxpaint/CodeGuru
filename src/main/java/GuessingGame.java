import java.util.Scanner;

public class GuessingGame {
    public static void main(String[] args) {
        int computerNumber = (int) (Math.random() * 100 + 1);
        System.out.println("A random number has been generated...");
        Scanner scanner = new Scanner(System.in);
        int userAnswer = 0;
        int length = 3;
        for (int i = 0; i < length; i++) {
            System.out.println("Enter a guess between 1 and 100");
            userAnswer = scanner.nextInt();
            extracted(computerNumber, userAnswer, i != length - 1 ? "Your guess is too high, guess again." : "Your guess is too high");
        }
        System.out.println("Sorry for your luck, restart the program to play again!");
    }

    private static void extracted(int computerNumber, int userAnswer, String s) {
        if (userAnswer <= 0 || userAnswer > 100) {
            System.out.println("Invalid response");
        } else if (userAnswer == computerNumber) {
            System.out.println("Correct! You win! See you next time!");
            System.exit(0);
        } else if (userAnswer > computerNumber) {
            System.out.println(s);
        } else if (userAnswer < computerNumber) {
            System.out.println("Your guess is too low, guess again.");
        } else {
            System.out.println("Your guess is incorrect");
        }
    }
}
