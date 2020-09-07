package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Preventivo;

public interface PreventivoService {

	public List<Preventivo> getAllPreventivi();
	
	public Preventivo getPreventivoById(Integer idFornitore);
	
	public Preventivo saveOrUpdate(Preventivo oPreventivo); 
	
	public void deletePreventivoById(Integer idFornitore);
	
	public List<Preventivo> getPreventiviPerFornitore(Integer idFornitore);
}
