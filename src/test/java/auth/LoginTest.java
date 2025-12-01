package auth;
import config.BaseTests;
import static io.restassured.RestAssured.*;
import payloads.AuthServices;
import io.restassured.response.Response;
import validators.ResponseValidator;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

@Epic("Autenticação")
@Feature("Login")

public class LoginTest extends BaseTests {



    AuthServices auth = new AuthServices();
    ResponseValidator validator = new ResponseValidator();

    @Test
    @Story("Login bem-sucedido")
    public void deveLogarComSucesso(){

        Response response = auth.fazerLogin("qa_user@gamestore.com", "123456");
        validator.validarStatusCode(response,200);
        token = auth.extrairToken(response);

    }

    @Test
    @Story("Erro de credenciais")

    public void deveRetornar401QuandoEmailForInvalido(){

        Response response = auth.fazerLogin("dev_incorreto@email.com", "123456");
        validator.validarStatusCode(response,401);
    }

    @Test
    @Story("Erro de payload")
    public void deveRetornar400QuandoBodyForVazio(){

        Response response = auth.fazerLogin("", "");
        validator.validarStatusCode(response,400);

    }




}



