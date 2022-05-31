package com.almoxarifado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.almoxarifado.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	
	
}
