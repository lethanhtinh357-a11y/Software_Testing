package dtm.tests;

import dtm.base.BaseTest;
import dtm.data.DangNhapData;
import dtm.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_DangNhapTest extends BaseTest {

    @Test(
            dataProvider = "du_lieu_dang_nhap",
            dataProviderClass = DangNhapData.class
    )
    public void kiemThuDangNhap(String username,
                                String password,
                                String ketQuaMongDoi,
                                String moTa) {

        getDriver().get("https://www.saucedemo.com/");

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.dangNhap(username, password);

        switch (ketQuaMongDoi) {

            case "THANH_CONG":
                Assert.assertTrue(
                        loginPage.isDangOTrangSanPham(),
                        "FAIL: " + moTa
                );
                break;

            case "BI_KHOA":
                Assert.assertTrue(
                        loginPage.layThongBaoLoi() != null &&
                        loginPage.layThongBaoLoi().contains("locked out"),
                        "FAIL: " + moTa
                );
                break;

            default:
                Assert.assertFalse(
                        loginPage.isDangOTrangSanPham(),
                        "FAIL: " + moTa
                );
                break;
        }
    }
}