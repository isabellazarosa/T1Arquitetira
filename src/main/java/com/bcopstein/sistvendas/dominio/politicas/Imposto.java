package com.bcopstein.sistvendas.dominio.politicas;

import com.bcopstein.sistvendas.dominio.entidades.OrcamentoModel;

public interface Imposto {
   double calcular(OrcamentoModel orcamento);
}
