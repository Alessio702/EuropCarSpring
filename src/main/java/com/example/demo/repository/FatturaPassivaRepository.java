package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.FatturaPassiva;

public interface FatturaPassivaRepository extends CrudRepository<FatturaPassiva, Integer> {
	
	@Query("SELECT f FROM FatturaPassiva f WHERE f.oFornitore.idFornitore = :idFornitore")
	List<FatturaPassiva> getFatturePerFornitore(Integer idFornitore);
}