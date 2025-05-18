package com.bcopstein.sistvendas.interfaceAdaptadora.controladores;

import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

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

import com.bcopstein.sistvendas.dominio.politicas.impostos.ImpostoRS;
import com.bcopstein.sistvendas.dominio.politicas.impostos.ImpostoFederalPadrao;
import com.bcopstein.sistvendas.dominio.politicas.impostos.PoliticaDeImposto;

import com.bcopstein.sistvendas.dominio.politicas.descontos.DescontoPorQuantidadeItem;
import com.bcopstein.sistvendas.dominio.politicas.descontos.DescontoPorQuantidadeTotalDeItens;
import com.bcopstein.sistvendas.dominio.politicas.descontos.PoliticaDeDesconto;

import com.bcopstein.sistvendas.dominio.servicos.ServicoDeEstoque;
import com.bcopstein.sistvendas.dominio.servicos.ServicoDeVendas;

@RestController
public class Controller {
    private ProdutosDisponiveisUC produtosDisponiveis;
    private CriaOrcamentoUC criaOrcamento;
    private EfetivaOrcamentoUC efetivaOrcamento;
    private QuantidadeEstoqueDisponivelUC quantEstoqueDisponivel;
    private OrcamentosEfetivadosNoPeriodoUC orcamentosEfetivadosNoPeriodo;

    //@Autowired
    public Controller(ProdutosDisponiveisUC produtosDisponiveis,
                      EfetivaOrcamentoUC efetivaOrcamento,
                      QuantidadeEstoqueDisponivelUC quantEstoqueDisponivel,
                      OrcamentosEfetivadosNoPeriodoUC orcamentosEfetivadosNoPeriodo,
                      ServicoDeVendas servicoDeVendas,
                      ServicoDeEstoque servicoEstoque) {
        this.produtosDisponiveis = produtosDisponiveis;
        this.efetivaOrcamento = efetivaOrcamento;
        this.quantEstoqueDisponivel = quantEstoqueDisponivel;
        this.orcamentosEfetivadosNoPeriodo = orcamentosEfetivadosNoPeriodo;

        // Define as políticas
        List<PoliticaDeImposto> politicasDeImposto = Arrays.asList(
                new ImpostoRS(),
                new ImpostoFederalPadrao()
        );

        List<PoliticaDeDesconto> politicasDeDesconto = Arrays.asList(
                new DescontoPorQuantidadeItem(),
                new DescontoPorQuantidadeTotalDeItens()
        );

        // Cria o UC com as políticas
        this.criaOrcamento = new CriaOrcamentoUC(servicoDeVendas, servicoEstoque, politicasDeImposto, politicasDeDesconto);
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