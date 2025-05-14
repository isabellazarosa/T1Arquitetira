package com.bcopstein.sistvendas.dominio.interfRepositorios;

import java.util.List;

import com.bcopstein.sistvendas.dominio.entidades.ProdutoModel;

public interface IProdutoRepositorio {
    List<ProdutoModel> todos();
    ProdutoModel consultaPorId(long codigo);
}
