package lib;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileOutputStream;
import java.time.Duration;
import java.util.Properties;

public class CoreTestCase {
    protected RemoteWebDriver driver;

    @Before
    @Step("Run driver and session")
    public void setUp() throws Exception{
        driver = Platform.getInstance().getDriver();
        this.createAllurePropertyFile();
        this.rotateScreenPortrait();
        this.openWikiWebPageForMobileWeb();

    }

    @After
    @Step("Remove driver and session")
    public void tearDown(){
        driver.quit();
    }

    @Step("Rotate screen to the portrait mode")
    protected void rotateScreenPortrait(){
        if(driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        }else {
            System.out.println("Method rotateScreenPortrait() does nothing for platform "+ Platform.getInstance().getPlatformVar());
        }

    }
    @Step("Rotate screen to the landscape mode")
    protected void rotateScreenLandscape(){
        if(driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        }else {
            System.out.println("Method rotateScreenLandscape() does nothing for platform "+ Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Open web site Wikipedia for Mobile Web (method does not work for Mobile)")
    protected void openWikiWebPageForMobileWeb(){
        if(Platform.getInstance().isMW()){
            driver.get("https://en.m.wikipedia.org/");
        }else {
            System.out.println("Method openWikiWebPageForMobileWeb() does nothing for platform "+ Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Send the app to the background(method does not work for Mobile Web)")
    protected void backgroundApp(int seconds){
        if(driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(Duration.ofSeconds(seconds));
        }else {
            System.out.println("Method backgroundApp() does nothing for platform "+ Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Create file with environment for allure reports")
    private void createAllurePropertyFile(){
        String path = System.getProperty("allure.results.directory");
        try{
            Properties props = new Properties();
            FileOutputStream fos = new FileOutputStream(path + "/environment.properties");
            props.setProperty("Environment", Platform.getInstance().getPlatformVar());
            props.store(fos,"See allure wiki");
            fos.close();
        }catch (Exception e){
            System.err.println("IO problem when writing allure properties file");
            e.printStackTrace();
        }
    }

}
