package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Preventivo;

public interface PreventivoRepository extends CrudRepository<Preventivo, Integer> {
	
	@Query("SELECT f FROM Preventivo f WHERE f.oFornitore.idFornitore = :idFornitore")
	List<Preventivo> getPreventiviPerFornitore(Integer idFornitore);

}