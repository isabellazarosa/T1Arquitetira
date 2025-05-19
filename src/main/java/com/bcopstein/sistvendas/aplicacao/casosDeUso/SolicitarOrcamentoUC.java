package com.bcopstein.sistvendas.aplicacao.casosDeUso;

import java.util.List;
import java.util.stream.Collectors;

import com.bcopstein.sistvendas.aplicacao.dtos.PedidoCreateDTO;
import org.springframework.stereotype.Component;

import com.bcopstein.sistvendas.aplicacao.dtos.ItemPedidoDTO;
import com.bcopstein.sistvendas.aplicacao.dtos.OrcamentoDTO;
import com.bcopstein.sistvendas.dominio.entidades.ItemPedidoModel;
import com.bcopstein.sistvendas.dominio.entidades.OrcamentoModel;
import com.bcopstein.sistvendas.dominio.entidades.PedidoModel;
import com.bcopstein.sistvendas.dominio.entidades.ProdutoModel;
import com.bcopstein.sistvendas.dominio.servicos.ServicoDeEstoque;
import com.bcopstein.sistvendas.dominio.servicos.ServicoDeVendas;

@Component
public class SolicitarOrcamentoUC {

    private final ServicoDeVendas servicoDeVendas; 
    private final ServicoDeEstoque servicoDeEstoque; 

    public SolicitarOrcamentoUC(ServicoDeEstoque servicoDeEstoque,
                                ServicoDeVendas servicoDeVendas) {
        this.servicoDeEstoque = servicoDeEstoque;
        this.servicoDeVendas = servicoDeVendas;
    }
    public OrcamentoDTO executar(PedidoCreateDTO itensDto) {
        List<ItemPedidoModel> itens = itensDto.getItensPedido().stream().map(dto -> {
            ProdutoModel produto = servicoDeEstoque.produtoPorCodigo(dto.getIdProduto());
            if (produto == null) {
                throw new IllegalArgumentException("Produto não encontrado: " + dto.getIdProduto());
            }
            return new ItemPedidoModel(produto, dto.getQtdade());
        }).toList();

        PedidoModel pedido = new PedidoModel(1L);
        itens.forEach(pedido::addItem);
        pedido.setEstado(itensDto.getEstado());
        pedido.setPais(itensDto.getPais());

        OrcamentoModel orcamento = servicoDeVendas.criaOrcamento(pedido);

        return OrcamentoDTO.fromModel(orcamento);
    }

}
