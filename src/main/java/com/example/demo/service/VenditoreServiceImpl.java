package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Venditore;
import com.example.demo.repository.VenditoreRepository;

@Service
@Transactional
public class VenditoreServiceImpl implements VenditoreService {

	@Autowired
	VenditoreRepository venditoreRepository;

	@Override
	public List<Venditore> getAllVenditori() {
		return (List<Venditore>) venditoreRepository.findAll();
	}

	@Override
	public Venditore getVenditoreById(Integer idVenditore) {
		return venditoreRepository.findById(idVenditore).get();
	}

	@Override
	public Venditore saveOrUpdate(Venditore oVenditore) {
		return venditoreRepository.save(oVenditore);
	}

	@Override
	public void deleteVenditoreById(Integer idVenditore) {
		venditoreRepository.deleteById(idVenditore);
	}

	
	
}