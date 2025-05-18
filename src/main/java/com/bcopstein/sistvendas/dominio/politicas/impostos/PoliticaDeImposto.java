package com.bcopstein.sistvendas.dominio.politicas.impostos;

import com.bcopstein.sistvendas.dominio.entidades.OrcamentoModel;

public interface PoliticaDeImposto {
    double calcular(OrcamentoModel orcamento);
}
