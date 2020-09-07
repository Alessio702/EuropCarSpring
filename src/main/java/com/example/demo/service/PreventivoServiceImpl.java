package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Preventivo;
import com.example.demo.repository.PreventivoRepository;

@Service
@Transactional
//@CacheConfig(cacheNames = "{preventivi}")
public class PreventivoServiceImpl implements PreventivoService {
	
	@Autowired
	PreventivoRepository preventivoRepository;

	@Override
//	@Cacheable
	public List<Preventivo> getAllPreventivi() {
		return (List<Preventivo>) preventivoRepository.findAll();
	}

	@Override
//	@Cacheable(value = "preventivo", key = "idPreventivo", sync = true)
	public Preventivo getPreventivoById(Integer idPreventivo) {
		return preventivoRepository.findById(idPreventivo).get();
	}

	@Override
//	@Caching(evict = {
//			@CacheEvict(cacheNames = "articoli", allEntries = true),
//			@CacheEvict(cacheNames = "articolo", key = "#articolo.idPreventivo")
//	})
	public Preventivo saveOrUpdate(Preventivo oPreventivo) {
		return preventivoRepository.save(oPreventivo);
	}

	@Override
//	@Caching(evict = {
//						@CacheEvict(cacheNames = "articoli", allEntries = true),
//						@CacheEvict(cacheNames = "articolo", key = "#articolo.idPreventivo")
//	})
	public void deletePreventivoById(Integer idPreventivo) {
		preventivoRepository.deleteById(idPreventivo);
	}

	@Override
//	@Cacheable(value = "preventivo", key = "oFornitore.idFornitore", sync = true)
	public List<Preventivo> getPreventiviPerFornitore(Integer idFornitore) {
		return preventivoRepository.getPreventiviPerFornitore(idFornitore);
	}

	// Con la cache non funziona, da rivedere	
}
