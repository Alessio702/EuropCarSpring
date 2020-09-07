package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.FatturaPassivaDettaglio;

public interface FatturaPassivaDettaglioRepository extends CrudRepository<FatturaPassivaDettaglio, Integer> {
	
	@Query("SELECT f FROM FatturaPassivaDettaglio f WHERE f.oSpesaInvestimento.oSottoCategoria.oAreaInvestimento.oAnnoContabile.idAnno = :idAnno")
	List<FatturaPassivaDettaglio> getFattureDettagliPerAnno(Integer idAnno);

}