package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import framework.base.BaseTest;
import framework.pages.LoginPage;

public class CartTest extends BaseTest {

    @Test
    public void addItemToCart() {
        int count = new LoginPage(getDriver())
                .login("standard_user", "secret_sauce")
                .addFirstItemToCart()
                .getCartItemCount();

        Assert.assertEquals(count, 1);
    }

    @Test
    public void removeItem() {
        int count = new LoginPage(getDriver())
                .login("standard_user", "secret_sauce")
                .addFirstItemToCart()
                .goToCart()
                .removeFirstItem()
                .getItemCount();

        Assert.assertEquals(count, 0);
    }

    @Test
    public void emptyCart() {
        int count = new LoginPage(getDriver())
                .login("standard_user", "secret_sauce")
                .goToCart()
                .getItemCount();

        Assert.assertEquals(count, 0);
    }
}