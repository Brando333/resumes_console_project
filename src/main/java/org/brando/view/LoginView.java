package org.brando.view;


import org.brando.controller.LoginController;
import org.brando.model.Login;
import org.brando.model.User;

import java.util.Scanner;

import static java.lang.System.*;

public class LoginView {
    private static Scanner scanner = new Scanner(in);

    public static void showLogin() {

        out.println("Enter your email: ");
        String email = scanner.nextLine();

        out.println("Enter your password: ");
        String password = scanner.nextLine();

        Login login = new Login(email, password);
        LoginController loginController = new LoginController(login);

        User user = loginController.login();

        if (user != null) {
            HomeView.showHome(user);
        } else {
            out.println("Your password or your email doesn't match");
        }

    }
}
