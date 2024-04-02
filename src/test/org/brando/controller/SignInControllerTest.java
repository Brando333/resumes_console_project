package org.brando.controller;

import org.brando.model.SigIn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SignInControllerTest {


    @Test
    public void normalSignIn() { // checks if the user is signed in

        SigIn normalSigIn = new SigIn("test", Utils.getRandomEmail(), "test", "test");
        SignInController signInController = new SignInController(normalSigIn);
        int signed = signInController.signIn();
        Assertions.assertEquals(1, signed);

    }
}