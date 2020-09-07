package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.OrdineAcquisto;

public interface OrdineAcquistoRepository extends CrudRepository<OrdineAcquisto, Integer> {
	
	@Query("SELECT f FROM OrdineAcquisto f WHERE f.oFornitore.idFornitore = :idFornitore")
	List<OrdineAcquisto> getOrdiniPerFornitore(Integer idFornitore);

}