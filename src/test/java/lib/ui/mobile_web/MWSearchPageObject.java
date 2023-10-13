package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "css:button#searchIcon";
        SEARCH_INPUT = "css:form>input[type='search']";
        SEARCH_RESULT_BY_STRING_TPL = "xpath://div[contains(@class, 'wikidata-description')][contains(text(), '{SUBSTRING}'])";

  //      SEARCH_RESULT_BY_TWO_STRING_TPL = "//*[@resource-id = 'org.wikipedia:id/page_list_item_title' and @text = '{SUBSTRING1}']/../*[@resource-id = 'org.wikipedia:id/page_list_item_description' and @text = '{SUBSTRING2}']";
        SEARCH_CANCEL_BUTTON = "css://button.cancel";
        SEARCH_RESULT_ELEMENT = "css://ul.page-list>li.page-summary";
        SEARCH_EMPTY_RESULT_LABEL = "css:p.without-results";
    }

    public MWSearchPageObject(RemoteWebDriver driver){
        super(driver);
    }

}

