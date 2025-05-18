package com.bcopstein.sistvendas.aplicacao.casosDeUso;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bcopstein.sistvendas.aplicacao.dtos.OrcamentoResumoDTO;
import com.bcopstein.sistvendas.dominio.entidades.OrcamentoModel;
import com.bcopstein.sistvendas.dominio.servicos.ServicoDeVendas;

@Service
public class OrcamentosEfetivadosNoPeriodoUC {
    private final ServicoDeVendas servicoDeVendas;

    public OrcamentosEfetivadosNoPeriodoUC(ServicoDeVendas servicoDeVendas) {
        this.servicoDeVendas = servicoDeVendas;
    }

    public List<OrcamentoResumoDTO> executar(LocalDate inicio, LocalDate fim) {
        List<OrcamentoModel> orcamentos = servicoDeVendas.orcamentosEfetivadosEntre(inicio, fim);

        return orcamentos.stream()
            .map(orc -> new OrcamentoResumoDTO(
                orc.getId(),
                orc.getCustoConsumidor(),
                orc.getDataEfetivacao()))
            .collect(Collectors.toList());
    }
}
