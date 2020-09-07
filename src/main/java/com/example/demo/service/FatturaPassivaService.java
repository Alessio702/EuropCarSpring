package com.example.demo.service;

import java.util.List;

import com.example.demo.model.FatturaPassiva;

public interface FatturaPassivaService {

	public List<FatturaPassiva> getAllFatturePassive();
	
	public FatturaPassiva getFatturaPassivaById(Integer idFatturaPassiva);
	
	public FatturaPassiva saveOrUpdate(FatturaPassiva oFatturaPassiva); 
	
	public void deleteFatturaPassivaById(Integer idFatturaPassiva);
	
	public List<FatturaPassiva> getFatturePassivePerFornitore(Integer idFornitore);
}
