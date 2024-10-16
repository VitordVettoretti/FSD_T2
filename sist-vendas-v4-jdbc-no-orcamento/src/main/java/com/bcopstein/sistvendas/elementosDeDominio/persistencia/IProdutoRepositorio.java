package com.bcopstein.sistvendas.elementosDeDominio.persistencia;

import java.util.List;

import com.bcopstein.sistvendas.elementosDeDominio.modelos.ProdutoModel;

public interface IProdutoRepositorio {
    List<ProdutoModel> todos();
    ProdutoModel consultaPorId(long codigo);
}
