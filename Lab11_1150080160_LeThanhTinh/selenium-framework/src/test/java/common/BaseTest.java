package common;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Optional;

public class BaseTest {

    protected WebDriver driver;

    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser) {

        // lấy browser từ command line (CI ưu tiên)
        String browserFromCmd = System.getProperty("browser");

        if (browserFromCmd != null && !browserFromCmd.isEmpty()) {
            browser = browserFromCmd;
        }

        System.out.println("Browser đang chạy: " + browser);

        driver = DriverFactory.createDriver(browser);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}