package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.AreaInvestimento;
import com.example.demo.repository.AreaInvestimentoRepository;

@Service
@Transactional
public class AreaInvestimentoServiceImpl implements AreaInvestimentoService {
	
	@Autowired
	AreaInvestimentoRepository areaInvRepository;
	
	@Override
	public List<AreaInvestimento> getAllAreeInvestimento() {
		return (List<AreaInvestimento>) areaInvRepository.findAll();
	}
	
	@Override
	public List<AreaInvestimento> getAreaInvPerAnno(Integer idAnno) {
		return areaInvRepository.getAreePerAnno(idAnno);
	}

	@Override
	public AreaInvestimento getAreaInvById(Integer idAreaInvestimento) {
		return areaInvRepository.findById(idAreaInvestimento).get();
	}

	@Override
	public AreaInvestimento saveOrUpdate(AreaInvestimento oAreaInvestimento) {
		return areaInvRepository.save(oAreaInvestimento);
	}

	@Override
	public void deleteAreaInvestimentoById(Integer idAreaInvestimento) {
		areaInvRepository.deleteById(idAreaInvestimento);
	}
}
