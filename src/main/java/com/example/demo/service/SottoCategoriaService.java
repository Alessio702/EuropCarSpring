package com.example.demo.service;

import java.util.List;

import com.example.demo.model.OrdineAcquistoDettaglio;
import com.example.demo.model.SottoCategoria;

public interface SottoCategoriaService {
	
	public List<SottoCategoria> getAllSottoCategorie();

	public List<SottoCategoria> getSottoCategoriePerAnno(Integer idAnno);
	
	public SottoCategoria getSottoCategoriaById(Integer idSottoCategoria);
	
	public SottoCategoria saveOrUpdate(SottoCategoria oSottoCategoria); 
	
	public boolean deleteSottoCategoriaById(Integer idSottoCategoria);
	
	public boolean riconcilia(List<OrdineAcquistoDettaglio> dettagli);
	
	public List<SottoCategoria> getAllSottoCategoriePerAreaEAnno(Integer idAnno, Integer idAreaInvestimento);
}
