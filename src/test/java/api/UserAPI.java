package api;

import entity.User;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static utils.YamlReader.getValue;

public class UserAPI {
    private static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .setBaseUri(getValue("url.user"))
            .build();

    public static Response createUser(User payload) {
        return given()
                .spec(requestSpec)
                .body(payload)
                .post();
    }

    public static Response fetchUserDetail(Long userId) {
        return given()
                .spec(requestSpec)
                .pathParam("id", userId)
                .when()
                .get("/{id}");
    }
}
