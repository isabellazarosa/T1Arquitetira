package com.bcopstein.sistvendas.aplicacao.casosDeUso;

import java.util.List;

//import org.springframework.beans.factory.annotation.Autowired;
import com.bcopstein.sistvendas.dominio.politicas.descontos.PoliticaDeDesconto;
import com.bcopstein.sistvendas.dominio.politicas.impostos.PoliticaDeImposto;
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
public class CriaOrcamentoUC {
    private ServicoDeVendas  servicoDeVendas;
    private ServicoDeEstoque servicoDeEstoque;
    private List<PoliticaDeImposto> politicasDeImposto;
    private List<PoliticaDeDesconto> politicasDeDesconto;
    
    //@Autowired
    public CriaOrcamentoUC(
            ServicoDeVendas servicoDeVendas,
            ServicoDeEstoque servicoDeEstoque,
            List<PoliticaDeImposto> politicasDeImposto,
            List<PoliticaDeDesconto> politicasDeDesconto
    ) {
        this.servicoDeVendas = servicoDeVendas;
        this.servicoDeEstoque = servicoDeEstoque;
        this.politicasDeImposto = politicasDeImposto;
        this.politicasDeDesconto = politicasDeDesconto;
    }


    public OrcamentoDTO run(List<ItemPedidoDTO> itens){
        PedidoModel pedido = new PedidoModel(0); //TODO ta errado isso
        for(ItemPedidoDTO item:itens){
            ProdutoModel produto = servicoDeEstoque.produtoPorCodigo(item.getIdProduto());
            ItemPedidoModel itemPedido = new ItemPedidoModel(produto, item.getQtdade());
            pedido.addItem(itemPedido);
        }
        OrcamentoModel orcamento = servicoDeVendas.criaOrcamento(pedido);

        double totalImpostos = politicasDeImposto.stream()
                .mapToDouble(p -> p.calcular(orcamento))
                .sum();

        double totalDescontos = politicasDeDesconto.stream()
                .mapToDouble(p -> p.calcular(orcamento))
                .sum();

        orcamento.setImposto(totalImpostos);
        orcamento.setDesconto(totalDescontos);
        orcamento.setCustoConsumidor(orcamento.valorTotalItens() + totalImpostos - totalDescontos);

        return OrcamentoDTO.fromModel(orcamento);
    }
}
