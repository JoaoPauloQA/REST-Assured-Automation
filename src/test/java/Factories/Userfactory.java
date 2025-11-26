package Factories;

public class Userfactory {

    public static String gerarUsername() {
        return "user" + System.currentTimeMillis();
    }

    public static String gerarPassword() {
        return "123456";
    }

    public static String gerarNomeCompleto() {
        return "QA User";
    }

    public static String registroEmailInvalido() {
        return """
        {
            "username": "teste",
            "email": "emailInvalido.com",
            "password": "123456",
            "nome_completo": "QA User"
        }
    """;
    }

    public static String registroEmailDuplicado() {

        return """
        {
            "username": "Jessy",
            "email": "123483434333@teste.com",
            "password": "123456",
            "nome_completo": "Jessi"
        }
    """;
    }

    public static String registroSemBody() {

        return """
        {
            "username": "",
            "email": "",
            "password": "",
            "nome_completo": ""
        }
    """;
    }


}
