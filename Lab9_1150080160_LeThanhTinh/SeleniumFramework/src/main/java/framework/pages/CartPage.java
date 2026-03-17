package framework.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import framework.base.BasePage;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(xpath = "(//button[text()='Remove'])[1]")
    private WebElement removeFirstBtn;

    @FindBy(id = "checkout")
    private WebElement checkoutBtn;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> itemNames;

    public int getItemCount() {
        return cartItems.size(); // ✔ nếu rỗng → = 0
    }

    public CartPage removeFirstItem() {
        if (cartItems.size() > 0) {
            waitAndClick(removeFirstBtn);
        }
        return this;
    }

    public CheckoutPage goToCheckout() {
        waitAndClick(checkoutBtn);
        return new CheckoutPage(driver);
    }

    public List<String> getItemNames() {
        List<String> names = new ArrayList<>();
        for (WebElement e : itemNames) {
            names.add(e.getText());
        }
        return names;
    }
}