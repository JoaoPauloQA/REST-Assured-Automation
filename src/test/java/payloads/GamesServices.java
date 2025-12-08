package payloads;

import config.BaseTests;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class GamesServices  extends BaseTests {

    public  Response listarTodosJogos(String endpoint) {
       return given()
               .get(endpoint)
               .then()
               .log().all()
               .extract()
               .response();


    }
}
