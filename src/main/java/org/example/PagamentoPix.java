package org.example;

public class PagamentoPix extends Pagamento {

    public PagamentoPix(double valor){
        super(valor);
    }

    @Override
    public double calcularTaxa() {
        return 0.0;
    }
}
