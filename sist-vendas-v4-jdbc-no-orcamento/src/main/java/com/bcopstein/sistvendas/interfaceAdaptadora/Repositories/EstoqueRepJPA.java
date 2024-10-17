package com.bcopstein.sistvendas.interfaceAdaptadora.Repositories;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.bcopstein.sistvendas.RelacaoExterna.EstoqueJPA_ItfRep;
import com.bcopstein.sistvendas.RelacaoExterna.Produto;
import com.bcopstein.sistvendas.elementosDeDominio.modelos.ItemDeEstoqueModel;
import com.bcopstein.sistvendas.elementosDeDominio.modelos.ProdutoModel;
import com.bcopstein.sistvendas.elementosDeDominio.persistencia.IEstoqueRepositorio;

@Repository
@Primary
public class EstoqueRepJPA implements IEstoqueRepositorio{
    private EstoqueJPA_ItfRep estoque;

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
        ItemDeEstoque item = estoque.findById(codigo).orElse(null);
        if (item == null){
            return -1;
        }else{
            return item.getQuantidade();
        }
    }

    @Override
    public ItemDeEstoqueModel consultaPorId(long id) {
        ItemDeEstoque item = estoque.findById(id).orElse(null);
        if (item == null){
            return null;
        }else{
            return ItemDeEstoque.toItemDeEstoqueModel(item);
        }
    }

    @Override
    public void salva(ItemDeEstoqueModel item) {
        ItemDeEstoque itemJPA = ItemDeEstoque.toItemDeEstoque(item);
        estoque.save(itemJPA);
    }
}
