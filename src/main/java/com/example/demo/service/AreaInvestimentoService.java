package com.example.demo.service;

import java.util.List;

import com.example.demo.model.AreaInvestimento;

public interface AreaInvestimentoService {
	
	public List<AreaInvestimento> getAllAreeInvestimento();
	
	public List<AreaInvestimento> getAreaInvPerAnno(Integer idAnno);
	
	public AreaInvestimento getAreaInvById(Integer idAreaInvestimento);
	
	public AreaInvestimento saveOrUpdate(AreaInvestimento oAreaInvestimento); 
	
	public void deleteAreaInvestimentoById(Integer idAreaInvestimento);

}
