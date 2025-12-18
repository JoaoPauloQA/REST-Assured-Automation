package Contracts;

import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

public class LoginContract {

    public void validarContratoLoginComSucesso(Response response) {

        response.then().log().all()
                // Raiz do JSON
                .body("success", is (true))
                .body("token",notNullValue())
                .body("token",instanceOf(String.class))
                .body("user",notNullValue())

        // Objeto User

                .body("user.id", greaterThan(0))
                .body("user.username",not(emptyString()))
                .body("user.email",containsString("@"))
                .body("user.nome_completo", not(emptyString()));
    }

    public void validarContratoLoginNegativo(Response response) {

        response.then().log().all()
                .body("success",is(false))
                .body("error", notNullValue())
                .body("error",instanceOf(String.class))
                .body("message", notNullValue())
                .body("message",instanceOf(String.class));


    }
}
