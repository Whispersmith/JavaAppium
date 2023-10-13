package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "pcs-edit-section-title-description";
        FOOTER_ELEMENT = "//android.view.View[@content-desc = 'View article in browser']";
        SAVE_BUTTON = "org.wikipedia:id/page_save";
        ADD_TO_LIST_BUTTON = "org.wikipedia:id/snackbar_action";
        MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input";
        OK_BUTTON = "//*[@text = 'OK']";
    }
    public MWArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }
}
