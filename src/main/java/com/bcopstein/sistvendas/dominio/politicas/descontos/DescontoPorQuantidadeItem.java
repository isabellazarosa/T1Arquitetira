package com.bcopstein.sistvendas.dominio.politicas.descontos;

import com.bcopstein.sistvendas.dominio.entidades.OrcamentoModel;
import com.bcopstein.sistvendas.dominio.entidades.ItemPedidoModel;

public class DescontoPorQuantidadeItem implements PoliticaDeDesconto {
    @Override
    public double calcular(OrcamentoModel orcamento) {
        double desconto = 0.0;

        for (ItemPedidoModel item : orcamento.getItens()) {
            if (item.getQuantidade() > 3) {
                desconto += item.getProduto().getPrecoUnitario() * item.getQuantidade() * 0.05;
            }
        }

        return desconto;
    }
}
