package lab8;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        try {
            System.out.println(">>> B1: Bat dau setup");
            
            WebDriverManager.edgedriver().setup();
            System.out.println(">>> B2: WebDriverManager OK");

            EdgeOptions options = new EdgeOptions();
            options.addArguments("--remote-allow-origins=*");

            driver = new EdgeDriver(options);
            System.out.println(">>> B3: EdgeDriver OK - " + driver);

            driver.manage().window().maximize();
            driver.get("https://www.saucedemo.com/");
            System.out.println(">>> B4: Mo trang OK");

        } catch (Exception e) {
            System.out.println(">>> LOI O SETUP: " + e.getMessage());
            e.printStackTrace();
            // Throw lại để TestNG biết setUp thất bại
            throw new RuntimeException("setUp failed!", e);
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    // Chạy riêng để test driver có hoạt động không
    public static void main(String[] args) {
        BaseTest b = new BaseTest();
        b.setUp();
        System.out.println("Driver = " + b.driver);
        b.tearDown();
    }
}