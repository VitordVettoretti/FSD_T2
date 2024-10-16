package com.bcopstein.sistvendas.dominio.persistencia;

import java.util.List;

import com.bcopstein.sistvendas.dominio.modelos.ItemDeEstoqueModel;
import com.bcopstein.sistvendas.dominio.modelos.ProdutoModel;

public interface IEstoqueRepositorio {
    List<ProdutoModel> todos();
    List<ProdutoModel> todosComEstoque();
    int quantidadeEmEstoque(long codigo);
    ItemDeEstoqueModel consultaPorId(long id);
    void salva(ItemDeEstoqueModel item);
}
