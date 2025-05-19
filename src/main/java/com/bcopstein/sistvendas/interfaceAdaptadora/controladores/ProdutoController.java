package com.bcopstein.sistvendas.interfaceAdaptadora.controladores;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcopstein.sistvendas.aplicacao.casosDeUso.ChegadaUC;
import com.bcopstein.sistvendas.aplicacao.casosDeUso.ProdutosDisponiveisUC;
import com.bcopstein.sistvendas.aplicacao.casosDeUso.QuantidadeEstoqueDisponivelUC;
import com.bcopstein.sistvendas.aplicacao.dtos.ProdutoDTO;
import com.bcopstein.sistvendas.aplicacao.dtos.ProdutoEstoqueDTO;

@RestController
@RequestMapping("produtos")
@CrossOrigin(origins = "*")
public class ProdutoController {
    private final ProdutosDisponiveisUC produtosDisponiveis;
    private final QuantidadeEstoqueDisponivelUC quantEstoqueDisponivel;
    private final ChegadaUC chegada;

    public ProdutoController(
        ProdutosDisponiveisUC produtosDisponiveis,
        QuantidadeEstoqueDisponivelUC quantEstoqueDisponivel,
        ChegadaUC chegada
    ) {
        this.produtosDisponiveis = produtosDisponiveis;
        this.quantEstoqueDisponivel = quantEstoqueDisponivel;
        this.chegada = chegada;
    }

    @GetMapping("/disponiveis")
    public List<ProdutoDTO> listarProdutosDisponiveis() {
        return produtosDisponiveis.run();
    }

    @GetMapping("/estoque/quantidade")
    public List<ProdutoEstoqueDTO> consultarQuantidadesEmEstoque() {
        return quantEstoqueDisponivel.run();
    }

    @GetMapping("/estoque")
    public List<ProdutoEstoqueDTO> consultarEstoquePorProdutos(@RequestBody List<Long> codigosProdutos) {
        return quantEstoqueDisponivel.run(codigosProdutos);
    }

    @PostMapping("/chegada")
    public List<ProdutoDTO> registrarChegadaEstoque(@RequestBody List<ProdutoDTO> itens) {
        return chegada.run(itens);
    }
}
