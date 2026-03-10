package lab82;

import org.testng.Assert;
import org.testng.annotations.Test;

public class XepLoaiTest {

    @Test
    public void TC01_invalidScore(){

        String result = XepLoai.xepLoai(-1,false);
        Assert.assertEquals(result,"Diem khong hop le");

    }

    @Test
    public void TC02_gioi(){

        String result = XepLoai.xepLoai(9,false);
        Assert.assertEquals(result,"Gioi");

    }

    @Test
    public void TC03_kha(){

        String result = XepLoai.xepLoai(7.5,false);
        Assert.assertEquals(result,"Kha");

    }

    @Test
    public void TC04_trungbinh(){

        String result = XepLoai.xepLoai(6,false);
        Assert.assertEquals(result,"Trung Binh");

    }

    @Test
    public void TC05_thilai(){

        String result = XepLoai.xepLoai(4,true);
        Assert.assertEquals(result,"Thi lai");

    }

    @Test
    public void TC06_yeu(){

        String result = XepLoai.xepLoai(4,false);
        Assert.assertEquals(result,"Yeu - Hoc lai");

    }

}