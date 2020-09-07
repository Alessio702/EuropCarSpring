package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.AreaInvestimento;

public interface AreaInvestimentoRepository extends CrudRepository<AreaInvestimento, Integer> {

	@Query("SELECT f FROM AreaInvestimento f WHERE f.oAnnoContabile.idAnno = :idAnno")
	List<AreaInvestimento> getAreePerAnno(Integer idAnno);
	
}
