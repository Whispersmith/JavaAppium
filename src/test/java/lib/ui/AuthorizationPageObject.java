package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject{
    public static final String
            LOGIN_BUTTON = "xpath://body/div[5]/div[2]/a/span[text() = 'Log in']",
            LOGIN_INPUT= "css:input[name='wpName']",
            PASSWORD_INPUT="css:input[name='wpPassword']",
            SUBMIT_BUTTON ="css:button#wpLoginAttempt";

    public AuthorizationPageObject(RemoteWebDriver driver){
        super(driver);
    }

    public void clickAuthButton(){
        this.waitForElementPresent(LOGIN_BUTTON, "Cannot find auth button", 5);
        this.waitForElementAndClick(LOGIN_BUTTON, "Cannot find and click the auth button", 5);

    }

    public void enterLoginData(String login, String password){
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, "Cannot find input and put login data into it", 5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "Cannot find input and and put password data into it", 5);
    }

    public void submitForm(){
        this.waitForElementAndClick(SUBMIT_BUTTON, "Cannot click to submit button", 5);

    }
}
