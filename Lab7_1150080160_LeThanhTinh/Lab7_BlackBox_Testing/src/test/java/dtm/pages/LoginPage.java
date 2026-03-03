package dtm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage {

    private WebDriver driver;

    private By username = By.id("user-name");
    private By password = By.id("password");
    private By loginBtn = By.id("login-button");
    private By errorMsg = By.cssSelector("div[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void nhapUsername(String user) {
        WebElement element = driver.findElement(username);
        element.clear();
        if (user != null) {
            element.sendKeys(user);
        }
    }

    public void nhapPassword(String pass) {
        WebElement element = driver.findElement(password);
        element.clear();
        if (pass != null) {
            element.sendKeys(pass);
        }
    }

    public void clickDangNhap() {
        driver.findElement(loginBtn).click();
    }

    public void dangNhap(String user, String pass) {
        nhapUsername(user);
        nhapPassword(pass);
        clickDangNhap();
    }

    public String layThongBaoLoi() {
        List<WebElement> errors = driver.findElements(errorMsg);
        if (errors.size() > 0) {
            return errors.get(0).getText();
        }
        return null;
    }

    public boolean isDangOTrangSanPham() {
        return driver.getCurrentUrl().contains("inventory");
    }
}