package dtm.data;

import org.testng.annotations.DataProvider;

public class DangNhapData {

    @DataProvider(name = "du_lieu_dang_nhap")
    public Object[][] getData() {

        return new Object[][]{

                // ===== Tài khoản hợp lệ =====
                {"standard_user", "secret_sauce", "THANH_CONG", "Tài khoản hợp lệ"},
                {"locked_out_user", "secret_sauce", "BI_KHOA", "Tài khoản bị khóa"},
                {"problem_user", "secret_sauce", "THANH_CONG", "UI lỗi"},
                {"performance_glitch_user", "secret_sauce", "THANH_CONG", "Tải chậm"},
                {"error_user", "secret_sauce", "THANH_CONG", "Lỗi chức năng"},

                // ===== Sai thông tin =====
                {"abc", "123", "SAI_THONG_TIN", "Tài khoản không tồn tại"},

                // ===== Trường trống =====
                {"", "secret_sauce", "TRUONG_TRONG", "Username trống"},
                {"standard_user", "", "TRUONG_TRONG", "Password trống"},
                {"", "", "TRUONG_TRONG", "Cả hai trống"},

                // ===== Ký tự đặc biệt =====
                {"user!@#", "123", "SAI_THONG_TIN", "Ký tự đặc biệt"},

                // ===== Khoảng trắng =====
                {" standard_user ", "secret_sauce", "SAI_THONG_TIN", "Có khoảng trắng"},

                // ===== Null =====
                {null, null, "NULL_DATA", "Dữ liệu null"}
        };
    }
}