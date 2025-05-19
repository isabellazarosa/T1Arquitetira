package com.bcopstein.sistvendas.dominio.politicas;

import com.bcopstein.sistvendas.dominio.entidades.ItemPedidoModel;
import com.bcopstein.sistvendas.dominio.entidades.OrcamentoModel;

public class DescontoPorQuantidadeTotalItens implements Desconto {
    @Override
    public double calcular(OrcamentoModel orcamento) {
        int totalItens = orcamento.getItens().stream()
                .mapToInt(ItemPedidoModel::getQuantidade)
                .sum();

        if (totalItens > 10) {
            return orcamento.getCustoItens() * 0.10;
        }
        return 0.0;
    }
}
