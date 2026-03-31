package common;

import org.openqa.selenium.WebDriver;

// __Chrome__
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

// __Firefox__
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

// __Grid (Bài 4)__
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class DriverFactory {

    public static WebDriver createDriver(String browser) {

        boolean isCI = System.getenv("CI") != null;

        if (browser == null || browser.isEmpty()) {
            browser = System.getProperty("browser", "chrome");
        }

        System.out.println("Running browser: " + browser);
        System.out.println("Running in CI: " + isCI);

        // ===== BÀI 4: Kiểm tra có chạy trên Grid không =====
        String gridUrl = System.getProperty("grid.url");
        if (gridUrl != null && !gridUrl.isBlank()) {
            System.out.println("[Grid] Kết nối Grid tại: " + gridUrl);
            return createRemoteDriver(browser, gridUrl);
        }
        // ====================================================

        // Chạy local (Bài 1, 2, 3 — giữ nguyên)
        switch (browser.toLowerCase()) {

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions ffOptions = new FirefoxOptions();

                if (isCI) {
                    ffOptions.addArguments("--headless");
                    ffOptions.addArguments("--width=1920");
                    ffOptions.addArguments("--height=1080");
                }

                return new FirefoxDriver(ffOptions);

            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();

                if (isCI) {
                    options.addArguments("--headless=new");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-dev-shm-usage");
                    options.addArguments("--window-size=1920,1080");
                } else {
                    options.addArguments("--start-maximized");
                }

                return new ChromeDriver(options);
        }
    }

    // ===== BÀI 4: Tạo RemoteWebDriver kết nối Selenium Grid =====
    private static WebDriver createRemoteDriver(String browser, String gridUrl) {
        try {
            URL gridEndpoint = new URL(gridUrl + "/wd/hub");

            switch (browser.toLowerCase()) {

                case "firefox":
                    FirefoxOptions ffOptions = new FirefoxOptions();
                    RemoteWebDriver ffDriver = new RemoteWebDriver(gridEndpoint, ffOptions);
                    ffDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    System.out.println("[Grid] Firefox SessionID: " + ffDriver.getSessionId());
                    return ffDriver;

                case "chrome":
                default:
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    RemoteWebDriver chromeDriver = new RemoteWebDriver(gridEndpoint, chromeOptions);
                    chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    System.out.println("[Grid] Chrome SessionID: " + chromeDriver.getSessionId());
                    return chromeDriver;
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException("Grid URL không hợp lệ: " + gridUrl, e);
        }
    }
    // =============================================================
}