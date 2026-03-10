package lab8;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(groups = "smoke")
    public void testLoginSuccess() {
        System.out.println("=== LoginTest chay tren thread: " 
            + Thread.currentThread().getId());

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"),
            "Login failed!");
    }

    @Test(groups = "regression")
    public void testLoginFail() {
        driver.findElement(By.id("user-name")).sendKeys("wrong_user");
        driver.findElement(By.id("password")).sendKeys("123");
        driver.findElement(By.id("login-button")).click();

        Assert.assertTrue(driver.getPageSource().contains("Epic sadface"),
            "Error message not shown!");
    }
}