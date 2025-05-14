package com.bcopstein.sistvendas.interfaceAdaptadora.repositorios.interfaceJPA;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bcopstein.sistvendas.interfaceAdaptadora.repositorios.entidades.Produto;

public interface ProdutoJPA_ItfRep extends CrudRepository<Produto, Long>{
    List<Produto> findAll();
    Produto findById(long id);
}
