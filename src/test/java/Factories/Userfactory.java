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
            "email": "qa_user@gamestore.com",
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

    public static String TokenExpirado() {
        return "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NCwiZW1haWwiOiJxYV91c2VyQGdhbWVzdG9yZS5jb20iLCJ1c2VybmFtZSI6InFhVVNFUiIsImlhdCI6MTc2NTEyMDMyMywiZXhwIjoxNzY1MjA2NzIzfQ.oSvaNF5cHRGNzSeeWuB2iGYirSp9B160qfu98NHx2yc";
    }


}
