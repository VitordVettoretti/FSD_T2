package com.bcopstein.sistvendas.elementosDeDominio.servicos;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bcopstein.sistvendas.elementosDeDominio.modelos.ItemDeEstoqueModel;
import com.bcopstein.sistvendas.elementosDeDominio.modelos.ProdutoModel;
import com.bcopstein.sistvendas.elementosDeDominio.persistencia.IEstoqueRepositorio;
import com.bcopstein.sistvendas.elementosDeDominio.persistencia.IProdutoRepositorio;

@Service
public class ServicoDeEstoque{
    private IEstoqueRepositorio estoque;
    private IProdutoRepositorio produtos;
    
    public ServicoDeEstoque(IProdutoRepositorio produtos,IEstoqueRepositorio estoque){
        this.produtos = produtos;
        this.estoque = estoque;
    }
 
    public List<ProdutoModel> produtosDisponiveis(){
        return estoque.todosComEstoque();
    }

    public ProdutoModel produtoPorCodigo(long id){
        return this.produtos.consultaPorId(id);
    }

    public int qtdadeEmEstoque(long id){
        return estoque.quantidadeEmEstoque(id);
    }

    public void baixaEstoque(long id,int qtdade){
        ItemDeEstoqueModel item = estoque.consultaPorId(id);
        if (item == null){
            throw new IllegalArgumentException("Produto inexistente");
        }
        if (item.getQuantidade() < qtdade){
            throw new IllegalArgumentException("Quantidade em estoque insuficiente");
        }
        int novaQuantidade = item.getQuantidade() - qtdade;
        item.setQuantidade(novaQuantidade);
        estoque.salva(item);
    }
}
