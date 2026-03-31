package common;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            Object testClass = result.getInstance();
            WebDriver driver = ((BaseTest) testClass).driver;

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            File dest = new File("target/screenshots/" + result.getName() + ".png");

            dest.getParentFile().mkdirs();
            Files.copy(src.toPath(), dest.toPath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}