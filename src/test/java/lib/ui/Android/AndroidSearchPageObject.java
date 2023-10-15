package lib.ui.Android;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSearchPageObject extends SearchPageObject {

    static {
                SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]";
                SEARCH_INPUT = "xpath://*[contains(@text, 'Search Wikipedia')]";
                SEARCH_RESULT_BY_STRING_TPL = "xpath://*[@resource-id = 'org.wikipedia:id/search_results_display']//*[contains(@text, '{SUBSTRING}')]";

                SEARCH_RESULT_BY_TWO_STRING_TPL = "xpath://*[@resource-id = 'org.wikipedia:id/page_list_item_title' and @text = '{SUBSTRING1}']/../*[@resource-id = 'org.wikipedia:id/page_list_item_description' and @text = '{SUBSTRING2}']";
                SEARCH_SKIP_BUTTON = "id:org.wikipedia:id/fragment_onboarding_skip_button";
                SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
                SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id = 'org.wikipedia:id/fragment_search_results']//*[@resource-id = 'org.wikipedia:id/page_list_item_title']";
                SEARCH_EMPTY_RESULT_LABEL = "xpath://*[contains(@text, 'No results')]";
    }

    public AndroidSearchPageObject(RemoteWebDriver driver){
        super(driver);
    }

}
