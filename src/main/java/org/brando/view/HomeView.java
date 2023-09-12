package org.brando.view;

import org.brando.model.User;

import java.util.Scanner;

import static java.lang.System.*;

public class HomeView {

    private static Scanner scanner = new Scanner(in);

    public static void showHome(User user) {

        int option;

        out.println(Utils.getLines(16));
        out.println("[1] See resumes");
        out.println("[2] Create resume");
        out.println("[3] Exit");
        out.println(Utils.getLines(16));
        option = scanner.nextInt();

        switch (option) {
            case 1 -> ResumesView.showResumesPanel(user);
            case 2 -> ResumesView.showResumesCreate(user);
            case 3 -> LoginView.showLogin();
        }

    }
}
