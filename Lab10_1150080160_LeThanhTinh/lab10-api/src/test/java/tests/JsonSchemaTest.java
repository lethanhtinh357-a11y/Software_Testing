package tests;

import base.ApiBaseTest;
import models.CreateUserRequest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class JsonSchemaTest extends ApiBaseTest {

    // Giữ nguyên ApiBaseTest, override lại dùng jsonplaceholder
    @BeforeClass
    @Override
    public void setup() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://jsonplaceholder.typicode.com")
                .setContentType(ContentType.JSON)
                .addHeader("Accept", "application/json")
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();

        responseSpec = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectResponseTime(lessThan(3000L))
                .build();
    }

    // TEST 1: Validate schema danh sách users
    // GET /users → trả về array 10 users
    @Test(description = "Schema validation: GET /users - danh sách user")
    public void testUserListSchema() {
        given(requestSpec)
        .when()
            .get("/users")
        .then()
            .statusCode(200)
            .body(matchesJsonSchemaInClasspath("schemas/user-list-schema.json"))
            .body("size()", equalTo(10))
            .body("[0].id", notNullValue())
            .body("[0].email", containsString("@"));

        System.out.println("✅ TEST 1 PASS: user-list-schema.json hợp lệ");
    }

    // TEST 2: Validate schema 1 user cụ thể
    // GET /users/1 → trả về object có id, name, username, email, address...
    @Test(description = "Schema validation: GET /users/1 - chi tiết user")
    public void testSingleUserSchema() {
        given(requestSpec)
        .when()
            .get("/users/1")
        .then()
            .statusCode(200)
            .body(matchesJsonSchemaInClasspath("schemas/user-schema.json"))
            .body("id", equalTo(1))
            .body("email", containsString("@"))
            .body("name", not(emptyString()));

        System.out.println("✅ TEST 2 PASS: user-schema.json hợp lệ");
    }

    // TEST 3: Validate schema response POST tạo post mới
    // POST /posts → 201, trả về {id, title, body, userId}
    @Test(description = "Schema validation: POST /posts - tạo bài đăng mới")
    public void testCreatePostSchema() {
        given(requestSpec)
            .body("{\"title\":\"Tinh Test\",\"body\":\"QA Engineer\",\"userId\":1}")
        .when()
            .post("/posts")
        .then()
            .statusCode(201)
            .body(matchesJsonSchemaInClasspath("schemas/create-user-schema.json"))
            .body("title", equalTo("Tinh Test"))
            .body("id", notNullValue());

        System.out.println("✅ TEST 3 PASS: create-post-schema.json hợp lệ");
    }

    // TEST 4: DEMO Schema FAIL khi dùng schema sai cố ý
    @Test(description = "DEMO: Schema FAIL khi field không khớp")
    public void testSchemaFailDemo() {
        // ❌ Uncomment để xem FAIL: dùng create-post-schema cho GET /users/1
        // → FAIL vì response /users/1 có field name,username,address không có trong post schema
        /*
        given(requestSpec)
        .when()
            .get("/users/1")
        .then()
            .statusCode(200)
            .body(matchesJsonSchemaInClasspath("schemas/create-post-schema.json"));
        */

        // ✅ Chạy đúng schema → PASS
        given(requestSpec)
        .when()
            .get("/users/1")
        .then()
            .statusCode(200)
            .body(matchesJsonSchemaInClasspath("schemas/user-schema.json"));

        System.out.println("✅ TEST 4 PASS: Demo FAIL → uncomment để thấy lỗi");
    }
}