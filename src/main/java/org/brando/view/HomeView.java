package org.brando.view;

import org.brando.model.User;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;
import static org.brando.view.IndexView.showIndex;
import static org.brando.view.ResumesView.showResumesCreate;
import static org.brando.view.ResumesView.showResumesPanel;

public class HomeView {

    private static final Scanner scanner = new Scanner(in);

    public static void showHome(User user) {

        int option;


        out.println(Utils.getCarets(16));
        out.println(STR."Welcome back \{user.getFullName()}");
        out.println(Utils.getCarets(16));
        out.println("[1] Create resume");
        out.println("[2] See resumes");
        out.println("[3] Exit");
        out.println(Utils.getCarets(16));
        option = scanner.nextInt();

        switch (option) {
            case 1 -> showResumesCreate(user);
            case 2 -> showResumesPanel(user);
            case 3 -> {
                out.println("You have successfully logged out.");
                showIndex();
            }
        }

    }
}
