package framework.base;

import java.io.File;
import java.nio.file.Files;
import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import framework.config.ConfigReader;

public abstract class BaseTest {

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    protected WebDriver getDriver() {
        return tlDriver.get();
    }

    @Parameters({"browser", "env"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser,
                      @Optional("dev") String env) {

        // 🔥 BẮT BUỘC (theo đề)
        System.setProperty("env", env);

        // 🔥 LOAD CONFIG
        ConfigReader config = ConfigReader.getInstance();

        String baseUrl = config.getBaseUrl();
        int wait = config.getExplicitWait();

        System.out.println("URL: " + baseUrl);
        System.out.println("Explicit Wait: " + wait);

        // 🔥 DRIVER
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        // ❌ KHÔNG HARDCODE
        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(wait));

        driver.get(baseUrl);

        tlDriver.set(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            try {
                File dir = new File("target/screenshots/");
                if (!dir.exists()) dir.mkdirs();

                String fileName = result.getName() + "_" + System.currentTimeMillis() + ".png";

                File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
                File dest = new File(dir, fileName);

                Files.copy(src.toPath(), dest.toPath());

                System.out.println("📸 Screenshot saved: " + dest.getAbsolutePath());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (getDriver() != null) {
            getDriver().quit();
            tlDriver.remove();
        }
    }
}