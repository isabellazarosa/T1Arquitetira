package com.bcopstein.sistvendas.aplicacao.casosDeUso;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bcopstein.sistvendas.aplicacao.dtos.ProdutoEstoqueDTO;
import com.bcopstein.sistvendas.dominio.entidades.ProdutoModel;
import com.bcopstein.sistvendas.dominio.servicos.ServicoDeEstoque;

/**
 * Caso de uso que retorna, para cada produto do catálogo,
 * a quantidade atualmente disponível em estoque.
 */
@Component
public class QuantidadeEstoqueDisponivelUC {
    private final ServicoDeEstoque servicoEstoque;

    public QuantidadeEstoqueDisponivelUC(ServicoDeEstoque servicoEstoque) {
        this.servicoEstoque = servicoEstoque;
    }

    public List<ProdutoEstoqueDTO> run() {
        return servicoEstoque.produtosDisponiveis()  // traz todos os produtos do catálogo
                .stream()
                .map(p -> {
                    int qtd = servicoEstoque.qtdadeEmEstoque(p.getId());
                    return ProdutoEstoqueDTO.fromModel(
                            p.getId(), p.getDescricao(), p.getPrecoUnitario(), qtd
                    );
                })
                .toList();
    }

    public List<ProdutoEstoqueDTO> run(List<String> codigosProdutos) {
        return servicoEstoque.produtosDisponiveis().stream()
                .filter(p -> codigosProdutos.contains(p.getId()))
                .map(p -> {
                    int qtd = servicoEstoque.qtdadeEmEstoque(p.getId());
                    return ProdutoEstoqueDTO.fromModel(
                            p.getId(), p.getDescricao(), p.getPrecoUnitario(), qtd
                    );
                })
                .toList();
    }

}
