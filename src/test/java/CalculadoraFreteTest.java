import org.example.CalculadoraFrete;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// Define um nome mais legível para a classe de testes.
@DisplayName("Testes da Calculadora de Frete")

// Todos os testes desta classe pertencem à categoria "unidade".
@Tag("unidade")
class CalculadoraFreteTest {

private CalculadoraFrete calculadora;

/*
 * Este método será executado antes de cada teste.
 *
 * Assim, cada teste recebe uma nova CalculadoraFrete,
 * evitando que um teste interfira em outro.
 */
@BeforeEach
void prepararTeste() {
    calculadora = new CalculadoraFrete();
}

/*
 * @Nested cria um grupo de testes.
 *
 * Esta classe interna reúne os testes relacionados
 * ao frete comum.
 */
@Nested
@DisplayName("Quando a entrega for comum")
@Tag("frete-comum")
class FreteComum {

    @Test
    @DisplayName("Deve cobrar R$ 12 para pedido abaixo de R$ 200")
    void deveCobrarFreteComum() {

        // Arrange — preparação dos dados.
        double valorPedido = 100.0;
        boolean entregaExpressa = false;

        // Act — execução do método testado.
        double freteObtido =
                calculadora.calcularFrete(
                        valorPedido,
                        entregaExpressa
                );

        // Assert — verificação do resultado.
        assertEquals(12.0, freteObtido);
    }

    @Test
    @DisplayName("Deve cobrar R$ 12 para pedido de R$ 199,99")
    void deveCobrarFreteComumNoValorLimite() {

        double valorPedido = 199.99;
        boolean entregaExpressa = false;

        double freteObtido =
                calculadora.calcularFrete(
                        valorPedido,
                        entregaExpressa
                );

        assertEquals(12.0, freteObtido);
    }
}

/*
 * Grupo dos testes relacionados à entrega expressa.
 */
@Nested
@DisplayName("Quando a entrega for expressa")
@Tag("frete-expresso")
class FreteExpresso {

    @Test
    @DisplayName("Deve cobrar R$ 25 para pedido abaixo de R$ 200")
    void deveCobrarFreteExpresso() {

        // Arrange
        double valorPedido = 150.0;
        boolean entregaExpressa = true;

        // Act
        double freteObtido =
                calculadora.calcularFrete(
                        valorPedido,
                        entregaExpressa
                );

        // Assert
        assertEquals(25.0, freteObtido);
    }
}

/*
 * Grupo dos testes relacionados ao frete grátis.
 */
@Nested
@DisplayName("Quando o pedido possuir frete grátis")
@Tag("frete-gratis")
class FreteGratis {

    @Test
    @DisplayName("Deve oferecer frete grátis para pedido de R$ 200")
    void deveOferecerFreteGratisNoValorLimite() {

        double valorPedido = 200.0;
        boolean entregaExpressa = false;

        double freteObtido =
                calculadora.calcularFrete(
                        valorPedido,
                        entregaExpressa
                );

        assertEquals(0.0, freteObtido);
    }

    @Test
    @DisplayName("Frete grátis deve prevalecer sobre entrega expressa")
    void freteGratisDevePrevalecerSobreEntregaExpressa() {

        double valorPedido = 300.0;
        boolean entregaExpressa = true;

        double freteObtido =
                calculadora.calcularFrete(
                        valorPedido,
                        entregaExpressa
                );

        /*
         * Mesmo solicitando entrega expressa, o método determina
         * frete grátis para pedidos a partir de R$ 200.
         */
        assertEquals(0.0, freteObtido);
    }
}

/*
 * Grupo dos testes que verificam entradas inválidas.
 */
@Nested
@DisplayName("Quando o valor do pedido for inválido")
@Tag("excecao")
class ValorInvalido {

    @Test
    @DisplayName("Deve lançar exceção para valor negativo")
    void deveLancarExcecaoParaValorNegativo() {

        double valorPedido = -10.0;

        /*
         * assertThrows verifica se o código executado
         * lança a exceção esperada.
         *
         * Neste caso, esperamos IllegalArgumentException.
         */
        IllegalArgumentException excecao =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> calculadora.calcularFrete(
                                valorPedido,
                                false
                        )
                );

        /*
         * Além do tipo da exceção, verificamos sua mensagem.
         */
        assertEquals(
                "O valor do pedido não pode ser negativo.",
                excecao.getMessage()
        );
    }
}
}
