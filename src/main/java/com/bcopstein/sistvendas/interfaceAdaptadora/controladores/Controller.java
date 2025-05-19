package com.bcopstein.sistvendas.interfaceAdaptadora.controladores;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bcopstein.sistvendas.aplicacao.casosDeUso.CriaOrcamentoUC;
import com.bcopstein.sistvendas.aplicacao.casosDeUso.EfetivaOrcamentoUC;
import com.bcopstein.sistvendas.aplicacao.casosDeUso.OrcamentosEfetivadosNoPeriodoUC;
import com.bcopstein.sistvendas.aplicacao.casosDeUso.ProdutosDisponiveisUC;
import com.bcopstein.sistvendas.aplicacao.casosDeUso.QuantidadeEstoqueDisponivelUC;
import com.bcopstein.sistvendas.aplicacao.casosDeUso.SolicitarOrcamentoUC;
import com.bcopstein.sistvendas.aplicacao.dtos.ItemPedidoDTO;
import com.bcopstein.sistvendas.aplicacao.dtos.OrcamentoDTO;
import com.bcopstein.sistvendas.aplicacao.dtos.OrcamentoResumoDTO;
import com.bcopstein.sistvendas.aplicacao.dtos.ProdutoDTO;
import com.bcopstein.sistvendas.aplicacao.dtos.ProdutoEstoqueDTO;

@RestController
public class Controller {
    private ProdutosDisponiveisUC produtosDisponiveis;
    private CriaOrcamentoUC criaOrcamento;
    private EfetivaOrcamentoUC efetivaOrcamento;
    private QuantidadeEstoqueDisponivelUC quantEstoqueDisponivel;
    private OrcamentosEfetivadosNoPeriodoUC orcamentosEfetivadosNoPeriodo;
    private SolicitarOrcamentoUC solicitarOrcamento;

    //@Autowired
    public Controller(ProdutosDisponiveisUC produtosDisponiveis,
                      CriaOrcamentoUC criaOrcamento,
                      EfetivaOrcamentoUC efetivaOrcamento,
                      QuantidadeEstoqueDisponivelUC quantEstoqueDisponivel,
                      OrcamentosEfetivadosNoPeriodoUC orcamentosEfetivadosNoPeriodo,
                      SolicitarOrcamentoUC solicitarOrcamento){
        this.produtosDisponiveis = produtosDisponiveis;
        this.criaOrcamento = criaOrcamento;
        this.efetivaOrcamento = efetivaOrcamento;
        this.quantEstoqueDisponivel = quantEstoqueDisponivel;
        this.orcamentosEfetivadosNoPeriodo = orcamentosEfetivadosNoPeriodo;
        this.solicitarOrcamento = solicitarOrcamento;
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

    @PostMapping("/solicitarOrcamento")
    public ResponseEntity<OrcamentoDTO> solicitarOrcamento(
        @RequestParam String cliente,
        @RequestParam String estado,
        @RequestParam String pais,
        @RequestBody List<ItemPedidoDTO> itensPedido
    ) {
        OrcamentoDTO orcamentoDto = solicitarOrcamento.executar(cliente, estado, pais, itensPedido);
        return ResponseEntity.ok(orcamentoDto);
    }


}