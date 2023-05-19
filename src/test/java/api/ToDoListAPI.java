package api;

import entity.ToDo;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static utils.YamlReader.getValue;

public class ToDoListAPI {

    private static final RequestSpecification requestSpec =
            new RequestSpecBuilder()
                    .setContentType(ContentType.JSON)
                    .setBaseUri(getValue("url.todo"))
                    .build();

    public static Response getList() {
        return given()
                .spec(requestSpec)
                .when()
                .get(getValue("path.todo.getList"));
    }

    public static Response getDetail(Long id) {
        return given()
                .spec(requestSpec)
                .pathParam("id", id)
                .when()
                .get(getValue("path.todo.getDetail"));
    }

    public static Response create(ToDo payload) {
        return given()
                .spec(requestSpec)
                .header("Authorization", "Bearer " + getValue("token.todo"))
                .when()
                .body(payload)
                .post(getValue("path.todo.create"));
    }

    public static Response update(ToDo payload) {
        return given()
                .spec(requestSpec)
                .header("Authorization", "Bearer " + getValue("token.todo"))
                .pathParam("id", payload.getId())
                .when()
                .put(getValue("path.todo.update"));
    }

    public static Response delete(Long id) {
        return given()
                .spec(requestSpec)
                .header("Authorization", "Bearer " + getValue("token.todo"))
                .pathParam("id", id)
                .when()
                .delete(getValue("path.todo.delete"));
    }

}
