package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.OrdineAcquistoDettaglio;
import com.example.demo.model.SottoCategoria;
import com.example.demo.repository.OrdineAcquistoDettaglioRepository;
import com.example.demo.repository.SottoCategoriaRepository;

@Service
@Transactional
public class SottoCategoriaServiceImpl implements SottoCategoriaService {
	
	@Autowired
	SottoCategoriaRepository sottoCatRepository;
	
	@Autowired
	OrdineAcquistoDettaglioRepository dettaglioRepository;

	@Override
	public List<SottoCategoria> getAllSottoCategorie() {
		return (List<SottoCategoria>) sottoCatRepository.findAll();
	}
	
	@Override
	public List<SottoCategoria> getSottoCategoriePerAnno(Integer idAnno) {
		return sottoCatRepository.getSottoCategoriePerAnno(idAnno);
	}

	@Override
	public SottoCategoria getSottoCategoriaById(Integer idSottoCategoria) {
		return sottoCatRepository.findById(idSottoCategoria).get();
	}

	@Override
	public SottoCategoria saveOrUpdate(SottoCategoria oSottoCategoria) {
		return sottoCatRepository.save(oSottoCategoria);
	}

	@Override
	public boolean deleteSottoCategoriaById(Integer idSottoCategoria) {
		boolean check = false;
		List<OrdineAcquistoDettaglio> listaDettagli = (List<OrdineAcquistoDettaglio>) dettaglioRepository.findAll(); 
		
		for (int i = 0; i < listaDettagli.size() && !check; i++) {
			int chiaveSottoCat = listaDettagli.get(i).getoSpesaInvestimento().getoSottoCategoria().getIdSottoCategoria();
			if (chiaveSottoCat == idSottoCategoria) {
				check = true;
				break;
			}
		}
		
		if (!check)
			sottoCatRepository.deleteById(idSottoCategoria);
		
		return check;
	}

	@Override
	public boolean riconcilia(List<OrdineAcquistoDettaglio> dettagli) {
		float importo = 0.0f;
		HashMap<SottoCategoria, Float> hashMap = new HashMap<SottoCategoria, Float>();
		List<SottoCategoria> elencoSottoCategorie = new ArrayList<SottoCategoria>();
		
		for (int i = 0; i < dettagli.size(); i++) {
			SottoCategoria oSottoCat = dettagli.get(i).getoSpesaInvestimento().getoSottoCategoria();
			importo = dettagli.get(i).getImporto();
			
			if (hashMap.get(oSottoCat) != null) {
				hashMap.put(oSottoCat, (hashMap.get(oSottoCat) + importo));
			} else {
				// 1° volta per quella sottoCat
				hashMap.put(oSottoCat, importo);
				elencoSottoCategorie.add(oSottoCat);
			}
		}
 		boolean check = true;
 		
		if (elencoSottoCategorie.size() != 0) {
			for (int j = 0; j < elencoSottoCategorie.size(); j++) {
				SottoCategoria sottoCat = elencoSottoCategorie.get(j);
				sottoCat.setBudgetSpeso(hashMap.get(sottoCat));
			}
		} else {
			check = false;
		}
		
		return check;
	}

	@Override
	public List<SottoCategoria> getAllSottoCategoriePerAreaEAnno(Integer idAnno, Integer idAreaInvestimento) {
		return sottoCatRepository.getSottoCategoriePerAreaEAnno(idAnno, idAreaInvestimento);
	}
}