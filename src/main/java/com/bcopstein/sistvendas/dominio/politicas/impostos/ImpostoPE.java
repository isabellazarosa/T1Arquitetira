package com.bcopstein.sistvendas.dominio.politicas.impostos;

import com.bcopstein.sistvendas.dominio.entidades.OrcamentoModel;
import com.bcopstein.sistvendas.dominio.entidades.ItemPedidoModel;

public class ImpostoPE implements PoliticaDeImposto {
    @Override
    public double calcular(OrcamentoModel orcamento) {
        double totalImposto = 0.0;

        for (ItemPedidoModel item : orcamento.getItens()) {
            double aliquota = item.getProduto().getDescricao().endsWith("*") ? 0.05 : 0.15;
            totalImposto += item.getProduto().getPrecoUnitario() * item.getQuantidade() * aliquota;
        }

        return totalImposto;
    }
}
