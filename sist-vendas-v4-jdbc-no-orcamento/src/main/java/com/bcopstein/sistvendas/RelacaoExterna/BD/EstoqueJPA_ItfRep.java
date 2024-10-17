package com.bcopstein.sistvendas.RelacaoExterna.BD;

import org.springframework.data.repository.ListCrudRepository;

import com.bcopstein.sistvendas.interfaceAdaptadora.Repositories.ItemDeEstoque;

public interface EstoqueJPA_ItfRep extends ListCrudRepository<ItemDeEstoque,Long>{

}
