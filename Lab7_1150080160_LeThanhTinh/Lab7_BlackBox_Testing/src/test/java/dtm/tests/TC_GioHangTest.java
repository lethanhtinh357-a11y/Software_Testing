package dtm.tests;

import dtm.base.BaseTest;
import dtm.pages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TC_GioHangTest extends BaseTest {

    private InventoryPage inventoryPage;
    private CartPage cartPage;

    @BeforeMethod
    public void login() {
        getDriver().get("https://www.saucedemo.com/");
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.dangNhap("standard_user","secret_sauce");

        inventoryPage = new InventoryPage(getDriver());
        cartPage = new CartPage(getDriver());
    }

    @Test
    public void TC_CART_001_Them1SP() {
        inventoryPage.themNSanPhamDauTien(1);
        Assert.assertEquals(inventoryPage.laySoLuongBadge(),1);
    }

    @Test
    public void TC_CART_002_SortGiaTangDan() {
        inventoryPage.sortSanPham("lohi");
        List<Double> prices = inventoryPage.layDanhSachGiaSanPham();
        List<Double> sorted = prices.stream().sorted().collect(Collectors.toList());
        Assert.assertEquals(prices,sorted);
    }
}