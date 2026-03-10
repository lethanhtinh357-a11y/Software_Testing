package lab8;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    private void login() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    @Test(groups = "smoke")
    public void testAddToCart() {
        System.out.println("=== CartTest chay tren thread: " 
            + Thread.currentThread().getId());

        login();
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.className("shopping_cart_link")).click();

        Assert.assertTrue(driver.getPageSource().contains("Sauce Labs Backpack"),
            "Product not in cart!");
    }

    @Test(groups = "regression")
    public void testRemoveCart() {
        login();
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("remove-sauce-labs-backpack")).click();

        Assert.assertTrue(
            driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).isDisplayed(),
            "Remove failed!");
    }
}