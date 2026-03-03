package dtm.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class InventoryPage {

    private WebDriver driver;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    private By addButtons = By.cssSelector(".btn_inventory");
    private By badge = By.className("shopping_cart_badge");
    private By cartIcon = By.className("shopping_cart_link");
    private By sortDropdown = By.className("product_sort_container");
    private By priceList = By.className("inventory_item_price");

    public void themNSanPhamDauTien(int n) {
        List<WebElement> buttons = driver.findElements(addButtons);
        for (int i = 0; i < n && i < buttons.size(); i++) {
            buttons.get(i).click();
        }
    }

    public int laySoLuongBadge() {
        try {
            return Integer.parseInt(driver.findElement(badge).getText());
        } catch (Exception e) {
            return 0;
        }
    }

    public void vaoGioHang() {
        driver.findElement(cartIcon).click();
    }

    public void sortSanPham(String value) {
        Select select = new Select(driver.findElement(sortDropdown));
        select.selectByValue(value);
    }

    public List<Double> layDanhSachGiaSanPham() {
        List<WebElement> prices = driver.findElements(priceList);
        List<Double> result = new ArrayList<>();

        for (WebElement p : prices) {
            result.add(Double.parseDouble(p.getText().replace("$","")));
        }

        return result;
    }
}