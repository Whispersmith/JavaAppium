package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import lib.Platform;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.smartcardio.ATR;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {
    protected RemoteWebDriver driver;

    public MainPageObject(RemoteWebDriver driver){
        this.driver = driver;
    }

    @Step("Find element and swipe it up")
    public void swipeUp(int timeOfSwipe){
        if (driver instanceof AppiumDriver) {
            TouchAction action = new TouchAction((AppiumDriver)driver);
            Dimension size = driver.manage().window().getSize();
            int x = size.width / 2;
            int start_y = (int) (size.height * 0.8);
            int end_y = (int) (size.height * 0.2);

            action
                    .press(PointOption.point(x, start_y))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe)))
                    .moveTo(PointOption.point(x, end_y))
                    .release()
                    .perform();
        }else{
            System.out.println("Method swipeUp() does nothing for platform "+ Platform.getInstance().getPlatformVar());
        }

    }
    public void swipeUpQuick(){
        swipeUp(200);
    }

    @Step("Find element and swipe it up quick")
    public void swipeUpToFindElement(String locator, String error_message, int max_swipes){

        int already_swiped = 0;
//        int size = driver.findElements(by).size();
//        System.out.println(size);

        while(!this.isElementLocatedOnTheScreen(locator)){
            if(already_swiped > max_swipes){
                waitForElementPresent(locator,"Cannot find element by swiping up.\n"+ error_message, 0);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }
    @Step("Swipe element to the left")
    public void swipeElementToLeft(String locator, String error_message){
        if(driver instanceof AppiumDriver) {
            WebElement element = waitForElementPresent(locator, error_message, 10);

            int left_x = element.getLocation().getX();
            int right_x = left_x + element.getSize().getWidth();

            int upper_y = element.getLocation().getY();
            int lower_y = upper_y + element.getSize().getHeight();
            int middle_y = (upper_y + lower_y) / 2;

            TouchAction action = new TouchAction((AppiumDriver)driver);
            action
                    .press(PointOption.point(right_x, middle_y))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                    .moveTo(PointOption.point(left_x, middle_y))
                    .release()
                    .perform();
        }else {
            System.out.println("Method swipeElementToLeft() does nothing for platform "+ Platform.getInstance().getPlatformVar());
        }

    }

    @Step("Wait that element is present ")
    public WebElement waitForElementPresent(String locator, String error_message, long timeOutInSecond){
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSecond);
        wait.withMessage(error_message + "\n");

        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    @Step("Wait that element is present ")
    public WebElement waitForElementPresent(String locator, String error_message) {
        return waitForElementPresent(locator, error_message, 5);
    }
    @Step("Wait that element is present and click ")
    public WebElement waitForElementAndClick(String locator, String error_message, long timeOutInSecond) {
        WebElement element = waitForElementPresent(locator, error_message, timeOutInSecond);
        element.click();
        return element;
    }
    @Step("Wait that element is present and send value ")
    public WebElement waitForElementAndSendKeys(String locator, String value, String error_message, long timeOutInSecond) {
        WebElement element = waitForElementPresent(locator, error_message, timeOutInSecond);
        element.sendKeys(value);
        return element;
    }
    @Step("Wait that element is not present ")
    public boolean waitForElementNotPresent(String locator, String error_message, long timeOutInSecond){
    By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSecond);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }
    public WebElement waitForElementAndClear(String locator, String error_message, long timeOutInSecond){
        WebElement element = waitForElementPresent(locator, error_message, timeOutInSecond);
        element.clear();
        return element;
    }
    @Step("Get amount of elements by locator")
    public int getAmountOfElements(String locator){
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }

    public boolean isElementPresent(String locator){
        return getAmountOfElements(locator)>0;
    }

    @Step("Assert if element is still present ")
    public void assertElementsNotPresent(String locator, String error_message){
        int amount_of_elements = getAmountOfElements(locator);
        if (amount_of_elements >0){
            String default_message = "'An element '" +locator + "'supposed to not present'";
            throw new AssertionError(default_message + " " + error_message);

        }
    }
    @Step("Assert if element is not present ")
    public void assertElementPresent(String locator, String error_message){
        int amount_of_elements = getAmountOfElements(locator);
        if(amount_of_elements == 0){
            String default_message = "'An element '" + locator + "'supposed to not present'";
            throw new AssertionError(default_message + " " + error_message);
        }
    }
    public String waitForElementAndAttribute(String locator, String attribute, String error_message, long timeOutInSeconds){
        WebElement element = waitForElementPresent(locator,error_message,timeOutInSeconds);
        return element.getAttribute(attribute);
    }

    @Step("Checking element is visible")
    public boolean isElementLocatedOnTheScreen(String locator){
        int element_location_by_y = this.waitForElementPresent(locator,"Cannot find element by locator", 5).getLocation().getY();
        if(Platform.getInstance().isMW()){
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            Object js_result = JSExecutor.executeScript("return window.pageYOffset");
            element_location_by_y -= Integer.parseInt(js_result.toString());
        }
        int screen_size_y = driver.manage().window().getSize().getHeight();
        return element_location_by_y < screen_size_y;
    }
    /*
    public void swipeUpTillElementAppear(String locator, String error_message, int max_swipes){
        int already_swiped = 0;

        while (this.isElementLocatedOnTheScreen(locator)){
            if(already_swiped>max_swipes){
                Assert.assertTrue(error_message, this.isElementLocatedOnTheScreen(locator));
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }
*/
    @Step("Get locator from string ")
    private By getLocatorByString(String locator_with_type){
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];
        if(by_type.equals("id")){
            return By.id(locator);
        }else if(by_type.equals("css")){
            return By.cssSelector(locator);
        }else if(by_type.equals("xpath")){
            return By.xpath(locator);
        }else{
            throw new IllegalArgumentException("Cannot get type of locator: "+ exploded_locator);
        }
    }

    @Step("Scroll web page(method does nothing for android) ")
    public void scrollWebPageApp(){
        if(Platform.getInstance().isMW()){
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            JSExecutor.executeScript("window.scrollBy(0,250)");
        }else{
            System.out.println("Method scrollWebPageApp() does nothing for "+ Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Scroll web page before element will not visible(method does nothing for android) ")
    public void scrollWebpageTillElementNotVisible(String locator, String error_message, int max_swipes){
        int already_swiped = 0;
        WebElement element  = this.waitForElementPresent(locator,error_message);
        while (!this.isElementLocatedOnTheScreen(locator)){
            scrollWebPageApp();
            ++already_swiped;
            if(already_swiped>max_swipes){
                Assert.assertTrue(error_message, element.isDisplayed());
            }
        }
    }
    @Step("Try to click element with few attempts (method does nothing for android)")
    public void tryToClickElementWithFewAttempts(String locator, String error_message, int max_attempts){
        int current_attempts = 0;
        boolean need_new_attempt = true;

        while (need_new_attempt){
            try{
                this.waitForElementAndClick(locator, error_message,1);
                need_new_attempt = false;
            }catch (Exception e){
                if(current_attempts>max_attempts){
                    this.waitForElementAndClick(locator, error_message,1);
                }
            }
            ++current_attempts;
        }
    }

    @Step("Take screenshot with define name")
    public String takeScreenshot(String name){
        TakesScreenshot takesScreenshot = (TakesScreenshot)this.driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir")+ "/" + name +"_screenshot.png";
        try {
            FileUtils.copyFile(source, new File(path));
            System.out.println("The screenshot was taken" +path);
        }catch (Exception e){
            System.out.println("Cannot take the screenshot. Error: " + e.getMessage());
        }
        return path;
    }

    @Step("Adding screenshot to the method when need it ")
    @Attachment
    public static byte[] screenshot(String path){
        byte[] bytes = new byte[0];
        try{
            bytes = Files.readAllBytes(Paths.get(path));
        }catch (IOException e){
            System.out.println("Cannot get bytes from screenshot. Error "+e.getMessage());
        }
        return bytes;
    }



}
