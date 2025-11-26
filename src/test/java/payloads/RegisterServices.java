package payloads;
import config.BaseTests;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;


public class RegisterServices extends BaseTests {

    public Response RealizarRegistro(String username ,String password, String nomeCompleto) {

        String email = "user" + System.currentTimeMillis() + "@teste.com";

        return
         given()
                 .contentType("application/json")
                 .body(RegisterPayloads.registro(username,email, password, nomeCompleto ))
        .when()
                .post(REGISTER_ENDPOINT);




    }

    public Response RealizarRegistroNegativo(String body) {
        return
                given()
                        .contentType("application/json")
                        .body(body)
                        .when()
                        .post(REGISTER_ENDPOINT);
    }





}
