package org.brando.controller;

import java.util.Calendar;

public class Utils {
    public static String getRandomEmail() {
        return Calendar.getInstance().toInstant().toString().concat("@example.com");
    }

    public static String getRandomTitle(String tittle) {
        return tittle.concat(Calendar.getInstance().toInstant().toString());
    }
}
