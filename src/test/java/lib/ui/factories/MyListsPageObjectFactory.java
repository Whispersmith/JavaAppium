package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.Android.AndroidMyListPageObject;
import lib.ui.MyListsPageObjects;
import org.openqa.selenium.remote.RemoteWebDriver;


public class MyListsPageObjectFactory {
    public static MyListsPageObjects get(RemoteWebDriver driver){
        if (Platform.getInstance().isAndroid()){
            return new AndroidMyListPageObject(driver);
        }else{
            throw new RuntimeException("Has no platform " + Platform.getInstance());
        }
    }
}
