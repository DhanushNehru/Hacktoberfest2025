import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidation {
    public static boolean isValidEmail(String email) {
        // Regular expression for email validation
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        // Compile the regular expression
        Pattern pattern = Pattern.compile(emailRegex);

        // Match the email with the regex pattern
        Matcher matcher = pattern.matcher(email);

        // Return true if it matches, false otherwise
        return matcher.matches();
    }

    public static void main(String[] args) {
        // Create a scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter an email address
        System.out.print("Enter an email address to validate: ");
        String email = scanner.nextLine();

        // Check if the email is valid and print the result
        if (isValidEmail(email)) {
            System.out.println("The email address \"" + email + "\" is valid.");
        } else {
            System.out.println("The email address \"" + email + "\" is invalid.");
        }

        // Close the scanner to prevent resource leaks
        scanner.close();
    }
}
