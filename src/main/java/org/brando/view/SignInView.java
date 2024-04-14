package org.brando.view;

import org.brando.controller.SignInController;
import org.brando.exceptions.EmailAlreadyTakenException;
import org.brando.model.SigIn;
import org.brando.model.User;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;
import static org.brando.view.HomeView.showHome;

public class SignInView {
    private static Scanner scanner = new Scanner(in);

    public static void showSignIn() {
        int option;

        out.println("Enter your full name");
        String fullName = scanner.nextLine();
        out.println("Enter your email address");
        String email = scanner.nextLine();
        out.println("Enter your password");
        String password = scanner.nextLine();
        out.println("Reconfirm your password");
        String reconfirmationPassword = scanner.nextLine();

        validateInputs(new SigIn(fullName, email, password, reconfirmationPassword));

        try {
            SigIn sigIn = new SigIn(fullName, email, password, reconfirmationPassword);
            SignInController signInController = new SignInController(sigIn);
            int userId = signInController.signIn();
            User user = sigIn.getUser();
            user.setId(userId);
            showHome(sigIn.getUser());
        } catch (EmailAlreadyTakenException e) {
            System.out.println(STR."\nThe email \"\{email}\" is already taken, try other email.");
            out.println(Utils.getNewLines(2));

            showSignIn();
        }

    }

    private static void validateInputs(SigIn sigIn) {

        String fullName = sigIn.getFullName();
        String email = sigIn.getEmailAddress();
        String password = sigIn.getRawPassword();
        String passwordReconfirmation = sigIn.getRawPasswordReconfirmation();

        if (!isNameValid(fullName)) {
            out.println("Invalid name");
            out.println(Utils.getNewLines(2));
            showSignIn();
        } else if (!isEmailValid(email)) {
            out.println("Invalid email");
            out.println(Utils.getNewLines(2));
            showSignIn();
        } else if (!isReconfirmationPasswordMatching(password, passwordReconfirmation)) {
            out.println("Reconfirmation password doesn't match");
            out.println(Utils.getNewLines(2));
            showSignIn();
        }
    }

    private static boolean isReconfirmationPasswordMatching(String password, String passwordReconfirmation) {
        return password.equals(passwordReconfirmation);
    }

    private static boolean isEmailValid(String email) {
        return email.matches("^\\w+@[a-zA-Z]+\\..*$");
    }

    private static boolean isNameValid(String fullName) {
        return fullName.matches("[a-zA-Z ]+");
    }
}
