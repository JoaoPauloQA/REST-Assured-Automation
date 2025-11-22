package payloads;
import config.BaseTests;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;


public class RegisterServices extends BaseTests {

    public Response RealizarRegistro(String email) {

        return
         given()
                 .contentType("application/json")
        .body("{\"email\": \"" + email + "\", \"password\": \"123456\"}")
        .when()
                .post(REGISTER_ENDPOINT);




    }





}
