package Contracts;
import io.restassured.response.Response;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;;

public class CheckoutContract {

    public void CheckoutContractPositivo(Response response){

        response.then().log().all()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/checkout-success.schema.json"));

    }

    public void validarContratoErroCheckoutComMensagemEmPortugues(Response response){
        response.then().log().all()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/checkout-error-mensagem.schema.json"));
    }
    public void validarContratoErroCheckoutComMensagemEmIngles(Response response){
        response.then().log().all()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/checkout-error-message.schema.json"));
    }
}
