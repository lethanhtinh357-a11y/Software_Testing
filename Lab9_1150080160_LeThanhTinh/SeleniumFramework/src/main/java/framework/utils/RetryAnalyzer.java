package framework.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import framework.config.ConfigReader;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int count = 0;
    private int maxRetry = ConfigReader.getInstance().getRetryCount();

    @Override
    public boolean retry(ITestResult result) {

        if (count < maxRetry) {
            count++;
            System.out.println("🔁 Retry lần " + count + "/" + maxRetry);
            return true;
        }

        return false;
    }
}