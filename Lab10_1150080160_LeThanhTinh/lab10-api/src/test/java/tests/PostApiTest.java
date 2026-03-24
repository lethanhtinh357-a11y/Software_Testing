package tests;

import base.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostApiTest extends BaseTest {

    @Test(priority = 1, description = "GET /posts - Lấy 100 bài đăng")
    public void testGetAllPosts() {
        given(requestSpec)
            .when().get("/posts")
            .then().statusCode(200)
            .time(lessThan(3000L)) // SLA < 3000ms
            .body("size()", equalTo(100));
    }

    @Test(priority = 2, description = "GET /posts/1 - Lấy bài đăng id=1")
    public void testGetSinglePost() {
        given(requestSpec)
            .when().get("/posts/1")
            .then().statusCode(200)
            .time(lessThan(3000L))
            .body("id", equalTo(1))
            .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("PostSchema.json"));
    }

    @Test(priority = 3, description = "POST /posts - Tạo bài đăng mới")
    public void testCreatePost() {
        Map<String, Object> post = new HashMap<>();
        post.put("title", "Lab 10 - Tịnh");
        post.put("body", "Nội dung bài viết");
        post.put("userId", 1);

        given(requestSpec).body(post)
            .when().post("/posts")
            .then().statusCode(201)
            .time(lessThan(3000L))
            .body("title", equalTo("Lab 10 - Tịnh"));
    }

    @Test(priority = 4, description = "PUT /posts/1 - Cập nhật bài đăng")
    public void testUpdatePost() {
        Map<String, Object> updateData = new HashMap<>();
        updateData.put("title", "Updated Title");

        given(requestSpec).body(updateData)
            .when().put("/posts/1")
            .then().statusCode(200)
            .time(lessThan(3000L))
            .body("title", equalTo("Updated Title"));
    }

    @Test(priority = 5, description = "DELETE /posts/1 - Xóa bài đăng")
    public void testDeletePost() {
        given(requestSpec)
            .when().delete("/posts/1")
            .then().statusCode(200)
            .time(lessThan(3000L));
    }
}