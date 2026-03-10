package lab82;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TinhPhiShipTest {

 @Test(expectedExceptions = IllegalArgumentException.class)
 public void TC01_invalidWeight(){
  TinhPhiShip.tinhPhiShip(-1,"noi_thanh",false);
 }

 @Test
 public void TC02_noiThanh_basic(){
  Assert.assertEquals(TinhPhiShip.tinhPhiShip(3,"noi_thanh",false),15000);
 }

 @Test
 public void TC03_noiThanh_over5(){
  Assert.assertEquals(TinhPhiShip.tinhPhiShip(7,"noi_thanh",false),19000);
 }

 @Test
 public void TC04_ngoaiThanh_basic(){
  Assert.assertEquals(TinhPhiShip.tinhPhiShip(2,"ngoai_thanh",false),25000);
 }

 @Test
 public void TC05_ngoaiThanh_over3(){
  Assert.assertEquals(TinhPhiShip.tinhPhiShip(6,"ngoai_thanh",false),34000);
 }

 @Test
 public void TC06_tinhKhac_basic(){
  Assert.assertEquals(TinhPhiShip.tinhPhiShip(1,"tinh_khac",false),50000);
 }

 @Test
 public void TC07_tinhKhac_over2(){
  Assert.assertEquals(TinhPhiShip.tinhPhiShip(4,"tinh_khac",false),60000);
 }

 @Test
 public void TC08_member_discount(){
  Assert.assertEquals(TinhPhiShip.tinhPhiShip(4,"noi_thanh",true),13500);
 }

}
