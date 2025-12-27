package auth;

import config.BaseTests;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;

import Factories.Userfactory;
import payloads.RegisterServices;
import validators.ResponseValidator;

@Epic("Autenticação")
@Feature("Registro")
@Order(1)
public class RegisterTest extends BaseTests {

    ResponseValidator validator = new ResponseValidator();
    RegisterServices register = new RegisterServices();


    @Tag("smoke")
    @Test
    @Story("Registro bem sucedido")
    @Order(1)
    public void deveRegistrarComSucesso() {

        String username = Userfactory.gerarUsername();
        String password = Userfactory.gerarPassword();
        String nomeCompleto = Userfactory.gerarNomeCompleto();

        Response response = register.RealizarRegistro(
                username, password, nomeCompleto
        );

        validator.validarStatusCode(response, 201);

 }


    @Tag("smoke")
  @Test
    @Story("RegistroEmailInvalido")
  @Order(2)
    public void deveExibirMensagemDeErroRegistroNegativo() {

       String body = Userfactory.registroEmailInvalido();

        Response response = register.RealizarRegistroNegativo(body);

        validator.validarStatusCode(response, 400);

  }

  @Test
    @Story("RegistroEmailDuplicado")
  @Order(3)
    public void deveExibirMensagemDeErroRegistroEmailDuplicado() {

        String body = Userfactory.registroEmailDuplicado();

        Response response = register.RealizarRegistroNegativo(body);

        validator.validarStatusCode(response, 409);
  }
    @Tag("smoke")
@Test
    @Story("RegistroUsernameDuplicado")
@Order(4)
    public void DeveexibirMensagemDeErroRegistroSemBody() {

        String body = Userfactory.registroSemBody();

        Response response = register.RealizarRegistroNegativo(body);

        validator.validarStatusCode(response, 400);



}



    }
