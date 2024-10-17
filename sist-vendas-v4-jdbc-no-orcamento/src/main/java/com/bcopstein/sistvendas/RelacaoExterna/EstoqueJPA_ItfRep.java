package com.bcopstein.sistvendas.RelacaoExterna;

import org.springframework.data.repository.ListCrudRepository;

import com.bcopstein.sistvendas.interfaceAdaptadora.Repositories.ItemDeEstoque;

public interface EstoqueJPA_ItfRep extends ListCrudRepository<ItemDeEstoque,Long>{

}
