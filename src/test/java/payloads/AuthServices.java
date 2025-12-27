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
                        .log().all()
                        .when()
                        .post(LOGIN_ENDPOINT);
    }

    public String extrairToken(Response response) {
        return response.jsonPath().getString("token");
    }

    public Response VerificarToken(String token){
        return
                given()

                        .header("Authorization", "Bearer " + token)
                        .log().all()
                        .when()
                        .get(VERIFY_ENDPOINT);
    }
}

