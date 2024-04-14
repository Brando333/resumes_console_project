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
        String address = scanner.nextLine();
        out.println("Enter your password");
        String password = scanner.nextLine();
        out.println("Reconfirm your password");
        String reconfirmationPassword = scanner.nextLine();

        SigIn sigIn = new SigIn(fullName, address, password, reconfirmationPassword);
        SignInController signInController = new SignInController(sigIn);

        int userId = 0;
        try {
            userId = signInController.signIn();
        } catch (EmailAlreadyTakenException e) {
            out.println("Try with other email.");
            out.println(Utils.getNewLines(2));

            showSignIn();
        }
        User user = sigIn.getUser();
        user.setId(userId);

        showHome(sigIn.getUser());
    }
}
