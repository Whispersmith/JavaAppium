package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends  MainPageObject{
    protected static String
        TITLE,
        FOOTER_ELEMENT ,
        SAVE_BUTTON ,
        ADD_TO_LIST_BUTTON ,
        MY_LIST_NAME_INPUT ,
        OK_BUTTON ,
        CLOSE_ARTICLE_BUTTON,
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
            OPTIONS_ADD_TO_MY_LIST_BUTTON;


    public ArticlePageObject(RemoteWebDriver driver){
            super(driver);
        }
    @Step("Waiting for title on the article page")
    public WebElement waitForTitleArticle(){
        return this.waitForElementPresent(String.valueOf(By.id(TITLE)), "Cannot find title", 5);
    }
    @Step("Get article title")
    public String getArticleTitle(){
        WebElement title_element = waitForTitleArticle();
        screenshot(this.takeScreenshot("article_title"));
        if(Platform.getInstance().isAndroid()){
            return title_element.getAttribute("text");}
        else {
            return title_element.getText(); // for MW
        }

    }
    @Step("Swiping article to the footer")
    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(FOOTER_ELEMENT, "Cannot find the end of article", 5);
        }else
            this.scrollWebpageTillElementNotVisible(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article "+ FOOTER_ELEMENT,
                    40
            );
    }
    @Step("Adding article to the my list ")
    public void addArticleToMyList(String name_of_folder) {
        this.waitForElementAndClick(
                SAVE_BUTTON,
                "Cannot find Save",
                5
        );
        this.waitForElementAndClick(
                ADD_TO_LIST_BUTTON,
                "Cannot find button",
                5
        );
        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put the text into articles folder input",
                5
        );
        this.waitForElementAndClick(
                OK_BUTTON,
                "Cannot press the OK button",
                5
        );
    }

    @Step("Adding another article to my list")
    public void addArticleToMyOldList() {
        this.waitForElementAndClick(
                SAVE_BUTTON,
                "Cannot find Save",
                5
        );
        this.waitForElementAndClick(
                ADD_TO_LIST_BUTTON,
                "Cannot find button",
                5
        );
    }

    @Step("Adding article to my saved")
    public void addArticleToMySaved(){
        if(Platform.getInstance().isMW()){
            this.removeArticleFromSavedIfItAdded();
        }
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find options to add article to reading list",
                5);
    }
    @Step("Closing article")
    public void closeArticle(){
        if(Platform.getInstance().isAndroid()){
            this.waitForElementAndClick(
                    CLOSE_ARTICLE_BUTTON,
                    "Cannot close article by X",
                    5);
        }else {
            System.out.println("Method closeArticle() does nothing for "+ Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Removing article from my saved list if it has been added")
    public void removeArticleFromSavedIfItAdded(){
        if(this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)){
            this.waitForElementAndClick(
                    OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot find button to remove article from saved list",
                    5);
            this.waitForElementPresent(
                    OPTIONS_ADD_TO_MY_LIST_BUTTON,
                    "Cannot find button to add article after remove",
                    1 );
        }
    }


}
