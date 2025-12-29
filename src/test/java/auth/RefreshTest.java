package auth;
import config.BaseTests;
import io.restassured.response.Response;
import Contracts.RefreshContract;
import org.junit.jupiter.api.Test;
import payloads.AuthServices;
import validators.ResponseValidator;
import Factories.RefreshFactory;
import Factories.Userfactory;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

@Epic("Refresh")
@Feature("TokenRenovação")

public class RefreshTest extends BaseTests {

    AuthServices auth = new AuthServices();
    ResponseValidator validator = new ResponseValidator();
    RefreshContract contract = new RefreshContract();

    @Test
    @Story("RefreshTokenBemSucedido")
    public void DeveRetornar200ComTokenValido() {

        Response response = auth.fazerLogin("qa_user@gamestore.com", "123456789");
        token = auth.extrairToken(response);

        Response Refreshresponse = auth.RefreshToken(token);
        validator.validarStatusCode(Refreshresponse,200);

        contract.validarContratoRefreshComSucesso(Refreshresponse);
    }

    @Test
    @Story("TokenCorrompido")
    public void DeveRetornar401CasoTokenSejaCorrompido(){

        String token = RefreshFactory.TokenFaltando1Digito();
        Response Refreshresponse = auth.RefreshToken(token);
        validator.validarStatusCode(Refreshresponse,401);
        contract.validarContratoRefreshNegativo(Refreshresponse);


    }

    @Test
    @Story("TokenExpirado")
    public void Deveretornar401CasoTokenEstejaExpirado(){

        String token = Userfactory.TokenExpirado();
        Response Refreshresponse = auth.RefreshToken(token);
        validator.validarStatusCode(Refreshresponse,401);
        contract.validarContratoRefreshNegativo(Refreshresponse);

    }

    @Test
    @Story("TokenVazio")
    public void Deveretornar401CasoTokenEstejaVazio(){

        String token = "";
        Response Refreshresponse = auth.RefreshToken(token);
        validator.validarStatusCode(Refreshresponse,401);
        contract.validarContratoRefreshNegativo(Refreshresponse);
    }
}
