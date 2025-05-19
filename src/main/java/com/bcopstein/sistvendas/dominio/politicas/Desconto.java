package com.bcopstein.sistvendas.dominio.politicas;

import com.bcopstein.sistvendas.dominio.entidades.OrcamentoModel;

public interface Desconto {
    double calcular(OrcamentoModel orcamento);
}