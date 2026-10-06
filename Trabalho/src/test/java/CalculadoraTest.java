import org.example.Calculadora;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// 1-STUB: Fornecer uma resposta previamente definida
class CalculadoraStub implements Calculadora {
    @Override
    public int somar(int a, int b) {
        return 10; // vai responder sempre dez, sem ter que contar
    }
}

// 2-FAKE: Oferecer uma implementação simplificada e funcional
class CalculadoraFake implements Calculadora {
    @Override
    public int somar(int a, int b) {
        return a + b; // vai fazer a soma verdadeira
    }
}

// 3-SPY: Registrar as chamadas realizadas
class CalculadoraSpy implements Calculadora {
    public int quantidadeChamadas = 0;

    @Override
    public int somar(int a, int b) {
        quantidadeChamadas++; // vai anotar que o método foi executado
        return a + b;
    }
}

// 4-MOCK: Verificar se determinadas interações aconteceram
class CalculadoraMock implements Calculadora {
    public boolean chamouComCinco = false;

    @Override
    public int somar(int a, int b) {
        if (a == 5 && b == 5) {
            chamouComCinco = true; // vai marcar como verdadeiro se usou os valores esperados
        }
        return a + b;
    }
}

public class CalculadoraTest {

    public static void main(String[] args) {
        System.out.println("- - Demostração Dos Testes - - \n");

        // 1-DUMMY
        Calculadora dummy = null;
        System.out.println("1. Dummy: Objeto nulo apenas para preencher parâmetro -> " + dummy);

        // 2-STUB
        Calculadora stub = new CalculadoraStub();
        System.out.println("2. Stub (Sempre retorna 10): " + stub.somar(2, 2));

        // 3-FAKE
        Calculadora fake = new CalculadoraFake();
        System.out.println("3. Fake (Soma simples 3+4): " + fake.somar(3, 4));

        // 4-SPY
        CalculadoraSpy spy = new CalculadoraSpy();
        spy.somar(1, 1);
        spy.somar(2, 2);
        System.out.println("4. Spy (Quantidade de chamadas): " + spy.quantidadeChamadas);

        // 5-MOCK
        CalculadoraMock mock = new CalculadoraMock();
        mock.somar(5, 5);
        System.out.println("5. Mock (Chamou com 5 e 5?): " + mock.chamouComCinco);
    }
}
