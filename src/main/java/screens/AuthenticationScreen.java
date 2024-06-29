package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

public class AuthenticationScreen extends BaseScreen{
    public AuthenticationScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/inputEmail']")
    AndroidElement emailEditText;
    @FindBy(id = "com.sheygam.contactapp:id/inputPassword")
    AndroidElement passwordEditText;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/loginBtn']")
    AndroidElement loginBtn;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/regBtn']")
    AndroidElement registrationBtn;

    public AuthenticationScreen fillEmail(String email){
        //pause(4000);
        should(emailEditText,10);
        type(emailEditText,email);
        return this;
    }
   // "locker@gmail.com", "Qwerty1234!"
    public AuthenticationScreen fillPassword(String password){
        type(passwordEditText,password);
        return this;
    }

    public ContactListScreen submitLogin(){
        loginBtn.click();
        return new ContactListScreen(driver);
    }

}
