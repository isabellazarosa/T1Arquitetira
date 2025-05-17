package com.bcopstein.sistvendas.interfaceAdaptadora.repositorios.interfaceJPA;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.bcopstein.sistvendas.dominio.entidades.OrcamentoModel;

public interface OrcamentoJPA_ItfRep extends CrudRepository<OrcamentoModel, Long> {
    List<OrcamentoModel> findAll();
}
