package validators;

import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class ResponseValidator {

    public void  validarStatusCode(Response response, int esperado){
         assertThat(response.statusCode(), equalTo(esperado));
    }
}
