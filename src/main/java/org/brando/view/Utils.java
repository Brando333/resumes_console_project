package org.brando.view;

public class Utils {

    /**
     * create a line of n times
     */

    public static String getCarets(int n) {
        return "_".repeat(n);
    }

    public static String getNewLines(int n) {
        return "\n".repeat(n);
    }
}
