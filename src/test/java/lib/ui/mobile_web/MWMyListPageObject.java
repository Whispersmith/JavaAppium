package lib.ui.mobile_web;

import lib.ui.MyListsPageObjects;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListPageObject extends MyListsPageObjects {
    static {
            ARTICLE_TITLE_TPL = "xpath://ul[contains(@class, 'watchlist')]//h3[contains(text(),'{TITLE}')]";
            REMOVE_FROM_SAVE_BUTTON = "xpath://ul[contains(@class, 'watchlist')]//h3[contains(text(),'{TITLE}')]/../../div[contains(@class,'watched')]";
        }
        public MWMyListPageObject(RemoteWebDriver driver){
            super(driver);
        }
}
