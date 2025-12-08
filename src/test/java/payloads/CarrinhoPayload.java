package payloads;

public class CarrinhoPayload {


    public static String criarCheckout(int idProduto, int qtd , String formadePagamento){

        return  "{\n" +
                "  \"cart\": [\n" +
                "    {\"id\": " + idProduto + ", \"qty\": " + qtd + "}\n" +
                "  ],\n" +
                "  \"formaPagamento\": \"" + formadePagamento + "\"\n" +
                "}";
    }

    }

