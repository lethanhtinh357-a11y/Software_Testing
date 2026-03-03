package dtm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage {

    private WebDriver driver;

    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
    }

    private By successHeader = By.className("complete-header");

    public boolean isCheckoutThanhCong() {
        return driver.findElement(successHeader)
                .getText()
                .equalsIgnoreCase("Thank you for your order!");
    }
}