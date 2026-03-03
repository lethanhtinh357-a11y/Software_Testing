package dtm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepTwoPage {

    private WebDriver driver;

    public CheckoutStepTwoPage(WebDriver driver) {
        this.driver = driver;
    }

    private By finishBtn = By.id("finish");
    private By itemTotalLabel = By.className("summary_subtotal_label");
    private By taxLabel = By.className("summary_tax_label");
    private By totalLabel = By.className("summary_total_label");

    public void finishCheckout() {
        driver.findElement(finishBtn).click();
    }

    public double layItemTotal() {
        return tachSo(driver.findElement(itemTotalLabel).getText());
    }

    public double layTax() {
        return tachSo(driver.findElement(taxLabel).getText());
    }

    public double layTotal() {
        return tachSo(driver.findElement(totalLabel).getText());
    }

    private double tachSo(String text) {
        return Double.parseDouble(text.replaceAll("[^0-9.]", ""));
    }
}