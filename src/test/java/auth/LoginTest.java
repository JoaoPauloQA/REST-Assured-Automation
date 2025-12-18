package auth;
import config.BaseTests;
import static io.restassured.RestAssured.*;
import payloads.AuthServices;
import io.restassured.response.Response;
import validators.ResponseValidator;
import Contracts.LoginContract;
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
    LoginContract contract = new LoginContract();

    @Test
    @Story("Login bem-sucedido")
    public void deveLogarComSucesso(){

        Response response = auth.fazerLogin("qa_user@gamestore.com", "123456789");
        validator.validarStatusCode(response,200);
        contract.validarContratoLoginComSucesso(response);
        token = auth.extrairToken(response);


    }

    @Test
    @Story("Erro de credenciais")

    public void deveRetornar401QuandoEmailNaoEstiverCadastrado(){

        Response response = auth.fazerLogin("dev_incorreto@email.com", "123456");
        validator.validarStatusCode(response,401);
        contract.validarContratoLoginNegativo(response);
    }

    @Test
    @Story("EmailSem@")

    public void deveRetornar401QuandoEmailTiverFormatoInvalido(){

        Response response = auth.fazerLogin("dev_incorretoemail.com", "123456");
        validator.validarStatusCode(response,401);
        contract.validarContratoLoginNegativo(response);
    }

    @Test
    @Story("SenhaVazia")

    public void deveRetornar401QuandoSenhaEstiverVazia(){

        Response response = auth.fazerLogin("qa_user@gamestore.com", "");
        validator.validarStatusCode(response,400);
        contract.validarContratoLoginNegativo(response);
    }

    @Test
    @Story("EmailVazio")

    public void deveRetornar400QuandoEmailEstiverVazio(){

        Response response = auth.fazerLogin("", "123456");
        validator.validarStatusCode(response,400);
        contract.validarContratoLoginNegativo(response);
    }


    @Test
    @Story("Erro de payload")
    public void deveRetornar400QuandoBodyForVazio(){

        Response response = auth.fazerLogin("", "");
        validator.validarStatusCode(response,400);
        contract.validarContratoLoginNegativo(response);

    }




}



