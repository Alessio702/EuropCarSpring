package com.example.demo.service;

import java.util.List;

import com.example.demo.model.OrdineAcquistoDettaglio;

public interface OrdineAcquistoDettaglioService {

	public List<OrdineAcquistoDettaglio> getAllDettagli();
	
	public OrdineAcquistoDettaglio getDettaglioById(Integer idDettaglio);
	
	public OrdineAcquistoDettaglio saveOrUpdate(OrdineAcquistoDettaglio oOrdineAcquistoDettaglio); 
	
	public void deleteDettaglioById(Integer idDettaglio);
	
	public void deleteDettaglio(OrdineAcquistoDettaglio oDettaglio);
	
	public List<OrdineAcquistoDettaglio> getDettagliPerSottoCategorieEAnno(Integer idAnno, String dataInizio, String dataFine);
}
