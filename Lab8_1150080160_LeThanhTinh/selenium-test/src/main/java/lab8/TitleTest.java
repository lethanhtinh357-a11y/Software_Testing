package lab8;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TitleTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.saucedemo.com/");
    }

    // Test tiêu đề trang
    @Test
    public void testTitle() {

        String expectedTitle = "Swag Labs";
        String actualTitle = driver.getTitle();

        Assert.assertEquals(actualTitle, expectedTitle,
                "Tiêu đề trang không đúng");
    }

    // Test URL trang
    @Test
    public void testURL() {

        String currentURL = driver.getCurrentUrl();

        Assert.assertTrue(currentURL.contains("saucedemo"),
                "URL không đúng");
    }

    // Test source HTML
    @Test
    public void testPageSource() {

        String pageSource = driver.getPageSource();

        Assert.assertTrue(pageSource.contains("login-button"),
                "Không tìm thấy form đăng nhập");
    }

    // Test form đăng nhập hiển thị
    @Test
    public void testLoginFormDisplayed() {

        boolean username = driver.findElement(By.id("user-name")).isDisplayed();
        boolean password = driver.findElement(By.id("password")).isDisplayed();
        boolean loginBtn = driver.findElement(By.id("login-button")).isDisplayed();

        Assert.assertTrue(username && password && loginBtn,
                "Form đăng nhập không hiển thị");
    }

    @AfterMethod
    public void teardown() {

        driver.quit();
    }
}