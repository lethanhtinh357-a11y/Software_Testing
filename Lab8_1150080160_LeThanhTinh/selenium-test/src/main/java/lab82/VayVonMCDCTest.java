package lab82;

import org.testng.Assert;
import org.testng.annotations.Test;


public class VayVonMCDCTest {

    @Test(description = "TC01 - Tất cả điều kiện đều đúng")
    public void testAllTrue() {

        boolean result =
                VayVon.duDieuKienVay(25,12000000,true,750);

        Assert.assertTrue(result);
    }


    @Test(description = "TC02 - Tuổi < 22")
    public void testInvalidAge() {

        boolean result =
                VayVon.duDieuKienVay(20,12000000,true,750);

        Assert.assertFalse(result);
    }


    @Test(description = "TC03 - Thu nhập < 10 triệu")
    public void testLowIncome() {

        boolean result =
                VayVon.duDieuKienVay(25,8000000,true,750);

        Assert.assertFalse(result);
    }


    @Test(description = "TC04 - Không đủ điểm tín dụng nhưng có tài sản")
    public void testCollateralCase() {

        boolean result =
                VayVon.duDieuKienVay(25,12000000,true,650);

        Assert.assertTrue(result);
    }


    @Test(description = "TC05 - Không tài sản và điểm tín dụng thấp")
    public void testCreditScoreFail() {

        boolean result =
                VayVon.duDieuKienVay(25,12000000,false,650);

        Assert.assertFalse(result);
    }

}