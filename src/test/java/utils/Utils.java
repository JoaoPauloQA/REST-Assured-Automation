package utils;

public class Utils {
    public static String gerarEmailAleatorio() {
        return "user_" + System.currentTimeMillis() + "@teste.com";
    }
}