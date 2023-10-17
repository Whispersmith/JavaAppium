package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
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
    @Step("Click to the Back button for navigate to main screen(this method does nothing for Mobile Web)")
    public void navigateUpFromArticle(){
        this.waitForElementAndClick(
                NAVIGATE_UP_BUTTON,
                "Cannot click to Back button",
                5
        );
    }

    @Step("Go to Saved articles(this method does nothing for Mobile Web)")
    public void goToSavedArticles(){
        this.waitForElementAndClick(
                SAVED_ARTICLES,
                "Cannot find Saved in bottom bar",
                5
        );
    }
    @Step("Open navigation menu(this method does nothing for android)")
    public void openNavigation(){
        if(Platform.getInstance().isMW()){
            waitForElementAndClick(OPEN_NAVIGATION, "Cannot find menu navigation",5);

        }else {
            System.out.println("Method openNavigation() does nothing for "+ Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Click button to navigate to list of saved articles(this method does nothing for android)")
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
