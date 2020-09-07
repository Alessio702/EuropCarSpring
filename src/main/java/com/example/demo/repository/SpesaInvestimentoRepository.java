package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.SpesaInvestimento;

public interface SpesaInvestimentoRepository extends CrudRepository<SpesaInvestimento, Integer> {

	@Query("SELECT f FROM SpesaInvestimento f WHERE f.oSottoCategoria.oAreaInvestimento.oAnnoContabile.idAnno = :idAnno")
	List<SpesaInvestimento> getSpeseInvPerAnno(Integer idAnno);
}
