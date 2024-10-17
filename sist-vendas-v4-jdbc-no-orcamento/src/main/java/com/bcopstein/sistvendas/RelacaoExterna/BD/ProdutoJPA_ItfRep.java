package com.bcopstein.sistvendas.RelacaoExterna.BD;

import org.springframework.data.repository.ListCrudRepository;

import com.bcopstein.sistvendas.interfaceAdaptadora.Repositories.Produto;

public interface ProdutoJPA_ItfRep extends ListCrudRepository<Produto, Long>{
}
