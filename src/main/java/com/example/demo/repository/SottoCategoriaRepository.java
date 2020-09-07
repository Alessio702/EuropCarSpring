package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.SottoCategoria;

public interface SottoCategoriaRepository extends CrudRepository<SottoCategoria, Integer> {
	
	@Query("SELECT f FROM SottoCategoria f WHERE f.oAreaInvestimento.oAnnoContabile.idAnno = :idAnno AND"
											 + " f.oAreaInvestimento.idAreaInvestimento = :idArea")
	List<SottoCategoria> getSottoCategoriePerAreaEAnno(Integer idAnno, Integer idArea);
	
	@Query("SELECT f FROM SottoCategoria f WHERE f.oAreaInvestimento.oAnnoContabile.idAnno = :idAnno")
	List<SottoCategoria> getSottoCategoriePerAnno(Integer idAnno);
}