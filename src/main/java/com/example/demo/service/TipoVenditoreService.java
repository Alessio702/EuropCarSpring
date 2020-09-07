package com.example.demo.service;

import java.util.List;

import com.example.demo.model.TipoVenditore;

public interface TipoVenditoreService {

	public List<TipoVenditore> getAllTipiVenditore();
	
	public TipoVenditore getTipoVenditoreById(Integer idTipoVenditore);
	
	public TipoVenditore saveOrUpdate(TipoVenditore oTipoVenditore); 
	
	public void deleteTipoVenditoreById(Integer idTipoVenditore);
}
