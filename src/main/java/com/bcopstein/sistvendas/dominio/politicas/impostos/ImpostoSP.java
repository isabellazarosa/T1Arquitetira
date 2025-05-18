package com.bcopstein.sistvendas.dominio.politicas.impostos;

import com.bcopstein.sistvendas.dominio.entidades.OrcamentoModel;

public class ImpostoSP implements PoliticaDeImposto {
    @Override
    public double calcular(OrcamentoModel orcamento) {
        return orcamento.valorTotalItens() * 0.12;
    }
}
