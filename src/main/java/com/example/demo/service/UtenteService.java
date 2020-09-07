package com.example.demo.service;

import java.util.List;

import com.example.demo.model.User;

public interface UtenteService {

	public List<User> getAllUtenti();
	
	public User getUtenteByUsername(String username);
	
	public User getUtenteById(Long idUser);
	
	public void setPasswordAndSave(String password, User utente);
	
	public void saveAllUsers(List<User> utenti);
	
	public void saveUser(User oUtente);
	
	public void deleteAllUsers();
	
	public void deleteById(Long id);
	
	public void deleteUser(User utente);
}
