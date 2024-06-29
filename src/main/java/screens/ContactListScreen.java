package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class ContactListScreen extends BaseScreen{
    public ContactListScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    AndroidElement activityTextView;
    @FindBy(xpath = "//*[@content-desc='More options']")
    AndroidElement menuOption;
    @FindBy(xpath = "//*[@text='Logout']")
    AndroidElement logoutBtn;

    public boolean isActivityTitleListDisplayed(String text){

        //return activityTextView.getText().contains("Contact list");
        return iShouldHave(activityTextView,text,8);
    }

    public AuthenticationScreen logout(){
        menuOption.click();
        logoutBtn.click();
        return new AuthenticationScreen(driver);
    }

    public ContactListScreen isAccountOpened(){
        Assert.assertTrue(isActivityTitleListDisplayed("Contact list"));
        return this;

    }
}
