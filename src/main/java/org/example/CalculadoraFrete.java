package org.example;

public class CalculadoraFrete {

public double calcularFrete(
        double valorPedido,
        boolean entregaExpressa) {

    // Valor negativo não representa um pedido válido.
    if (valorPedido < 0) {
        throw new IllegalArgumentException(
                "O valor do pedido não pode ser negativo."
        );
    }

    // Pedidos a partir de R$200 possuem frete grátis.
    if (valorPedido >= 200) {
        return 0.0;
    }

    // Se a entrega for expressa, o frete custa R$ 25.
    if (entregaExpressa) {
        return 25.0;
    }

    // Caso contrário, será utilizado o frete comum.
    return 12.0;
}
}
