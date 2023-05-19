package testcases.todo;

import api.ToDoListAPI;
import com.github.javafaker.Faker;
import entity.ToDo;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class IntegrationTest {

    private static final Faker faker = new Faker();
    private static Long userId;
    private static Long todoId;
    private static final ResponseSpecification schemaValidator = new ResponseSpecBuilder()
            .expectBody(matchesJsonSchemaInClasspath("todo-schema.json"))
            .build();


    @Test(priority = 0)
    public void testGetList() {
        List<ToDo> todoLists = ToDoListAPI
                .getList()
                .then().statusCode(200)
                .extract().jsonPath().getList("", ToDo.class);
        todoLists.forEach(todo -> matchesJsonSchemaInClasspath("todo-schema.json").matches(todo));
        userId = todoLists.get(0).getUserId();
    }

    @Test(priority = 1)
    public void testCreate() {
        Response res = ToDoListAPI.create(ToDo.builder()
                .userId(userId)
                .status(faker.options().option("completed", "pending"))
                .title(faker.book().title())
                .dueOn(faker.date().future(10, TimeUnit.DAYS).toString())
                .build());
        todoId = res.then().spec(schemaValidator)
                .statusCode(201)
                .extract().jsonPath().getLong("id");
    }

    @Test(priority = 2)
    public void testGetDetail() {
        ToDoListAPI.getDetail(todoId)
                .then()
                .spec(schemaValidator);
    }




}
