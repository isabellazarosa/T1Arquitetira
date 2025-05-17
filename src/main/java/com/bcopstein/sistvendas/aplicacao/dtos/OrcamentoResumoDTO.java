package com.bcopstein.sistvendas.aplicacao.dtos;

import java.time.LocalDate;

public class OrcamentoResumoDTO {
    private long id;
    private double custoConsumidor;
    private LocalDate dataEfetivacao;

    public OrcamentoResumoDTO(long id, double custoConsumidor, LocalDate dataEfetivacao) {
        this.id = id;
        this.custoConsumidor = custoConsumidor;
        this.dataEfetivacao = dataEfetivacao;
    }

    public long getId() {
        return id;
    }

    public double getCustoConsumidor() {
        return custoConsumidor;
    }

    public LocalDate getDataEfetivacao() {
        return dataEfetivacao;
    }
}
