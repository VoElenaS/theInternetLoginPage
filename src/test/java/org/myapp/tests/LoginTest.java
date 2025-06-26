package org.myapp.tests;

import org.junit.jupiter.api.Test;
import org.myapp.models.User;
import org.myapp.pages.LoginPage;
import org.myapp.utils.DataGenerator;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginTest extends BaseTest {
    @Test
    public void testSuccessfulLogin() {
        User user = User.builder()
                .name("tomsmith")
                .password("SuperSecretPassword!")
                .build();

        String expected = new LoginPage(driver, wait)
                .loginAs(user)
                .getSuccessfulMessage();

        assertTrue(expected.contains("You logged into a secure area!"));
    }

    @Test
    public void testFailLogin() {
        User user = DataGenerator.realUser();

        String expected = new LoginPage(driver, wait)
                .loginWithInvalidCredentials(user)
                .getMessageUserInvalide();

        assertTrue(expected.contains("Your username is invalid!"));
    }

}