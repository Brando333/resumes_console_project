package org.brando.view;


import org.brando.controller.LoginController;
import org.brando.model.Login;
import org.brando.model.User;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;
import static org.brando.view.HomeView.showHome;

public class LoginView {
    private static final Scanner scanner = new Scanner(in);

    public static void showLogin() {

        out.println("You can always use the {'/b' = back , '/e' = exit} options");
        out.println(Utils.getCarets(20));
        out.println("Enter your email: ");
        String email = scanner.nextLine();
        OptionsManager.checkOptions(email, LoginView.class);

        out.println("Enter your password: ");
        String password = scanner.nextLine();
        OptionsManager.checkOptions(password, LoginView.class);

        Login login = new Login(email, password);
        LoginController loginController = new LoginController(login);

        User user = loginController.login();

        if (user != null) {
            showHome(user);
        } else {
            out.println("You can always use the '/b' = back , '/e' = exit options");
            out.println("Your password or your email doesn't match");
            out.println(Utils.getCarets(20));
            out.println(Utils.getNewLines(3));
            showLogin();
        }

    }
}
