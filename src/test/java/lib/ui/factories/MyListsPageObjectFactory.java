package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.Android.AndroidMyListPageObject;
import lib.ui.MyListsPageObjects;
import lib.ui.mobile_web.MWMyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;


public class MyListsPageObjectFactory {
    public static MyListsPageObjects get(RemoteWebDriver driver){
        if (Platform.getInstance().isAndroid()){
            return new AndroidMyListPageObject(driver);
        }else{
            return new MWMyListPageObject(driver);
        }
    }
}
