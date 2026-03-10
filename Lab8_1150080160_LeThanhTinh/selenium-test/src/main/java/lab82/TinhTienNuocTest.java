package lab82;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TinhTienNuocTest {

 @Test
 public void TC01_invalidM3(){
  Assert.assertEquals(TinhTienNuoc.tinhTienNuoc(-5,"dan_cu"),0);
 }

 @Test
 public void TC02_hoNgheo(){
  Assert.assertEquals(TinhTienNuoc.tinhTienNuoc(5,"ho_ngheo"),25000);
 }

 @Test
 public void TC03_danCu1(){
  Assert.assertEquals(TinhTienNuoc.tinhTienNuoc(8,"dan_cu"),60000);
 }

 @Test
 public void TC04_danCu2(){
  Assert.assertEquals(TinhTienNuoc.tinhTienNuoc(15,"dan_cu"),148500);
 }

 @Test
 public void TC05_danCu3(){
  Assert.assertEquals(TinhTienNuoc.tinhTienNuoc(25,"dan_cu"),285000);
 }

 @Test
 public void TC06_kinhDoanh(){
  Assert.assertEquals(TinhTienNuoc.tinhTienNuoc(10,"kinh_doanh"),220000);
 }

}