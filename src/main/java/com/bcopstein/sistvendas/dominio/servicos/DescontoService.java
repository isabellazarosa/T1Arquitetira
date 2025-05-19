package com.bcopstein.sistvendas.dominio.descontos;

import java.util.ArrayList;
import java.util.List;

import com.bcopstein.sistvendas.dominio.entidades.OrcamentoModel;
import com.bcopstein.sistvendas.dominio.politicas.Desconto;
import com.bcopstein.sistvendas.dominio.politicas.DescontoPorItemEmQuantidade;
import com.bcopstein.sistvendas.dominio.politicas.DescontoPorQuantidadeTotalItens;

public class DescontoService {
    private List<Desconto> politicas = new ArrayList<>();

    public DescontoService() {
        politicas.add(new DescontoPorItemEmQuantidade());
        politicas.add(new DescontoPorQuantidadeTotalItens());
        // futuras políticas podem ser adicionadas aqui
    }

    public double calcularDescontos(OrcamentoModel orcamento) {
        return politicas.stream()
                .mapToDouble(p -> p.calcular(orcamento))
                .sum();
    }
}
