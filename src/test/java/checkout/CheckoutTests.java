package checkout;
import config.BaseTests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import payloads.AuthServices;
import payloads.CheckoutServices;
import validators.ResponseValidator;
import io.restassured.response.Response;
import Contracts.CheckoutContract;
import Factories.Userfactory;
import org.junit.jupiter.api.Test;
import payloads.CarrinhoPayload;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

@Epic("Fluxo de compra")
@Feature("Checkout")

public class CheckoutTests  extends BaseTests {

    AuthServices auth = new AuthServices();
    ResponseValidator validator = new ResponseValidator();
    CheckoutServices checkout = new CheckoutServices();
    CheckoutContract contract = new CheckoutContract();

    @BeforeEach
    public void LoginAPI () {
        Response response = auth.fazerLogin("qa_user@gamestore.com", "123456789");
        token = auth.extrairToken(response);

    }


    @Tag("smoke")
    @Test
    @Story("Checkout bem sucedido")
    public void DeveComprarComsucesso() {

         // Login API


        String body = CarrinhoPayload.criarCheckout(1,1,"pix");

        Response checkoutresponse =  checkout.realizarCheckout(token, body);

        validator.validarStatusCode(checkoutresponse, 200);
        contract.CheckoutContractPositivo(checkoutresponse);


    }

    @Test
    @Story("Checkout sem body")
    public void DeveExibirMensagemDeErroAoRealizarCheckoutSemBody() {

        String body = "";

        Response checkoutresponse = checkout.realizarCheckout(token, body);

        validator.validarStatusCode(checkoutresponse,400);
        contract.validarContratoErroCheckoutComMensagemEmPortugues(checkoutresponse);



    }

    @Test
    @Story("Checkout com id inexistente")
    public void DeveExibirMensagemDeErroaoRealizarCheckoutComIDInexistente() {

        String body = CarrinhoPayload.criarCheckout(25,1,"pix");

        Response checkoutresponse = checkout.realizarCheckout(token, body);

        validator.validarStatusCode(checkoutresponse,400);
        contract.validarContratoErroCheckoutComMensagemEmPortugues(checkoutresponse);
    }

    @Test
    @Story("Checkout com token inválido")
    public void DeveExibirMensagemDeErroAoRealizarCheckoutcomTokenInválido() {

        String tokeninvalido = "abc234";

        String body = CarrinhoPayload.criarCheckout(2,2,"pix");

        Response checkoutresponse = checkout.realizarCheckout(tokeninvalido, body);

        validator.validarStatusCode(checkoutresponse, 401);
        contract.validarContratoErroCheckoutComMensagemEmIngles(checkoutresponse);

    }

    @Test
    @Story("Checkout sem token")
    public void DeveExibirMensagemDeErroAoRealizarCheckoutSemToken(){

        String tokenAusente = "";

        String body = CarrinhoPayload.criarCheckout(3,1,"pix");

        Response checkoutresponse = checkout.realizarCheckout(tokenAusente, body);

        validator.validarStatusCode(checkoutresponse,401);
        contract.validarContratoErroCheckoutComMensagemEmIngles(checkoutresponse);
    }
   @Test
    @Story("Checkout com token expirado")
     public void DeveExibirMensagemDeErroAoRealizarCheckoutComTokenExpirado() {

        String token_expirado = Userfactory.TokenExpirado();

        String body = CarrinhoPayload.criarCheckout(4,1,"pix");

        Response checkoutresponse = checkout.realizarCheckout(token_expirado, body);

        validator.validarStatusCode(checkoutresponse, 401);
       contract.validarContratoErroCheckoutComMensagemEmIngles(checkoutresponse);
   }
}
