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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bcopstein.sistvendas.aplicacao.casosDeUso.EfetivaOrcamentoUC;
import com.bcopstein.sistvendas.aplicacao.casosDeUso.OrcamentosEfetivadosNoPeriodoUC;
import com.bcopstein.sistvendas.aplicacao.casosDeUso.SolicitarOrcamentoUC;
import com.bcopstein.sistvendas.aplicacao.dtos.ItemPedidoDTO;
import com.bcopstein.sistvendas.aplicacao.dtos.OrcamentoDTO;
import com.bcopstein.sistvendas.aplicacao.dtos.OrcamentoResumoDTO;

@RestController
@RequestMapping("orcamentos")
@CrossOrigin(origins = "*")
public class OrcamentoController {
    private final EfetivaOrcamentoUC efetivaOrcamento;
    private final OrcamentosEfetivadosNoPeriodoUC orcamentosEfetivadosNoPeriodo;
    private final SolicitarOrcamentoUC solicitarOrcamento;

    public OrcamentoController(
        EfetivaOrcamentoUC efetivaOrcamento,
        OrcamentosEfetivadosNoPeriodoUC orcamentosEfetivadosNoPeriodo,
        SolicitarOrcamentoUC solicitarOrcamento
    ) {

        this.efetivaOrcamento = efetivaOrcamento;
        this.orcamentosEfetivadosNoPeriodo = orcamentosEfetivadosNoPeriodo;
        this.solicitarOrcamento = solicitarOrcamento;
    }


    @GetMapping("/efetivar/{id}")
    public OrcamentoDTO efetivarOrcamento(@PathVariable("id") long idOrcamento) {
        return efetivaOrcamento.run(idOrcamento);
    }

    @GetMapping("/efetivados/{inicio}/{fim}")
    public List<OrcamentoResumoDTO> orcamentosEfetivados(@PathVariable String inicio, @PathVariable String fim) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataInicio = LocalDate.parse(inicio, formatter);
        LocalDate dataFim = LocalDate.parse(fim, formatter);
        return orcamentosEfetivadosNoPeriodo.executar(dataInicio, dataFim);
    }

    @PostMapping("/solicitar")
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
