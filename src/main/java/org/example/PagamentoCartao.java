package org.example;

public class PagamentoCartao extends Pagamento{

    public PagamentoCartao(double valor){
        super(valor);
    }

    @Override
    public double calcularTaxa(){
        return getValor() * 0.025;
    }
}
