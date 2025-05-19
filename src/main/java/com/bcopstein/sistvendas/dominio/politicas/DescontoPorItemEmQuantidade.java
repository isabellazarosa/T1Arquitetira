package com.bcopstein.sistvendas.dominio.politicas;

import com.bcopstein.sistvendas.dominio.entidades.OrcamentoModel;

public class DescontoPorItemEmQuantidade implements Desconto {
    @Override
    public double calcular(OrcamentoModel orcamento) {
        return orcamento.getItens().stream()
                .filter(item -> item.getQuantidade() > 3)
                .mapToDouble(item -> item.getProduto().getPrecoUnitario() * item.getQuantidade() * 0.05)
                .sum();
    }
}
