package org.brando.controller;

import org.brando.model.Login;
import org.brando.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Deque;

import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest {


    private static Login login;

    @BeforeAll
    public static void init() {
        //this user is already in the data base
        login = new Login("diego@hotmail.com", "123");

    }

    @Test
    public void login() {
        LoginController loginController = new LoginController(login);
        User user = loginController.login();
        Assertions.assertNotNull(user);

    }
}