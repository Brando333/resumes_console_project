package org.brando.view;

import java.util.Scanner;

import static java.lang.System.*;
import static org.brando.view.LoginView.showLogin;
import static org.brando.view.SignInView.*;

public class IndexView {
    private static final Scanner scanner = new Scanner(in);

    public static void showIndex() {
        int option;

        out.println("[1] Log In");
        out.println("[2] Sign In");
        out.println("[0] Quit");
        option = scanner.nextInt();

        switch (option) {
            case 1 -> showLogin();
            case 2 -> showSignIn();
            case 0 -> System.exit(1);
        }
    }
}
