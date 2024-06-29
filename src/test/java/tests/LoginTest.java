package tests;

import config.AppiumConfig;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.SplashScreen;

public class LoginTest extends AppiumConfig {
    // "locker@gmail.com", "Qwerty1234!"
    @Test
    public void loginSuccess(){
       boolean result =  new SplashScreen(driver)
                .checkCurrentVersion("Version 1.0.0")
                .fillEmail("locker@gmail.com")
                .fillPassword("Qwerty1234!")
                .submitLogin()
                .isActivityTitleListDisplayed("Contact list");

        Assert.assertTrue(result);
    }
}
