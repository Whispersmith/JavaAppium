package lib.ui;

import io.qameta.allure.Step;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObjects extends MainPageObject{
    protected static String
        NOT_NOW_BUTTON ,
        FOLDER_BY_NAME_TPL ,
        ARTICLE_TITLE_TPL,
            REMOVE_FROM_SAVE_BUTTON;

    public MyListsPageObjects(RemoteWebDriver driver){
        super(driver);
    }

    @Step("Click button Not Now(this method does nothing for Mobile Web)")
    public void clickNotNow(){
        this.waitForElementAndClick(
                NOT_NOW_BUTTON,
                "Cannot close the dialog by Not now",
                5
        );
    }

    private static String getFolderByXpath(String name_folder){
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_folder);
    }

    @Step("Open folder by name")
    public void openFolderByName(String name_folder){
        String folder_name_xpath = getFolderByXpath(name_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find folder by name "+ name_folder,
                5
        );
    }
    @Step("Search article by title(this method does nothing for android)")
    public static String getTitleByXpath(String article_title){
        return ARTICLE_TITLE_TPL.replace("{TITLE}",article_title);
    }

    @Step("Search button to remove article(this method does nothing for android)")
    public static String getRemoveButtonByTitle(String article_title){
        return REMOVE_FROM_SAVE_BUTTON.replace("{TITLE}",article_title);
    }

    @Step("Wait article is appear")
    public void waitForArticleAppear(String article_title){
        String article_xpath = getTitleByXpath(article_title);
        this.waitForElementPresent(
                article_xpath,
                "Cannot find article " + article_xpath,
                5);
    }
    @Step("Wait article is dissapear")
    public void waitForArticleDisapear(String article_title){
        String article_xpath = getTitleByXpath(article_title);
        this.waitForElementNotPresent(
                article_xpath,
                "Article is still present",
                5);
    }

    @Step("Swipe article to delete for android or refresh page for mobile web")
    public void swipeArticleToDelete(String article_title){
        String article_xpath = getFolderByXpath(article_title);
        this.waitForArticleAppear(article_title);
        if(Platform.getInstance().isAndroid()) {
            this.swipeElementToLeft(
                    article_xpath,
                    "Cannot find article title " + article_xpath + " for swipe"
            );
        }else{
            String remove_locator = getRemoveButtonByTitle(article_title);
            this.waitForElementAndClick(
                    remove_locator,
                    "Cannot click button to remove article ",
                    10
            );
        }
        if(Platform.getInstance().isMW()){
            driver.navigate().refresh();
        }

        this.waitForArticleDisapear(article_title);
    }



}
