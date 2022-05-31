package com.almoxarifado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.almoxarifado.domain.SaidaProdutoDetalhe;

@Repository
public interface SaidaProdutoDetalheRepository extends JpaRepository<SaidaProdutoDetalhe, Long>{

}
