import org.example.PagamentoPix;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("testando os pagamentos")
class PagamentoTest{
    //teste do pagamento do pix

    @Nested
    @DisplayName("pagamento via pix")
    class TestePix{
        @Test
        @DisplayName("tem que retornar taxa zero para o pagamento via pix :)")
                void deveLancarExcecaoPraValorInvalido(){
            IllegalArgumentException excecao = assertThrows(
                    IllegalArgumentException.class,
                    () -> new PagamentoPix(0.0)
            );
            assertEquals("O valor deve ser maior que zero.",excecao.getMessage());
        }
    }
    @Nested
    @DisplayName("pagamento via cartao")
    class TesteCartao{
        @Test
        @DisplayName("tem que retorna com 2.5 de taxa")
    }
}
