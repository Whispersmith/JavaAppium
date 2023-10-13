package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.rmi.Remote;

abstract public class NavigationUI extends MainPageObject{
    protected static String
        NAVIGATE_UP_BUTTON,
        SAVED_ARTICLES;

    public NavigationUI(RemoteWebDriver driver){
        super(driver);
    }
    public void navigateUpFromArticle(){
        this.waitForElementAndClick(
                By.xpath(NAVIGATE_UP_BUTTON),
                "Cannot click to Back button",
                5
        );
    }

    public void goToSavedArticles(){
        this.waitForElementAndClick(
                By.xpath(SAVED_ARTICLES),
                "Cannot find Saved in bottom bar",
                5
        );
    }


}
