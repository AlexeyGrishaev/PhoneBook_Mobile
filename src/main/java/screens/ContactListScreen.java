package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

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
    @FindBy(xpath = "//*[@content-desc='add']")
    AndroidElement plusBtn;
    @FindBy(id = "com.sheygam.contactapp:id/rowName")
    List<AndroidElement>  contactNameList;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowContainer']")
    List<AndroidElement> contactList;
    @FindBy(id = "android:id/button1")
    AndroidElement yesBtn;
    @FindBy(id = "com.sheygam.contactapp:id/emptyTxt")
    AndroidElement noContactsHereTextView;
    @FindBy(xpath = "//*[@content-desc='More options']")
    List<AndroidElement> menuOptionList;

    int countBefore;
    int countAfter;
    public boolean isActivityTitleListDisplayed(String text){

        //return activityTextView.getText().contains("Contact list");
        return iShouldHave(activityTextView,text,8);
    }

    public AuthenticationScreen logout(){
        if(activityTextView.getText().equals("Contact list")) {
            menuOption.click();
            logoutBtn.click();
        }
        return new AuthenticationScreen(driver);
    }
    public AuthenticationScreen logout2() {
        if (isElementDisplayed(menuOption)) {
            menuOption.click();
            logoutBtn.click();
        }
        return new AuthenticationScreen(driver);
    }
    public AuthenticationScreen logout3() {
        if (isElementPresentInList(menuOptionList)) {
            menuOption.click();
            logoutBtn.click();
        }
        return new AuthenticationScreen(driver);
    }

    public ContactListScreen isAccountOpened(){
        Assert.assertTrue(isActivityTitleListDisplayed("Contact list"));
        return this;

    }

    public AddNewContactScreen openContactForm(){


            plusBtn.click();
        return new AddNewContactScreen(driver);
    }

    public ContactListScreen isContactAddedByName(String name, String lastName) {
        iShouldHave(activityTextView,"Contactl list",5);
        boolean isPresent = false;
        for (AndroidElement el :contactNameList){
            if(el.getText().equals(name+" "+lastName)) {
                isPresent = true;
                break;
            }
        }
        Assert.assertTrue(isPresent);
        return this;
    }

    public ContactListScreen deleteFirstContact(){
        isActivityTitleListDisplayed("Contact list");
        countBefore = contactList.size();
        //countBefore
        AndroidElement first = contactNameList.get(0);
        Rectangle rectangle = first.getRect();
        int xFrom=rectangle.getX()+rectangle.getWidth()/8;
        int y=rectangle.getY()+rectangle.getHeight()/2;
        int xTo=rectangle.getX()+(rectangle.getWidth()/8)*7;
        //int xTo2 = rectangle.getWidth()-xFrom;
        TouchAction<?> touchAction = new TouchAction<>(driver);
        touchAction.longPress(PointOption.point(xFrom,y))
                .moveTo(PointOption.point(xTo,y))
                .release()
                .perform();

        should(yesBtn, 8);
        yesBtn.click();
        countAfter = contactList.size();
        //countAfter


        return this;
    }

    public ContactListScreen isListSizeLessOnOne() {
        Assert.assertEquals(countBefore-countAfter,1);
        return this;
    }

    public ContactListScreen removeAllContacts() {
        pause(1000);
        while (contactList.size()>0){
            deleteFirstContact();
        }
        return this;
    }

    public ContactListScreen isNoContactsHere() {
        pause(1000);
        iShouldHave(noContactsHereTextView,"No Contacts. Add One more!",10);
        return this;
    }
}
