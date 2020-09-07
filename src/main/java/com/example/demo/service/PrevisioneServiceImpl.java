package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Previsione;
import com.example.demo.repository.PrevisioneRepository;

@Service
@Transactional
public class PrevisioneServiceImpl implements PrevisioneService {

	@Autowired
	PrevisioneRepository previsioneRepository;

	@Override
	public List<Previsione> getAllPrevisioni() {
		return (List<Previsione>) previsioneRepository.findAll();
	}

	@Override
	public Previsione getPrevisioneById(Integer idPrevisione) {
		return previsioneRepository.findById(idPrevisione).get();
	}

	@Override
	public Previsione saveOrUpdate(Previsione oPrevisione) {
		return previsioneRepository.save(oPrevisione);
	}

	@Override
	public void deletePrevisioneById(Integer idPrevisione) {
		previsioneRepository.deleteById(idPrevisione);
	}


	
	
}