package payloads;
//✔ Aqui você centraliza todos os payloads
//✔ Igual "fixtures"/"factories" no Cypress

public class RegisterPayloads {


    public static String registro(String username, String email, String password, String nomeCompleto) {
        return """
            {
                "username": "%s",
                "email": "%s",
                "password": "%s",
                "nome_completo": "%s"
            }
        """.formatted(username, email, password, nomeCompleto);
    }


}
