package dtm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    private WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    private By checkoutBtn = By.id("checkout");

    public void checkout() {
        driver.findElement(checkoutBtn).click();
    }
}