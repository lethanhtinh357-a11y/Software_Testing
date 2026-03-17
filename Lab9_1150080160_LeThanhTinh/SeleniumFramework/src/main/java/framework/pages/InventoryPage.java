package framework.pages;

import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import framework.base.BasePage;

public class InventoryPage extends BasePage {

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "inventory_item")
    private List<WebElement> items;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartLink;

    @FindBy(xpath = "(//button[contains(text(),'Add to cart')])[1]")
    private WebElement firstAddBtn;

    public boolean isLoaded() {
        return items.size() > 0;
    }

    public InventoryPage addFirstItemToCart() {
        waitAndClick(firstAddBtn);
        return this;
    }

    public InventoryPage addItemByName(String name) {
        WebElement item = driver.findElement(
            By.xpath("//div[text()='" + name + "']/ancestor::div[@class='inventory_item']//button")
        );
        waitAndClick(item);
        return this;
    }

    public int getCartItemCount() {
        try {
            return Integer.parseInt(cartBadge.getText());
        } catch (Exception e) {
            return 0;
        }
    }

    public CartPage goToCart() {
        waitAndClick(cartLink);
        return new CartPage(driver);
    }
}