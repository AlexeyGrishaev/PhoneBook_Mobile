package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;


public class LoginTest extends AppiumConfig {
    //Classic
    // "locker@gmail.com", "Qwerty1234!"
    @Test
    public void loginSuccess() {
//       boolean result =  new SplashScreen(driver)
//                .checkCurrentVersion("Version 1.0.0")
        boolean result = new AuthenticationScreen(driver)
                .fillEmail("locker@gmail.com")
                .fillPassword("Qwerty1234!")
                .submitLogin()
                .isActivityTitleListDisplayed("Contact list");

        Assert.assertTrue(result);
    }

    @Test
    public void loginSuccessLoginModel() {
//        boolean result =  new SplashScreen(driver)
//                .checkCurrentVersion("Version 1.0.0")
        boolean result = new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder()
                        .email("locker@gmail.com")
                        .password("Qwerty1234!")
                        .build())
                .submitLogin()
                .isActivityTitleListDisplayed("Contact list");
        Assert.assertTrue(result);
    }

    @Test
    public void loginSuccessLoginModel2() {
        Assert.assertTrue(new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder()
                        .email("locker@gmail.com")
                        .password("Qwerty1234!")
                        .build())
                .submitLogin()
                .isActivityTitleListDisplayed("Contact list"));

    }

    @Test
    public void loginWrongEmail() {
        new AuthenticationScreen(driver).fillLoginRegistrationForm(Auth.builder()
                .email("lockergmail.com")
                .password("Qwerty1234!")
                .build()).submitLoginNegative().isErrorMessageContainsText("Login or Password incorrect");

    }
    @Test
    public void loginWrongPassword() {
        new AuthenticationScreen(driver).fillLoginRegistrationForm(Auth.builder()
                .email("locker@gmail.com")
                .password("Qwerty1234")
                .build()).submitLoginNegative().isErrorMessageContainsText("Login or Password incorrect");

    }

    @AfterMethod
    public void postCondition() {
        new ContactListScreen(driver).logout();
    }


}
