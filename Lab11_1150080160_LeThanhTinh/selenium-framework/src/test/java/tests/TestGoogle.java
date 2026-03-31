package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import common.BaseTest;

public class TestGoogle extends BaseTest {

    @Test
    public void testGoogle() {
        driver.get("https://www.google.com");
        Assert.assertTrue(driver.getTitle().contains("Google"));
    }
}