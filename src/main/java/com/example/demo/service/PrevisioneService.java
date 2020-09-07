package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Previsione;

public interface PrevisioneService {

	public List<Previsione> getAllPrevisioni();
	
	public Previsione getPrevisioneById(Integer idPrevisione);
	
	public Previsione saveOrUpdate(Previsione oPrevisione); 
	
	public void deletePrevisioneById(Integer idPrevisione);
}
