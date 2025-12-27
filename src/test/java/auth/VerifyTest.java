package auth;
import config.BaseTests;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import payloads.AuthServices;
import validators.ResponseValidator;
import Factories.TokenFactory;

public class VerifyTest  extends BaseTests {

    AuthServices auth = new AuthServices();
    ResponseValidator validator = new ResponseValidator();

    @BeforeEach
    public void LoginAPI () {
        Response response = auth.fazerLogin("qa_user@gamestore.com", "123456789");
        token = auth.extrairToken(response);

    }

    @Test
    public void VerificarTokenValido() {

     Response verifyResponse = auth.VerificarToken(token);

     validator.validarStatusCode(verifyResponse, 200);

    }

    @Test
    public void deveRetornar401ParaTokenComAssinaturaCorrompida(){

        String TokenCorrompido = TokenFactory.TokenComAssinaturaInvalida();

        Response verifyResponse = auth.VerificarToken(TokenCorrompido);
                validator.validarStatusCode(verifyResponse, 401);
    }

    @Test
    public void DeveRetornar401ComTokenAusente() {

        String tokenAusente = "";
        Response verifyResponse = auth.VerificarToken(tokenAusente);
        validator.validarStatusCode(verifyResponse,401);
    }


}
