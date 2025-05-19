package com.bcopstein.sistvendas.dominio.servicos;

import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcopstein.sistvendas.dominio.entidades.ItemPedidoModel;
import com.bcopstein.sistvendas.dominio.entidades.OrcamentoModel;
import com.bcopstein.sistvendas.dominio.entidades.PedidoModel;
import com.bcopstein.sistvendas.dominio.entidades.ProdutoModel;
import com.bcopstein.sistvendas.dominio.interfRepositorios.IEstoqueRepositorio;
import com.bcopstein.sistvendas.dominio.interfRepositorios.IOrcamentoRepositorio;

@Service
public class ServicoDeVendas {
    private IOrcamentoRepositorio orcamentos;
    private IEstoqueRepositorio estoque;

    //@Autowired
    public ServicoDeVendas(IOrcamentoRepositorio orcamentos,IEstoqueRepositorio estoque){
        this.orcamentos = orcamentos;
        this.estoque = estoque;
    }
     
    public List<ProdutoModel> produtosDisponiveis() {
        return estoque.todosComEstoque();
    }

    public OrcamentoModel recuperaOrcamentoPorId(long id) {
        return this.orcamentos.recuperaPorId(id);
    }

    public OrcamentoModel criaOrcamento(PedidoModel pedido) {
        var novoOrcamento = new OrcamentoModel();
        novoOrcamento.setCliente(pedido.getCliente());
        novoOrcamento.setEstado(pedido.getEstado());
        novoOrcamento.setPais(pedido.getPais());
        novoOrcamento.addItensPedido(pedido);

        double custoItens = novoOrcamento.getItens().stream()
                .mapToDouble(it -> it.getProduto().getPrecoUnitario() * it.getQuantidade())
                .sum();
        novoOrcamento.setCustoItens(custoItens);

        double imposto = 0.0;
        String estado = pedido.getEstado().toUpperCase();

        switch (estado) {
            case "RS":
            case "RIO GRANDE DO SUL":
                if (custoItens > 100.0) {
                    imposto = (custoItens - 100.0) * 0.10;
                }
                break;

            case "SP":
            case "SÃO PAULO":
                imposto = custoItens * 0.12;
                break;

            case "PE":
            case "PERNAMBUCO":
                for (ItemPedidoModel item : novoOrcamento.getItens()) {
                    String descricao = item.getProduto().getDescricao();
                    double preco = item.getProduto().getPrecoUnitario();
                    int quantidade = item.getQuantidade();
                    boolean essencial = descricao.trim().endsWith("*");
                    double aliquota = essencial ? 0.05 : 0.15;
                    imposto += preco * quantidade * aliquota;
                }
                break;

            default:
                // Alíquota padrão de 10% para outros estados
                imposto = custoItens * 0.10;
                break;
        }

        novoOrcamento.setImposto(imposto);

        // Regras de desconto
        if (novoOrcamento.getItens().size() > 5) {
            novoOrcamento.setDesconto(custoItens * 0.05);
        } else {
            novoOrcamento.setDesconto(0.0);
        }

        double custoFinal = custoItens + imposto - novoOrcamento.getDesconto();
        novoOrcamento.setCustoConsumidor(custoFinal);

        return this.orcamentos.cadastra(novoOrcamento);
    }
 
    public OrcamentoModel efetivaOrcamento(long id) {
        // Recupera o orçamento
        var orcamento = this.orcamentos.recuperaPorId(id);
        var ok = true;
        // Verifica se tem quantidade em estoque para todos os itens
        for (ItemPedidoModel itemPedido:orcamento.getItens()) {
            int qtdade = estoque.quantidadeEmEstoque(itemPedido.getProduto().getId());
            if (qtdade < itemPedido.getQuantidade()) {
                ok = false;
                break;
            }
        }
        // Se tem quantidade para todos os itens, da baixa no estoque para todos
        if (ok) {
            for (ItemPedidoModel itemPedido:orcamento.getItens()) {
                int qtdade = estoque.quantidadeEmEstoque(itemPedido.getProduto().getId());
                if (qtdade >= itemPedido.getQuantidade()) {
                    estoque.baixaEstoque(itemPedido.getProduto().getId(), itemPedido.getQuantidade());
                }
            }
            // Marca o orcamento como efetivado
            orcamentos.marcaComoEfetivado(id);
            orcamentos.atualiza(orcamento);
        }
        // Retorna o orçamento marcado como efetivado ou não conforme disponibilidade do estoque
        return orcamentos.recuperaPorId(id);
    }

    public List<OrcamentoModel> orcamentosEfetivadosEntre(LocalDate inicio, LocalDate fim) {
    return orcamentos.todos().stream()
        .filter(orc -> orc.isEfetivado()
                    && orc.getDataEfetivacao() != null
                    && !orc.getDataEfetivacao().isBefore(inicio)
                    && !orc.getDataEfetivacao().isAfter(fim))
        .collect(Collectors.toList());
    }
}
