package tests;

import base.ApiBaseTestReqres;
import models.CreateUserRequest;
import models.UserResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserCrudTest extends ApiBaseTestReqres {

    String userId;
    String createdAt;

    private void sleep() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 1. POST
    @Test(priority = 1)
    public void testCreateUser() {

        sleep();

        CreateUserRequest request = new CreateUserRequest("Tịnh", "Tester");

        UserResponse response =
                given(requestSpec)
                        .body(request)
                .when()
                        .post("/api/users")
                .then()
                        .statusCode(201)
                        .body("name", equalTo("Tịnh"))
                        .body("job", equalTo("Tester"))
                        .body("id", notNullValue())
                        .body("createdAt", notNullValue())
                        .extract().as(UserResponse.class);

        userId = response.getId();
        createdAt = response.getCreatedAt();
    }

    // 2. PUT
    @Test(priority = 2, dependsOnMethods = "testCreateUser")
    public void testUpdateUserPut() {

        sleep();

        CreateUserRequest request = new CreateUserRequest("Tịnh Update", "Senior Tester");

        String updatedAt =
                given(requestSpec)
                        .body(request)
                .when()
                        .put("/api/users/" + userId)
                .then()
                        .statusCode(200)
                        .body("job", equalTo("Senior Tester"))
                        .body("updatedAt", notNullValue())
                        .extract().path("updatedAt");

        Assert.assertNotEquals(updatedAt, createdAt);
    }

    // 3. PATCH
    @Test(priority = 3, dependsOnMethods = "testCreateUser")
    public void testUpdateUserPatch() {

        sleep();

        CreateUserRequest request = new CreateUserRequest();
        request.setJob("Lead Tester");

        given(requestSpec)
                .body(request)
        .when()
                .patch("/api/users/" + userId)
        .then()
                .statusCode(200)
                .body("job", equalTo("Lead Tester"))
                .body("updatedAt", notNullValue());
    }

    // 4. DELETE
    @Test(priority = 4, dependsOnMethods = "testCreateUser")
    public void testDeleteUser() {

        sleep();

        given(requestSpec)
        .when()
                .delete("/api/users/" + userId)
        .then()
                .statusCode(204)
                .body(equalTo(""));
    }

    // 5. POST → GET
    @Test(priority = 5)
    public void testPostThenGet() {

        sleep();

        CreateUserRequest request = new CreateUserRequest("An", "Developer");

        UserResponse response =
                given(requestSpec)
                        .body(request)
                .when()
                        .post("/api/users")
                .then()
                        .statusCode(201)
                        .extract().as(UserResponse.class);

        String id = response.getId();

        given(requestSpec)
        .when()
                .get("/api/users/" + id)
        .then()
                .statusCode(anyOf(is(200), is(404)));
    }
}