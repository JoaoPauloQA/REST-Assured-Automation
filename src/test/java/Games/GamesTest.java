package Games;

import config.BaseTests;
import payloads.GamesServices;
import io.restassured.response.Response;
import validators.ResponseValidator;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

public class GamesTest extends BaseTests {

    ResponseValidator validator = new ResponseValidator();
    GamesServices games = new GamesServices();


    @Test
    @Story("Buscar Jogos")
    public void DeveExibirJogosListados() {

        Response response = games.listarTodosJogos(GAMES_ENDPOINT);

        validator.validarStatusCode(response, 200);



    }

    @Test
    @Story("Buscar jogos populares")
    public void DeveExibirJogosPopulares() {

        Response response = games.listarTodosJogos(GAMESPOPULAR_ENDPOINT);

        validator.validarStatusCode(response, 200);
    }

    @Test
    @Story("BuscarDetalhesJogos")
    public void DeveExibirDetalhesdoJogo() {

        Response response = games.listarTodosJogos(GAMES_ENDPOINT+ "/1/details");

        validator.validarStatusCode(response, 200);
    }
}
