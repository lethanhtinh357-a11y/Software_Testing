package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CommentApiTest extends BaseTest {
    @Test(description = "GET /posts/1/comments -> 5 comments")
    public void testGetCommentsByPost() {
        given(requestSpec)
            .when().get("/posts/1/comments")
            .then().statusCode(200)
            .time(lessThan(3000L))
            .body("size()", equalTo(5))
            .body("[0].postId", equalTo(1))
            .body("[0]", hasKey("id"))
            .body("[0]", hasKey("name"))
            .body("[0]", hasKey("email"))
            .body("[0]", hasKey("body"));
    }
}