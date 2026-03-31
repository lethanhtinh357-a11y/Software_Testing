package tests;

import common.BaseTest;
import common.RetryAnalyzer;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigReader;

import java.time.Duration;

public class LoginTest extends BaseTest {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void testLoginSuccess() {

        driver.get("https://www.saucedemo.com/");

        String username = ConfigReader.getUsername();
        String password = ConfigReader.getPassword();

        System.out.println("USER = " + username);
        System.out.println("PASS = " + password);

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // thử tìm inventory (login thành công)
            boolean isDisplayed = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.className("inventory_list"))
            ).isDisplayed();

            Assert.assertTrue(isDisplayed);

        } catch (Exception e) {

            // 👉 nếu fail → in ra lỗi login
            String error = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();

            System.out.println("LOGIN ERROR: " + error);

            Assert.fail("Login failed: " + error);
        }
    }
}