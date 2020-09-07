package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.TipoVenditore;
import com.example.demo.repository.TipoVenditoreRepository;

@Service
@Transactional
public class TipoVenditoreServiceImpl implements TipoVenditoreService {

	@Autowired
	TipoVenditoreRepository tipoRepository;

	@Override
	public List<TipoVenditore> getAllTipiVenditore() {
		return (List<TipoVenditore>) tipoRepository.findAll();
	}

	@Override
	public TipoVenditore getTipoVenditoreById(Integer idTipoVenditore) {
		return tipoRepository.findById(idTipoVenditore).get();
	}

	@Override
	public TipoVenditore saveOrUpdate(TipoVenditore oTipoVenditore) {
		return tipoRepository.save(oTipoVenditore);
	}

	@Override
	public void deleteTipoVenditoreById(Integer idTipoVenditore) {
		tipoRepository.deleteById(idTipoVenditore);
	}
	
	
}