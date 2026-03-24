package tests;

import base.ApiBaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserGetTest extends ApiBaseTest {

    @Test(description = "GET all users")
    public void testGetAllUsers() {
        given(requestSpec)
        .when()
            .get("/users")
        .then()
            .spec(responseSpec)
            .statusCode(200)
            .body("size()", greaterThan(0))
            .body("[0].id", notNullValue())
            .body("[0].email", containsString("@"));
    }

    @Test(description = "GET user id=1")
    public void testGetUser1() {
        given(requestSpec)
        .when()
            .get("/users/1")
        .then()
            .spec(responseSpec)
            .statusCode(200)
            .body("id", equalTo(1))
            .body("name", not(emptyString()))
            .body("email", containsString("@"));
    }

    @Test(description = "GET user id=5")
    public void testGetUser5() {
        given(requestSpec)
        .when()
            .get("/users/5")
        .then()
            .spec(responseSpec)
            .statusCode(200)
            .body("id", equalTo(5))
            .body("username", notNullValue())
            .body("address.city", notNullValue());
    }

    @Test(description = "GET user not found")
    public void testUserNotFound() {
        given(requestSpec)
        .when()
            .get("/users/9999")
        .then()
            .statusCode(404)
            .body("$", anEmptyMap());
    }
}