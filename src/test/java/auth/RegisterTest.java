package auth;

import config.BaseTests;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import Factories.Userfactory;
import payloads.RegisterServices;
import validators.ResponseValidator;

@Epic("Autenticação")
@Feature("Registro")
public class RegisterTest extends BaseTests {

    ResponseValidator validator = new ResponseValidator();
    RegisterServices register = new RegisterServices();

    @Test
    @Story("Registro bem sucedido")
    public void deveRegistrarComSucesso() {

        String username = Userfactory.gerarUsername();
        String password = Userfactory.gerarPassword();
        String nomeCompleto = Userfactory.gerarNomeCompleto();

        Response response = register.RealizarRegistro(
                username, password, nomeCompleto
        );

        validator.validarStatusCode(response, 201);

 }



  @Test
    @Story("RegistroEmailInvalido")
    public void deveExibirMensagemDeErroRegistroNegativo() {

       String body = Userfactory.registroEmailInvalido();

        Response response = register.RealizarRegistroNegativo(body);

        validator.validarStatusCode(response, 400);

  }

  @Test
    @Story("RegistroEmailDuplicado")
    public void deveExibirMensagemDeErroRegistroEmailDuplicado() {

        String body = Userfactory.registroEmailDuplicado();

        Response response = register.RealizarRegistroNegativo(body);

        validator.validarStatusCode(response, 409);
  }

@Test
    @Story("RegistroUsernameDuplicado")
    public void DeveexibirMensagemDeErroRegistroSemBody() {

        String body = Userfactory.registroSemBody();

        Response response = register.RealizarRegistroNegativo(body);

        validator.validarStatusCode(response, 400);



}



    }
