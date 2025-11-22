package payloads;

import config.BaseTests;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthServices extends BaseTests {

    public Response fazerLogin(String email, String password) {
        return
                given()
                        .contentType("application/json")
                        .body(AuthPayloads.login(email, password))
                        .when()
                        .post(LOGIN_ENDPOINT);
    }

    public String extrairToken(Response response) {
        return response.jsonPath().getString("token");
    }
}