package com.example.demo.service;

import java.util.List;

import com.example.demo.model.FatturaPassivaDettaglio;

public interface FatturaPassivaDettaglioService {

	public List<FatturaPassivaDettaglio> getAllDettagli();
	
	public List<FatturaPassivaDettaglio> getDettagliPerAnno(Integer idAnno);
	
	public FatturaPassivaDettaglio getDettaglioById(Integer idDettaglio);
	
	public FatturaPassivaDettaglio saveOrUpdate(FatturaPassivaDettaglio oFatturaPassivaDettaglio); 
	
	public void deleteDettaglioById(Integer idDettaglio);
	
	public void deleteDettaglio(FatturaPassivaDettaglio oDettaglio);
	
}
