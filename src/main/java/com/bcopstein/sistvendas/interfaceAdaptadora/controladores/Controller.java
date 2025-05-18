package com.bcopstein.sistvendas.interfaceAdaptadora.controladores;

import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//import org.springframework.beans.factory.annotation.Autowired;
import com.bcopstein.sistvendas.aplicacao.dtos.ProdutoEstoqueDTO;
import com.bcopstein.sistvendas.aplicacao.casosDeUso.OrcamentosEfetivadosNoPeriodoUC;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bcopstein.sistvendas.aplicacao.casosDeUso.CriaOrcamentoUC;
import com.bcopstein.sistvendas.aplicacao.casosDeUso.EfetivaOrcamentoUC;
import com.bcopstein.sistvendas.aplicacao.casosDeUso.ProdutosDisponiveisUC;
import com.bcopstein.sistvendas.aplicacao.casosDeUso.QuantidadeEstoqueDisponivelUC;
import com.bcopstein.sistvendas.aplicacao.dtos.ItemPedidoDTO;
import com.bcopstein.sistvendas.aplicacao.dtos.OrcamentoDTO;
import com.bcopstein.sistvendas.aplicacao.dtos.ProdutoDTO;
import com.bcopstein.sistvendas.aplicacao.dtos.OrcamentoResumoDTO;

@RestController
public class Controller {
    private ProdutosDisponiveisUC produtosDisponiveis;
    private CriaOrcamentoUC criaOrcamento;
    private EfetivaOrcamentoUC efetivaOrcamento;
    private QuantidadeEstoqueDisponivelUC quantEstoqueDisponivel;
    private OrcamentosEfetivadosNoPeriodoUC orcamentosEfetivadosNoPeriodo;

    //@Autowired
    public Controller(ProdutosDisponiveisUC produtosDisponiveis,
                      CriaOrcamentoUC criaOrcamento,
                      EfetivaOrcamentoUC efetivaOrcamento,
                      QuantidadeEstoqueDisponivelUC quantEstoqueDisponivel,
                      OrcamentosEfetivadosNoPeriodoUC orcamentosEfetivadosNoPeriodo){
        this.produtosDisponiveis = produtosDisponiveis;
        this.criaOrcamento = criaOrcamento;
        this.efetivaOrcamento = efetivaOrcamento;
        this.quantEstoqueDisponivel = quantEstoqueDisponivel;
        this.orcamentosEfetivadosNoPeriodo = orcamentosEfetivadosNoPeriodo;
    }

    @GetMapping("")
    @CrossOrigin(origins = "*")
    public String welcomeMessage(){
        return("Bem vindo as lojas ACME");
    }

    @GetMapping("produtosDisponiveis")
    @CrossOrigin(origins = "*")
    public List<ProdutoDTO> produtosDisponiveis(){
        return produtosDisponiveis.run();
    }    

    @PostMapping("novoOrcamento")
    @CrossOrigin(origins = "*")
    public OrcamentoDTO novoOrcamento(@RequestBody List<ItemPedidoDTO> itens){
        return criaOrcamento.run(itens);
    }

    @GetMapping("efetivaOrcamento/{id}")
    @CrossOrigin(origins = "*")
    public OrcamentoDTO efetivaOrcamento(@PathVariable(value="id") long idOrcamento){
        return efetivaOrcamento.run(idOrcamento);
    }

    // Retorna a quantidade disponível em estoque para todos os itens do catálogo
    @GetMapping("quantidadesEmEstoque")
    @CrossOrigin(origins = "*")
    public List<ProdutoEstoqueDTO> quantidadesEmEstoque(){
                return quantEstoqueDisponivel.run();
            }
    
    @GetMapping("orcamentosEfetivados/{inicio}/{fim}")
    @CrossOrigin(origins = "*")
    public List<OrcamentoResumoDTO> orcamentosEfetivados(@PathVariable String inicio, @PathVariable String fim) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataInicio = LocalDate.parse(inicio, formatter);
        LocalDate dataFim = LocalDate.parse(fim, formatter);
        return orcamentosEfetivadosNoPeriodo.executar(dataInicio, dataFim);
}


}