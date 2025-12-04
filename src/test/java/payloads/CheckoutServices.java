package payloads;

import config.BaseTests;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class CheckoutServices  extends BaseTests {

    public Response realizarCheckout(String token,String body) {

     return given()
             .header("Authorization", "Bearer" + token)
             .contentType("application/json")
             .body(body)
             .when()
             .post(CHECKOUT_ENDPOINT)
             .then()
             .extract()
             .response();

    }



}
