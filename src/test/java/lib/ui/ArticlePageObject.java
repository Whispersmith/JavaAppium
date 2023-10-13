package lib.ui;

import io.appium.java_client.AppiumDriver;
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
        OK_BUTTON ;


    public ArticlePageObject(RemoteWebDriver driver){
            super(driver);
        }
    public WebElement waitForTitleArticle(){
            return this.waitForElementPresent(By.id(TITLE), "Cannot find title", 5);
    }
    public String getArticleTitle(){
        WebElement title_element = waitForTitleArticle();
        if(Platform.getInstance().isAndroid()){
            return title_element.getAttribute("text");}
        else {
            return title_element.getText();
        }

    }
    public void swipeToFooter(){
        this.swipeUpToFindElement(By.xpath(FOOTER_ELEMENT),"Cannot find the end of article", 5);
    }

    public void addArticleToMyList(String name_of_folder) {
        this.waitForElementAndClick(
                By.id(SAVE_BUTTON),
                "Cannot find Save",
                5
        );
        this.waitForElementAndClick(
                By.id(ADD_TO_LIST_BUTTON),
                "Cannot find button",
                5
        );
        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                name_of_folder,
                "Cannot put the text into articles folder input",
                5
        );
        this.waitForElementAndClick(
                By.xpath(OK_BUTTON),
                "Cannot press the OK button",
                5
        );
    }

    public void addArticleToMyOldList() {
        this.waitForElementAndClick(
                By.id(SAVE_BUTTON),
                "Cannot find Save",
                5
        );
        this.waitForElementAndClick(
                By.id(ADD_TO_LIST_BUTTON),
                "Cannot find button",
                5
        );

    }


}
