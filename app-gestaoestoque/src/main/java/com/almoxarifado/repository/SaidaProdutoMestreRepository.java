package com.almoxarifado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.almoxarifado.domain.SaidaProdutoMestre;

@Repository
public interface SaidaProdutoMestreRepository extends JpaRepository<SaidaProdutoMestre, Long> {

}
