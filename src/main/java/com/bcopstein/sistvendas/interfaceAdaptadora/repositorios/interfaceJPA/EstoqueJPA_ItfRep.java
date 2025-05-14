package com.bcopstein.sistvendas.interfaceAdaptadora.repositorios.interfaceJPA;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.bcopstein.sistvendas.interfaceAdaptadora.repositorios.entidades.ItemDeEstoque;

public interface EstoqueJPA_ItfRep extends CrudRepository<ItemDeEstoque,Long>{
    List<ItemDeEstoque> findAll();
    Optional<ItemDeEstoque> findById(long id);
}
