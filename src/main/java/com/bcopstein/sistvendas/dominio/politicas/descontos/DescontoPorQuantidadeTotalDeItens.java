package com.bcopstein.sistvendas.dominio.politicas.descontos;

import com.bcopstein.sistvendas.dominio.entidades.OrcamentoModel;

public class DescontoPorQuantidadeTotalDeItens implements PoliticaDeDesconto {
    @Override
    public double calcular(OrcamentoModel orcamento) {
        int totalItens = orcamento.getItens().stream()
                .mapToInt(item -> item.getQuantidade())
                .sum();

        if (totalItens > 10) {
            return orcamento.valorTotalItens() * 0.10;
        }

        return 0.0;
    }
}
