package com.bcopstein.sistvendas.aplicacao.casosDeUso;

import java.util.List;
import java.util.stream.Collectors;

import com.bcopstein.sistvendas.aplicacao.dtos.ProdutoEstoqueDTO;
import com.bcopstein.sistvendas.dominio.entidades.ItemDeEstoqueModel;
import com.bcopstein.sistvendas.dominio.servicos.ServicoDeEstoque;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bcopstein.sistvendas.aplicacao.dtos.ProdutoDTO;
import com.bcopstein.sistvendas.dominio.entidades.ProdutoModel;
import com.bcopstein.sistvendas.dominio.interfRepositorios.IProdutoRepositorio;

@Service
public class ChegadaUC {
    private ServicoDeEstoque servicoDeEstoque;

    public ChegadaUC(ServicoDeEstoque servicoDeEstoque) {
        this.servicoDeEstoque = servicoDeEstoque;
    }

    @Transactional
    public List<ProdutoDTO> run(List<ProdutoDTO> itens) {
        for (ProdutoDTO dto : itens) {
            ProdutoModel produto = servicoDeEstoque.produtoPorCodigo(dto.getId());

            if (produto != null) {
                // Produto já existe: aumenta o estoque em 1
                servicoDeEstoque.addEstoque(produto.getId());
            } else {
                // Produto novo: cadastra com estoque = 1
                //produto = new ProdutoModel(dto.getId(), dto.getDescricao(), dto.getPrecoUnitario());
                //itemDeEstoque = new ItemDeEstoqueModel(dto.getId(), dto.getDescricao(), dto.getPrecoUnitario())
                servicoDeEstoque.addNovoEstoque(dto);
            }

        }
        return itens;
    }
}
