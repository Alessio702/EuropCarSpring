package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.OrdineAcquistoDettaglio;
import com.example.demo.repository.OrdineAcquistoDettaglioRepository;

@Service
@Transactional
public class OrdineAcquistoDettaglioServiceImpl implements OrdineAcquistoDettaglioService {
	
	@Autowired
	OrdineAcquistoDettaglioRepository ordineAcquistoDettaglioRepository;

	@Override
	public List<OrdineAcquistoDettaglio> getAllDettagli() {
		return (List<OrdineAcquistoDettaglio>) ordineAcquistoDettaglioRepository.findAll();
	}

	@Override
	public OrdineAcquistoDettaglio getDettaglioById(Integer idDettaglio) {
		return ordineAcquistoDettaglioRepository.findById(idDettaglio).get();
	}

	@Override
	public OrdineAcquistoDettaglio saveOrUpdate(OrdineAcquistoDettaglio oOrdineAcquistoDettaglio) {
		return ordineAcquistoDettaglioRepository.save(oOrdineAcquistoDettaglio);
	}

	@Override
	public void deleteDettaglioById(Integer idDettaglio) {
		ordineAcquistoDettaglioRepository.deleteById(idDettaglio);
	}

	@Override
	public void deleteDettaglio(OrdineAcquistoDettaglio oDettaglio) {
		ordineAcquistoDettaglioRepository.delete(oDettaglio);
		
	}

	@Override
	public List<OrdineAcquistoDettaglio> getDettagliPerSottoCategorieEAnno(Integer idAnno, String dataInizio, String dataFine) {
		return ordineAcquistoDettaglioRepository.getDettagliPerSottoCategorieEAnno(idAnno, dataInizio, dataFine);
	}
	
	
}
