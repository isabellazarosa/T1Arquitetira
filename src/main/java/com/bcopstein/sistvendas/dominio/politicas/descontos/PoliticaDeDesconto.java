package com.bcopstein.sistvendas.dominio.politicas.descontos;

import com.bcopstein.sistvendas.dominio.entidades.OrcamentoModel;

public interface PoliticaDeDesconto {
    double calcular(OrcamentoModel orcamento);
}
