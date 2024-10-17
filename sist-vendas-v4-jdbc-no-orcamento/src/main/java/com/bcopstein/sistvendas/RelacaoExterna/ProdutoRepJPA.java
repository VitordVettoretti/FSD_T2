package com.bcopstein.sistvendas.RelacaoExterna;

import java.util.LinkedList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.bcopstein.sistvendas.elementosDeDominio.modelos.ProdutoModel;
import com.bcopstein.sistvendas.elementosDeDominio.persistencia.IProdutoRepositorio;

@Repository
@Primary
public class ProdutoRepJPA implements IProdutoRepositorio {
    private ProdutoJPA_ItfRep produtoRepository;

    public ProdutoRepJPA(ProdutoJPA_ItfRep produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoModel> todos() {
        List<Produto> produtos = produtoRepository.findAll();
        if (produtos.isEmpty()) {
            return new LinkedList<>();
        } else {
            return produtos.stream()
                    .map(Produto::toProdutoModel)
                    .toList();
        }
    }

    public ProdutoModel consultaPorId(long id) {
        Produto produto = produtoRepository.findById(id).orElse(null);
        if (produto == null) {
            return null;
        } else {
            return Produto.toProdutoModel(produto);
        }
    }
}
