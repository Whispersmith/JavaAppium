package lib.ui.Android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidNavigationUI extends NavigationUI {

    static {
        NAVIGATE_UP_BUTTON = "xpath://android.widget.ImageButton[@content-desc= 'Navigate up']";
                SAVED_ARTICLES = "xpath://android.widget.FrameLayout[@content-desc= 'Saved']";
    }
    public AndroidNavigationUI(RemoteWebDriver driver){
        super(driver);
    }
}
