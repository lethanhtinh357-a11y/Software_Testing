package tests;

import framework.base.BaseTest;
import framework.model.UserData;
import framework.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserLoginTest extends BaseTest {

    @Test(dataProvider = "jsonUsers", dataProviderClass = TestDataProvider.class)
    public void loginWithJson(UserData user) {

        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.login(user.getUsername(), user.getPassword());

        String currentUrl = getDriver().getCurrentUrl();

        if (user.isExpectSuccess()) {
            Assert.assertTrue(currentUrl.contains("inventory"),
                    "Đáng lẽ login thành công");
        } else {
            Assert.assertTrue(loginPage.isErrorDisplayed(),
                    "Đáng lẽ phải báo lỗi");
        }
    }
}