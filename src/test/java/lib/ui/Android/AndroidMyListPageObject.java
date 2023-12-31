package lib.ui.Android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MainPageObject;
import lib.ui.MyListsPageObjects;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMyListPageObject extends MyListsPageObjects {
    static {
        NOT_NOW_BUTTON = "id:org.wikipedia:id/negativeButton";
        FOLDER_BY_NAME_TPL = "xpath://*[@resource-id = 'org.wikipedia:id/item_title_container']//*[@text = '{FOLDER_NAME}']";
        ARTICLE_TITLE_TPL = "xpath://*[@text = '{TITLE}']";
    }
    public AndroidMyListPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
