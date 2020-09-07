package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Venditore;

public interface VenditoreService {

	public List<Venditore> getAllVenditori();
	
	public Venditore getVenditoreById(Integer idVenditore);
	
	public Venditore saveOrUpdate(Venditore oVenditore); 
	
	public void deleteVenditoreById(Integer idVenditore);
}
