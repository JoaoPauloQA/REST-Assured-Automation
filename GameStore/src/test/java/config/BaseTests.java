package config;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;


public class BaseTests {

    protected static final String BASE_URL = "http://localhost:3000/api";

    protected static final String REGISTER_ENDPOINT = "/auth/register";
    protected static final String LOGIN_ENDPOINT = "/auth/login";

    protected static final String GAMES_ENDPOINT = "/games";
    protected static final String CHECKOUT_ENDPOINT = "/checkout";
    protected static final String PURCHASES_ENDPOINT = "/purchases";

    protected static String token;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }
}