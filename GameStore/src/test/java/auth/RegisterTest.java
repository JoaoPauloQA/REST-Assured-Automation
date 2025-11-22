package auth;
import config.BaseTests;
import static io.restassured.RestAssured.*;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import payloads.AuthServices;
import io.restassured.response.Response;
import payloads.RegisterServices;
import utils.Utils;
import validators.ResponseValidator;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;


@Epic("Autenticação")
@Feature("Registro")

public class  RegisterTest extends BaseTests{

    ResponseValidator validator = new ResponseValidator();
    RegisterServices register = new RegisterServices();


    @Test


    public void deveRegistrarComEmailUnico() {

        String email = Utils.gerarEmailAleatorio();
        Response response = register.RealizarRegistro(email);
        validator.validarStatusCode(response, 201);





    }
}