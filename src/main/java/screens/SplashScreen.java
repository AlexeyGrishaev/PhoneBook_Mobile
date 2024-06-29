package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

public class SplashScreen extends BaseScreen{

    public SplashScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/version_text']")
    AndroidElement versionTestView;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/title_text']")
    AndroidElement titleTextView;

    public String getCurrentVersion(){
        return versionTestView.getText();
    }

    public AuthenticationScreen checkCurrentVersion(String version){
        iShouldHave(versionTestView,version,5);
        return new AuthenticationScreen(driver);
    }
}
