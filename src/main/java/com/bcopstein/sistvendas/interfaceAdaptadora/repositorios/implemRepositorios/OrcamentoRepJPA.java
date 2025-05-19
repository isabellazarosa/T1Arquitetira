package com.bcopstein.sistvendas.interfaceAdaptadora.repositorios.implemRepositorios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.bcopstein.sistvendas.dominio.entidades.OrcamentoModel;
import com.bcopstein.sistvendas.dominio.interfRepositorios.IOrcamentoRepositorio;
import com.bcopstein.sistvendas.interfaceAdaptadora.repositorios.interfaceJPA.OrcamentoJPA_ItfRep;

@Repository
@Primary
public class OrcamentoRepJPA implements IOrcamentoRepositorio {

    @Autowired
    private OrcamentoJPA_ItfRep orcamentoJPA;

    @Override
    public List<OrcamentoModel> todos() {
        return orcamentoJPA.findAll();
    }

    @Override
    public OrcamentoModel cadastra(OrcamentoModel orcamento) {
        return orcamentoJPA.save(orcamento);
    }

    @Override
    public OrcamentoModel recuperaPorId(long id) {
        return orcamentoJPA.findById(id)
                .orElseThrow(() -> new RuntimeException("Orçamento com ID " + id + " não encontrado"));
    }

    @Override
    public void marcaComoEfetivado(long id) {
        OrcamentoModel orc = orcamentoJPA.findById(id)
            .orElseThrow(() -> new RuntimeException("Orçamento com ID " + id + " não encontrado"));
        orc.efetiva();
        orcamentoJPA.save(orc);
    }

    @Override
    public void atualiza(OrcamentoModel orcamento) {
        orcamentoJPA.save(orcamento);
    }
    
}
