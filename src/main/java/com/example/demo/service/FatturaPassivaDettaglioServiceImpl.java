package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.FatturaPassivaDettaglio;
import com.example.demo.repository.FatturaPassivaDettaglioRepository;

@Service
@Transactional
public class FatturaPassivaDettaglioServiceImpl implements FatturaPassivaDettaglioService {
	
	@Autowired
	FatturaPassivaDettaglioRepository dettaglioRepository;

	@Override
	public List<FatturaPassivaDettaglio> getAllDettagli() {
		return (List<FatturaPassivaDettaglio>) dettaglioRepository.findAll();
	}

	@Override
	public List<FatturaPassivaDettaglio> getDettagliPerAnno(Integer idAnno) {
		return dettaglioRepository.getFattureDettagliPerAnno(idAnno);
	}
	
	@Override
	public FatturaPassivaDettaglio getDettaglioById(Integer idDettaglio) {
		return dettaglioRepository.findById(idDettaglio).get();
	}

	@Override
	public FatturaPassivaDettaglio saveOrUpdate(FatturaPassivaDettaglio oFatturaPassivaDettaglio) {
		return dettaglioRepository.save(oFatturaPassivaDettaglio);
	}

	@Override
	public void deleteDettaglioById(Integer idDettaglio) {
		dettaglioRepository.deleteById(idDettaglio);
		
	}

	@Override
	public void deleteDettaglio(FatturaPassivaDettaglio oDettaglio) {
		dettaglioRepository.delete(oDettaglio);
		
	}
}
