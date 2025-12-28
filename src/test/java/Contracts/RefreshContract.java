package Contracts;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;;

public class RefreshContract {

    public void validarContratoRefreshComSucesso(Response response) {

        response.then().log().all()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/Refresh-success.schema.json"));

    }

    public void validarContratoRefreshNegativo(Response response) {

        response.then().log().all()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/Refresh-error.schema.json"));

    }
}
