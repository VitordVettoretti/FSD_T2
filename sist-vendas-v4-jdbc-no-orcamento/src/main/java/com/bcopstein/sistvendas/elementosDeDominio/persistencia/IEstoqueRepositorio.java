package com.bcopstein.sistvendas.elementosDeDominio.persistencia;

import java.util.List;

import com.bcopstein.sistvendas.elementosDeDominio.modelos.ItemDeEstoqueModel;
import com.bcopstein.sistvendas.elementosDeDominio.modelos.ProdutoModel;

public interface IEstoqueRepositorio {
    List<ProdutoModel> todos();
    List<ProdutoModel> todosComEstoque();
    int quantidadeEmEstoque(long codigo);
    ItemDeEstoqueModel consultaPorId(long id);
    void salva(ItemDeEstoqueModel item);
}
