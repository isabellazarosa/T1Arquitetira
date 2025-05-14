package com.bcopstein.sistvendas.dominio.interfRepositorios;

import java.util.List;

import com.bcopstein.sistvendas.dominio.entidades.OrcamentoModel;

public interface IOrcamentoRepositorio {
    List<OrcamentoModel> todos();
    OrcamentoModel cadastra(OrcamentoModel orcamento);
    OrcamentoModel recuperaPorId(long id);
    void marcaComoEfetivado(long id); 
}
