package lib.ui.Android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidArticlePageObject extends ArticlePageObject {
    static {
            TITLE = "id:pcs-edit-section-title-description";
            FOOTER_ELEMENT = "xpath://android.view.View[@content-desc = 'View article in browser']";
            SAVE_BUTTON = "id:org.wikipedia:id/page_save";
            ADD_TO_LIST_BUTTON = "id:org.wikipedia:id/snackbar_action";
            MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
            OK_BUTTON = "xpath://*[@text = 'OK']";
            CLOSE_ARTICLE_BUTTON = "id:org.wikipedia:id/search_close_btn";
    }
    public AndroidArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }
}
