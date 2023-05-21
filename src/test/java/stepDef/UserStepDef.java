package stepDef;

import api.UserAPI;
import entity.User;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.Map;

public class UserStepDef {
    private static final ThreadLocal<User> currenUser = new ThreadLocal<>();


    @When("I create a user with {string}, {string} and {int}")
    public void iCreateAUserWithNameSexAndAge(String name, String sex, Integer age) {
        currenUser.set(UserAPI.createUser(User.builder()
                        .name(name)
                        .sex(sex)
                        .age(age)
                        .build())
                .then()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/user-schema.json"))
                .statusCode(201)
                .extract().jsonPath().getObject("", User.class));

    }

    @And("I get the user info with id")
    public void iGetTheUserInfoWithId() {
        System.out.println(currenUser.get().getId());
        Response response = UserAPI.fetchUserDetail(currenUser.get().getId());
        response.then()
                .log().body();
    }


    @Then("The user info should match the {string}, {string} and {int}")
    public void theUserInfoShouldMatchTheNameSexAndAge(String name, String sex, int age) {
        Assert.assertEquals(currenUser.get().getName(), name);
        Assert.assertEquals(currenUser.get().getSex(), sex);
        Assert.assertEquals((int) currenUser.get().getAge(), age);
    }
}
