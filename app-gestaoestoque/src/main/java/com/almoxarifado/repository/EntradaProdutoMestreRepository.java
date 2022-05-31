package com.almoxarifado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.almoxarifado.domain.EntradaProdutoMestre;

@Repository
public interface EntradaProdutoMestreRepository extends JpaRepository<EntradaProdutoMestre, Long> {
	
}
