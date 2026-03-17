package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import framework.base.BaseTest;

public class SmokeTest extends BaseTest {

    @Test
    public void testOpenPage() {
        String url = getDriver().getCurrentUrl();
        System.out.println("Current URL: " + url);
        Assert.assertTrue(url.contains("saucedemo"));
    }

    @Test
    public void testFailExample() {
        System.out.println("Running fail test...");
        Assert.assertTrue(false, "Cố tình fail để chụp màn hình");
    }
}