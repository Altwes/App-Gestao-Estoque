package com.almoxarifado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.almoxarifado.domain.EntradaProdutoDetalhe;

@Repository
public interface EntradaProdutoDetalheRepository extends JpaRepository<EntradaProdutoDetalhe, Long>{

}
