package lab82;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PhiShipBasisPathTest {

    @Test(description = "Path 1: Trọng lượng không hợp lệ")
    public void testPath1_InvalidWeight() {

        Assert.assertThrows(
                IllegalArgumentException.class,
                () -> PhiShip.tinhPhiShip(-1, "noi_thanh", false)
        );
    }

    @Test(description = "Path 2: Nội thành, <=5kg, không member")
    public void testPath2_NoiThanhNheKhongMember() {

        double expected = 15000;

        Assert.assertEquals(
                PhiShip.tinhPhiShip(3, "noi_thanh", false),
                expected,
                0.01
        );
    }

    @Test(description = "Path 3: Nội thành, >5kg, không member")
    public void testPath3_NoiThanhNangKhongMember() {

        double expected = 19000; //15000 + (7-5)*2000

        Assert.assertEquals(
                PhiShip.tinhPhiShip(7, "noi_thanh", false),
                expected,
                0.01
        );
    }

    @Test(description = "Path 4: Ngoại thành, <=3kg, không member")
    public void testPath4_NgoaiThanhNhe() {

        double expected = 25000;

        Assert.assertEquals(
                PhiShip.tinhPhiShip(2, "ngoai_thanh", false),
                expected,
                0.01
        );
    }

    @Test(description = "Path 5: Ngoại thành, >3kg")
    public void testPath5_NgoaiThanhNang() {

        double expected = 34000; //25000 + (6-3)*3000

        Assert.assertEquals(
                PhiShip.tinhPhiShip(6, "ngoai_thanh", false),
                expected,
                0.01
        );
    }

    @Test(description = "Path 6: Tỉnh khác, <=2kg")
    public void testPath6_TinhKhacNhe() {

        double expected = 50000;

        Assert.assertEquals(
                PhiShip.tinhPhiShip(1, "tinh_khac", false),
                expected,
                0.01
        );
    }

    @Test(description = "Path 7: Tỉnh khác, >2kg")
    public void testPath7_TinhKhacNang() {

        double expected = 60000; //50000 + (4-2)*5000

        Assert.assertEquals(
                PhiShip.tinhPhiShip(4, "tinh_khac", false),
                expected,
                0.01
        );
    }

    @Test(description = "Path 8: Nội thành + Member (giảm 10%)")
    public void testPath8_MemberDiscount() {

        double expected = 13500; //15000 * 0.9

        Assert.assertEquals(
                PhiShip.tinhPhiShip(3, "noi_thanh", true),
                expected,
                0.01
        );
    }
}