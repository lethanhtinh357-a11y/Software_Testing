package tests;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import framework.base.BaseTest;
import framework.pages.LoginPage;

import java.util.Map;

public class LoginDDTTest extends BaseTest {

    // ✔ SMOKE
    @Test(dataProvider = "loginData", dataProviderClass = TestDataProvider.class,
          groups = {"smoke"})
    public void loginSmokeTest(Map<String, String> data) {
        runTest(data);
    }

    // ✔ REGRESSION
    @Test(dataProvider = "loginData", dataProviderClass = TestDataProvider.class,
          groups = {"regression"})
    public void loginRegressionTest(Map<String, String> data) {
        runTest(data);
    }

    // 🔥 LOGIC CHUNG
    private void runTest(Map<String, String> data) {

        String username = data.get("username");
        String password = data.get("password");

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(username, password);

        String currentUrl = getDriver().getCurrentUrl();

        // SUCCESS
        if (data.containsKey("expected_url") && !data.get("expected_url").isEmpty()) {
            Assert.assertTrue(currentUrl.contains(data.get("expected_url")),
                    "Không điều hướng đúng URL");
        }

        // FAIL
        else {
            Assert.assertTrue(loginPage.isErrorDisplayed(),
                    "Không hiển thị error");
        }
    }

    // ✔ HIỂN THỊ DESCRIPTION
    @AfterMethod
    public void setTestName(ITestResult result) {
        Object[] params = result.getParameters();

        if (params.length > 0) {
            Map<String, String> data = (Map<String, String>) params[0];
            result.setAttribute("name", data.get("description"));
        }
    }
}