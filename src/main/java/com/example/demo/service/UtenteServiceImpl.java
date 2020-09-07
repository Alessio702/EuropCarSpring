package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
@Transactional
public class UtenteServiceImpl implements UtenteService {

	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> getAllUtenti() {
		return userRepository.findAll();
	}

	@Override
	public User getUtenteByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	@Override
	public User getUtenteById(Long idUser) {
		return userRepository.findById(idUser).get();
	}	
	
	@Override
	public void setPasswordAndSave(String password, User utente) {
		utente.setPassword(password);
		
		userRepository.save(utente);
	}

	@Override
	public void saveAllUsers(List<User> utenti) {
		userRepository.saveAll(utenti);		
	}
	
	@Override
	public void saveUser(User oUtente) {
		userRepository.save(oUtente);
	}

	@Override
	public void deleteAllUsers() {
		userRepository.deleteAll();
	}

	@Override
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public void deleteUser(User utente) {
		userRepository.delete(utente);
	}

}