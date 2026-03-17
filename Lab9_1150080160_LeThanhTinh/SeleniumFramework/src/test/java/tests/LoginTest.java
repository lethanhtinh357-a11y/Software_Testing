package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import framework.base.BaseTest;
import framework.pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void loginSuccess() {
        LoginPage lp = new LoginPage(getDriver());
        Assert.assertTrue(lp.login("standard_user", "secret_sauce").isLoaded());
    }

    @Test
    public void loginFail() {
        LoginPage lp = new LoginPage(getDriver());
        lp.loginExpectingFailure("wrong", "wrong");

        Assert.assertTrue(lp.isErrorDisplayed());
    }

    @Test
    public void loginErrorMessage() {
        LoginPage lp = new LoginPage(getDriver());
        lp.loginExpectingFailure("wrong", "wrong");

        Assert.assertTrue(lp.getErrorMessage().contains("Epic sadface"));
    }
}