package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.OrdineAcquistoDettaglio;

public interface OrdineAcquistoDettaglioRepository extends CrudRepository<OrdineAcquistoDettaglio, Integer> {
	
	@Query("SELECT f FROM OrdineAcquistoDettaglio f WHERE f.oSpesaInvestimento.oSottoCategoria.oAreaInvestimento.oAnnoContabile.idAnno = ?1"
			+ " AND f.oOrdineAcquisto.data BETWEEN ?2 AND ?3")
	List<OrdineAcquistoDettaglio> getDettagliPerSottoCategorieEAnno(Integer idAnno, String dataInizio, String dataFine);
	
//	@Query("SELECT f FROM OrdineAcquistoDettaglio f WHERE f.oSpesaInvestimento.oSottoCategoria.oAreaInvestimento.oAnnoContabile.idAnno = :idAnno")
//	List<OrdineAcquistoDettaglio> getDettagliPerAnno(Integer idAnno);
}