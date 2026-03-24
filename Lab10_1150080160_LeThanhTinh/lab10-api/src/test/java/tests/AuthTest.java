package tests;

import io.restassured.response.ValidatableResponse;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import base.BaseTest;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class AuthTest extends BaseTest {

    // ── PHẦN A: BÀI 4 - KIỂM THỬ THỦ CÔNG (5 TEST CASES) ──────────
    // Mục tiêu: Kiểm tra các phương thức HTTP cơ bản (GET, POST, PUT, DELETE)

    @Test(priority = 1, description = "TC01: Tạo bài viết mới thành công")
    public void testCreatePostSuccess() {
        Map<String, Object> body = new HashMap<>();
        body.put("title", "Học Testing");
        body.put("body",  "Lê Thanh Tịnh");
        body.put("userId", 1);

        given(requestSpec)
            .body(body)
            .when()
            .post("/posts")
            .then()
            .statusCode(201)
            .body("title", equalTo("Học Testing"))
            .body("id", notNullValue());
    }

    @Test(priority = 2, description = "TC02: Lấy thông tin bài viết theo ID")
    public void testGetPostSuccess() {
        given(requestSpec)
            .when()
            .get("/posts/1")
            .then()
            .statusCode(200)
            .body("id", equalTo(1))
            .body("userId", notNullValue());
    }

    @Test(priority = 3, description = "TC03: Truy cập bài viết không tồn tại")
    public void testGetPostNotFound() {
        given(requestSpec)
            .when()
            .get("/posts/9999")
            .then()
            .statusCode(404);
    }

    @Test(priority = 4, description = "TC04: Cập nhật nội dung bài viết")
    public void testUpdatePostSuccess() {
        Map<String, Object> body = new HashMap<>();
        body.put("title", "Update bởi Lê Thanh Tịnh");

        given(requestSpec)
            .body(body)
            .when()
            .put("/posts/1")
            .then()
            .statusCode(200)
            .body("title", containsString("Lê Thanh Tịnh"));
    }

    @Test(priority = 5, description = "TC05: Xóa bài viết thành công")
    public void testDeletePostSuccess() {
        given(requestSpec)
            .when()
            .delete("/posts/1")
            .then()
            .statusCode(200);
    }

    // ── PHẦN B: BÀI 5 - DATA-DRIVEN TESTING (5 TEST CASES) ────────
    // Mục tiêu: Sử dụng bộ dữ liệu để kiểm tra tính năng tạo Post với nhiều đầu vào khác nhau

    @DataProvider(name = "postScenarios")
    public Object[][] postScenarios() {
        return new Object[][] {
            // Title                Body                UserId    Status mong đợi
            {"Bài viết số 1",      "Nội dung 1",       101,      201},
            {"Bài viết số 2",      "Nội dung 2",       102,      201},
            {"Tiêu đề có dấu",     "Nội dung trống",   103,      201},
            {"",                   "Tiêu đề trống",    104,      201},
            {"!@#$%^&*",           "Ký tự đặc biệt",   105,      201}
        };
    }

    @Test(dataProvider = "postScenarios", priority = 6, 
          description = "TC06-10: Kiểm thử Data-driven tạo bài viết")
    public void testCreatePostDataDriven(String title, String content, int uId, int expStatus) {
        Map<String, Object> body = new HashMap<>();
        body.put("title",  title);
        body.put("body",   content);
        body.put("userId", uId);

        given(requestSpec)
            .body(body)
            .when()
            .post("/posts")
            .then()
            .statusCode(expStatus)
            .body("userId", equalTo(uId))
            .body("id", notNullValue());
    }
}