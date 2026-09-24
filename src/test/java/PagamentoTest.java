package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Testes do Sistema de Pagamento")
class PagamentoTest {
    @Nested
    @DisplayName("pagamento via pix")
    class TestePix {
        @Test
        @DisplayName("tem que retornar taxa zero")
        void deveRetornarTaxaZero() {
            PagamentoPix pix = new PagamentoPix(100.0);
            assertEquals(0.0, pix.calcularTaxa());
        }

        @Test
        @DisplayName("tem que lançar exceção para valor inválido")
        void deveLancarExcecaoPraValorInvalido() {
            IllegalArgumentException excecao = assertThrows(
                    IllegalArgumentException.class,
                    () -> new PagamentoPix(0.0)
            );
            assertEquals("O valor deve ser maior que zero.", excecao.getMessage());
        }
    }

    @Nested
    @DisplayName("pagamento via cartão")
    @Tag("cartao")
    class TesteCartao {
        @Test
        @DisplayName("tem que calcular taxa de 2,5%")
        void deveCalcularTaxaDeDoisEPontoCinco() {
            PagamentoCartao cartao = new PagamentoCartao(200.0);
            assertEquals(5.0, cartao.calcularTaxa());
        }

        @Test
        @DisplayName("tem que lançar exceção para valor negativo")
        void deveLancarExcecaoParaValorNegativo() {
            IllegalArgumentException excecao = assertThrows(
                    IllegalArgumentException.class,
                    () -> new PagamentoCartao(-50.0)
            );
            assertEquals("O valor deve ser maior que zero.", excecao.getMessage());
        }
    }

    @Nested
    @DisplayName("polimorfismo de pagamentos")
    class TestePolimorfismo {
        @Test
        @DisplayName("executa taxa correta usando referencia do tipo Pagamento")
        void deveCalcularTaxasCorretasComPolimorfismo() {
            Pagamento pagamentoPix = new PagamentoPix(100.0);
            Pagamento pagamentoCartao = new PagamentoCartao(100.0);

            assertAll("Validação de taxas polimórficas",
                    () -> assertEquals(0.0, pagamentoPix.calcularTaxa()),
                    () -> assertEquals(2.5, pagamentoCartao.calcularTaxa())
            );
        }
    }
}
