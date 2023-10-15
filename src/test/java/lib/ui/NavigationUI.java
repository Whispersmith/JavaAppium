package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.rmi.Remote;

abstract public class NavigationUI extends MainPageObject{
    protected static String
        NAVIGATE_UP_BUTTON,
        SAVED_ARTICLES,
        OPEN_NAVIGATION,
            MY_LIST_LINK;

    public NavigationUI(RemoteWebDriver driver){
        super(driver);
    }
    public void navigateUpFromArticle(){
        this.waitForElementAndClick(
                NAVIGATE_UP_BUTTON,
                "Cannot click to Back button",
                5
        );
    }

    public void goToSavedArticles(){
        this.waitForElementAndClick(
                SAVED_ARTICLES,
                "Cannot find Saved in bottom bar",
                5
        );
    }
    public void openNavigation(){
        if(Platform.getInstance().isMW()){
            waitForElementAndClick(OPEN_NAVIGATION, "Cannot find menu navigation",5);

        }else {
            System.out.println("Method openNavigation() does nothing for "+ Platform.getInstance().getPlatformVar());
        }
    }

    public void clickToMyLists(){
        if(Platform.getInstance().isMW()){
            this.tryToClickElementWithFewAttempts(
                    MY_LIST_LINK,
                    "Cannot find navigation button to my lists",
                    5);
        }
        this.waitForElementAndClick(MY_LIST_LINK,"Cannot find navigation button to my lists", 1);
    }


}
