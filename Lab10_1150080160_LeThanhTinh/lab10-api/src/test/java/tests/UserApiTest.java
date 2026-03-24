package tests;

import base.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserApiTest extends BaseTest {

    @Test(description = "GET /users -> 10 users")
    public void testGetListUsers() {
        given(requestSpec)
            .when().get("/users")
            .then().statusCode(200)
            .time(lessThan(3000L))
            .body("size()", equalTo(10));
    }

    @Test(description = "GET /users/1 -> Validate Nested Object Address Schema")
    public void testUserAddressSchema() {
        given(requestSpec)
            .when().get("/users/1")
            .then().statusCode(200)
            .time(lessThan(3000L))
            // Kiểm tra schema cho đối tượng address lồng nhau
            .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("UserSchema.json"))
            .body("address.city", notNullValue())
            .body("address.zipcode", notNullValue());
    }
}