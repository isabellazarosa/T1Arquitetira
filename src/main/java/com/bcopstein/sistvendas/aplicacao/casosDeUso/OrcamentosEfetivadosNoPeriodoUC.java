package com.bcopstein.sistvendas.aplicacao.casosDeUso;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.bcopstein.sistvendas.aplicacao.dtos.OrcamentoResumoDTO;
import com.bcopstein.sistvendas.dominio.interfRepositorios.IOrcamentoRepositorio;

@Service
public class OrcamentosEfetivadosNoPeriodoUC {
    private IOrcamentoRepositorio orcamentoRepositorio;

    public OrcamentosEfetivadosNoPeriodoUC(IOrcamentoRepositorio orcamentoRepositorio) {
        this.orcamentoRepositorio = orcamentoRepositorio;
    }

    public List<OrcamentoResumoDTO> executar(LocalDate inicio, LocalDate fim) {
        return orcamentoRepositorio.todos().stream()
            .filter(orc -> orc.isEfetivado() &&
                           orc.getDataEfetivacao() != null &&
                           !orc.getDataEfetivacao().isBefore(inicio) &&
                           !orc.getDataEfetivacao().isAfter(fim))
            .map(orc -> new OrcamentoResumoDTO(
                orc.getId(),
                orc.getCustoConsumidor(),
                orc.getDataEfetivacao()))
            .collect(Collectors.toList());
    }
}
