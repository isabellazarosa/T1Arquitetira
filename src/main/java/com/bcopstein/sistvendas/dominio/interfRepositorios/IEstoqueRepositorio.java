package com.bcopstein.sistvendas.dominio.interfRepositorios;

import java.util.List;

import com.bcopstein.sistvendas.aplicacao.dtos.ProdutoDTO;
import com.bcopstein.sistvendas.dominio.entidades.ProdutoModel;

public interface IEstoqueRepositorio {
    List<ProdutoModel> todos();
    List<ProdutoModel> todosComEstoque();
    int quantidadeEmEstoque(long codigo);
    int baixaEstoque(long codProd, int qtdade);

    int addEstoque(long codProd);

    int addNovoEstoque(ProdutoDTO dto);

}
