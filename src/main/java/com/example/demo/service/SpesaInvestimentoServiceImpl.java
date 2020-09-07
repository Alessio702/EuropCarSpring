package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.SpesaInvestimento;
import com.example.demo.repository.SpesaInvestimentoRepository;

@Service
@Transactional
public class SpesaInvestimentoServiceImpl implements SpesaInvestimentoService {
	
	@Autowired
	SpesaInvestimentoRepository spesaInvestimentoRepository;

	@Override
	public List<SpesaInvestimento> getAllSpeseInvestimento() {
		return (List<SpesaInvestimento>) spesaInvestimentoRepository.findAll();
	}
	
	@Override
	public List<SpesaInvestimento> getSpeseInvestimentoPerAnno(Integer idAnno) {
		return spesaInvestimentoRepository.getSpeseInvPerAnno(idAnno);
	}

	@Override
	public SpesaInvestimento getSpesaInvestimentoById(Integer idSpesaInvestimento) {
		return spesaInvestimentoRepository.findById(idSpesaInvestimento).get();
	}

	@Override
	public SpesaInvestimento saveOrUpdate(SpesaInvestimento oSpesaInvestimento) {
		return spesaInvestimentoRepository.save(oSpesaInvestimento);
	}

	@Override
	public void deleteSpesaInvestimentoById(Integer idSpesaInvestimento) {
		spesaInvestimentoRepository.deleteById(idSpesaInvestimento);
	}
}
