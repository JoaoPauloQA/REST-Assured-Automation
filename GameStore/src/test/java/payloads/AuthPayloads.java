package payloads;

//✔ Aqui você centraliza todos os payloads
//✔ Igual "fixtures"/"factories" no Cypress

public class AuthPayloads {

    public static String login(String email, String password) {
        return """
            {
                "email": "%s",
                "password": "%s"
            }
        """.formatted(email, password);
    }
}