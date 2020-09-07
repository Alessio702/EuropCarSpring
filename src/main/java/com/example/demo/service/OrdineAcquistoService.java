package com.example.demo.service;

import java.util.List;

import com.example.demo.model.OrdineAcquisto;

public interface OrdineAcquistoService {

	public List<OrdineAcquisto> getAllOrdiniAcquisto();
	
	public OrdineAcquisto getOrdineAcquistoById(Integer idOrdineAcquisto);
	
	public OrdineAcquisto saveOrUpdate(OrdineAcquisto oOrdineAcquisto); 
	
	public void deleteOrdineAcquistoById(Integer idSpesaIOrdineAcquisto);
	
	public List<OrdineAcquisto> getOrdiniAcquistoPerFornitore(Integer idFornitore);
}
