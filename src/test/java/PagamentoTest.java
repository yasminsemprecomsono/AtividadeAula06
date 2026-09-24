import org.example.PagamentoPix;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("testando os pagamentos")
class PagamentoTest {
    //teste do pagamento do pix

    @Nested
    @DisplayName("pagamento via pix")
    class TestePix {
        @Test
        @DisplayName("tem que retornar taxa zero para o pagamento via pix")
        void deveRetornarTaxaZero() {
            PagamentoPix pix = new PagamentoPix(100.0);
            assertEquals(0.0, pix.calcularTaxa());
        }

        @Test
        @DisplayName("tem que lançar excecao para o valor menor ou igual a zero no pix")
        void deveLancarExcecaoPraValorValido() {
            IllegalArgumentException excecao = assertThrows(
                    IllegalArgumentException.class,
                    () -> new PagamentoPix(0.0)
            );
            assertEquals("O valor deve ser maior que zero.", excecao.getMessage());
        }
    }
}
