package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.FatturaPassiva;
import com.example.demo.repository.FatturaPassivaRepository;

@Service
@Transactional
public class FatturaPassivaServiceImpl implements FatturaPassivaService {
	
	@Autowired
	FatturaPassivaRepository fatturaPassivaRepository;

	@Override
	public List<FatturaPassiva> getAllFatturePassive() {
		return (List<FatturaPassiva>) fatturaPassivaRepository.findAll();
	}

	@Override
	public FatturaPassiva getFatturaPassivaById(Integer idFatturaPassiva) {
		return fatturaPassivaRepository.findById(idFatturaPassiva).get();
	}

	@Override
	public FatturaPassiva saveOrUpdate(FatturaPassiva oFatturaPassiva) {
		return fatturaPassivaRepository.save(oFatturaPassiva);
	}

	@Override
	public void deleteFatturaPassivaById(Integer idFatturaPassiva) {
		fatturaPassivaRepository.deleteById(idFatturaPassiva);
	}

	@Override
	public List<FatturaPassiva> getFatturePassivePerFornitore(Integer idFornitore) {
		return fatturaPassivaRepository.getFatturePerFornitore(idFornitore);
	}


	
}
