package com.bcopstein.sistvendas.dominio.politicas.impostos;

import com.bcopstein.sistvendas.dominio.entidades.OrcamentoModel;

public class ImpostoRS implements PoliticaDeImposto {
    @Override
    public double calcular(OrcamentoModel orcamento) {
        double totalItens = orcamento.valorTotalItens();
        if (totalItens <= 100.0) {
            return 0.0;
        } else {
            return (totalItens - 100.0) * 0.10;
        }
    }
}
