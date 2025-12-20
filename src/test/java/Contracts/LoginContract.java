package Contracts;

import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;;

public class LoginContract {

    public void validarContratoLoginComSucesso(Response response) {

        response.then().log().all()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/login-success.schema.json"));

    }
    public void validarContratoLoginNegativo(Response response) {

        response.then().log().all()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/login-error.schema.json"));


    }
}
