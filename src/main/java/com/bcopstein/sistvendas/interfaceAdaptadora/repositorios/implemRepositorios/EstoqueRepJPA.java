package com.bcopstein.sistvendas.interfaceAdaptadora.repositorios.implemRepositorios;

import java.util.List;
import java.util.Optional;

import com.bcopstein.sistvendas.aplicacao.dtos.ProdutoDTO;
import com.bcopstein.sistvendas.dominio.entidades.ItemDeEstoqueModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.bcopstein.sistvendas.dominio.entidades.ProdutoModel;
import com.bcopstein.sistvendas.dominio.interfRepositorios.IEstoqueRepositorio;
import com.bcopstein.sistvendas.interfaceAdaptadora.repositorios.entidades.ItemDeEstoque;
import com.bcopstein.sistvendas.interfaceAdaptadora.repositorios.entidades.Produto;
import com.bcopstein.sistvendas.interfaceAdaptadora.repositorios.interfaceJPA.EstoqueJPA_ItfRep;


@Repository
@Primary
public class EstoqueRepJPA implements IEstoqueRepositorio{
    private EstoqueJPA_ItfRep estoque;
    private int proxId = 300; //TODO achar uma forma menos troglodita de fazer autoincrement
    @Autowired
    public EstoqueRepJPA(EstoqueJPA_ItfRep estoque){
        this.estoque = estoque;
    }

    @Override
    public List<ProdutoModel> todos() {
        List<ItemDeEstoque> itens = estoque.findAll();
        return itens.stream()
                .map(it->Produto.toProdutoModel(it.getProduto()))
                .toList();
    }

    @Override
    public List<ProdutoModel> todosComEstoque() {
        List<ItemDeEstoque> itens = estoque.findAll();
        return itens.stream()
                .filter(it->it.getQuantidade()>0)
                .map(it->Produto.toProdutoModel(it.getProduto()))
                .toList();
    }

    @Override
    public int quantidadeEmEstoque(long codigo) {
        Optional<ItemDeEstoque> itemOpt = estoque.findByProduto_Id(codigo);

        if (itemOpt.isEmpty()) {
            return -1;
        }
        return itemOpt.get().getQuantidade();
    }

    @Override
    public int baixaEstoque(long codProd, int qtdade) {
        Optional<ItemDeEstoque> itemOpt = estoque.findByProduto_Id(codProd);
        if (itemOpt.isEmpty()) {
            throw new IllegalArgumentException("Produto inexistente");
        }
        ItemDeEstoque item = itemOpt.get();

        if (item.getQuantidade() < qtdade) {
            throw new IllegalArgumentException("Quantidade em estoque insuficiente");
        }
        int novaQuantidade = item.getQuantidade() - qtdade;
        item.setQuantidade(novaQuantidade);
        estoque.save(item);
        return novaQuantidade;
    }

    @Override
    public int addEstoque(long codProd){//adiciona mais um para o item
        Optional<ItemDeEstoque> itemOpt = estoque.findByProduto_Id(codProd);
        if (itemOpt == null){
            throw new IllegalArgumentException("Produto inexistente");
        }
        ItemDeEstoque item = itemOpt.get();
        item.setQuantidade(item.getQuantidade()+1);
        estoque.save(item);
        return item.getQuantidade();
    }

    @Override
    public int addNovoEstoque(ProdutoDTO dto){
        Produto produto = new Produto(dto.getId(), dto.getDescricao(), dto.getPrecoUnitario());
        ItemDeEstoque itemDeEstoque = new ItemDeEstoque(proxId,produto,1,0,200);//quantidades temporarias
        proxId+= 100;
        estoque.save(itemDeEstoque);
        return 0;
    }
}
