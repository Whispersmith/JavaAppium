package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SearchPageObject extends MainPageObject{
    protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_RESULT_BY_STRING_TPL,
            SEARCH_RESULT_BY_TWO_STRING_TPL,
            SEARCH_SKIP_BUTTON ,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULT_LABEL;


    public SearchPageObject(RemoteWebDriver driver){
        super(driver);
    }

    /* TEMPLATE METHODS */
    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT_BY_STRING_TPL.replace("{SUBSTRING}",substring);
    }

    private static String getResultFromSearchList(String title, String description){
        return SEARCH_RESULT_BY_TWO_STRING_TPL.replace("{SUBSTRING1}",title).replace("{SUBSTRING2}",description);
    }

    /* TEMPLATE METHODS */

    public void initSearchInput (){
        this.waitForElementPresent(SEARCH_INPUT, "Cannot find search input"+ SEARCH_INPUT);
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find element and click",5);
    }
    public void setSearchSkipButton(){
        //this.waitForElementPresent(By.id(SEARCH_SKIP_BUTTON), "Cannot find Skip button");
        this.waitForElementAndClick(SEARCH_SKIP_BUTTON, "Cannot tap to skip button", 5);
    }

    public void typeSearchLine(String search_line){
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot type to search input", 5);
    }

    public void waitForSearchResult(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring " + substring);
    }
    public void clickByArticleWithSubstring(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring " + substring, 10);
    }

    public void waitForElementByTitleAndDescription(String title, String description){
        String search_result = getResultFromSearchList(title,description);
        this.waitForElementPresent(search_result,"Cannot find article with title " +title+ " and with description " +description , 10);
    }

    @Override
    public boolean waitForElementNotPresent(String locator, String error_message, long timeOutInSecond) {
        return super.waitForElementNotPresent(locator, error_message, timeOutInSecond);
    }
    public void waitForCancelButtonToAppear(){
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find Cancel button", 5);

    }

    public void waitForCancelButtonToDissappear(){
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Cancel button is still present", 5);
    }
    public void clickCancelButton(){
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON,"Cannot tap to the Cancel button", 5);
    }

    public int getAmountOfFoundArticles(){

        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find something by request ",
                15
        );
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultsLabel(){
        this.waitForElementPresent(
                SEARCH_EMPTY_RESULT_LABEL,
                "Cannot find empty result label by request ",
                15
        );
    }

    public void assertThereIsNoResultsOfSearch(){
        this.assertElementsNotPresent(
                SEARCH_RESULT_ELEMENT,
                "We've found some results"
        );
    }
    public void assertThereIsResultOfSearch(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.assertElementPresent(
                search_result_xpath,
                "We've not found results by text " + substring
        );
    }

}
