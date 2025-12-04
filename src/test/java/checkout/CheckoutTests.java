package checkout;
import config.BaseTests;
import payloads.AuthServices;
import payloads.CheckoutServices;
import validators.ResponseValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

@Epic("Fluxo de compra")
@Feature("Checkout")

public class CheckoutTests  extends BaseTests {

    AuthServices auth = new AuthServices();
    ResponseValidator validator = new ResponseValidator();
    CheckoutServices checkout = new CheckoutServices();

    @Test
    @Story("Checkout bem sucedido")
    public void DeveComprarComsucesso() {

         // Login API
        Response response = auth.fazerLogin("qa_user@gamestore.com", "123456");
        token = auth.extrairToken(response);







    }


}
