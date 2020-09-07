package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.AnnoContabile;
import com.example.demo.repository.AnnoContabileRepository;

@Service
@Transactional
public class AnnoContabileServiceImpl implements AnnoContabileService {
	
	@Autowired
	AnnoContabileRepository annoRepository;

	@Override
	public List<AnnoContabile> getAllAnni() {
		
		List<AnnoContabile> listaAnni = (List<AnnoContabile>) annoRepository.findAll();
		return listaAnni;
	}

	@Override
	public AnnoContabile getAnnoById(Integer id) {
		
		AnnoContabile oAnno = annoRepository.findById(id).get();
		return oAnno;
	}

	@Override
	public void saveOrUpdate(AnnoContabile oAnno) {
		annoRepository.save(oAnno);
	}

	@Override
	public void delete(AnnoContabile oAnno) {
		annoRepository.delete(oAnno);
	}

	
	
	
	

}
