package framework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import framework.base.BasePage;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "user-name")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement loginBtn;

    @FindBy(css = ".error-message-container")
    private WebElement errorMsg;

    // ✅ Fluent Interface
    public InventoryPage login(String user, String pass) {
        waitAndType(username, user);
        waitAndType(password, pass);
        waitAndClick(loginBtn);
        return new InventoryPage(driver);
    }

    public LoginPage loginExpectingFailure(String user, String pass) {
        waitAndType(username, user);
        waitAndType(password, pass);
        waitAndClick(loginBtn);
        return this;
    }

    public String getErrorMessage() {
        return getText(errorMsg);
    }

    public boolean isErrorDisplayed() {
        return errorMsg.isDisplayed();
    }
}