package com.example.demo.configuration.security;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.model.Venditore;
import com.example.demo.service.UtenteService;
import com.example.demo.service.VenditoreService;
@Service
public class DbInit implements CommandLineRunner {
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	VenditoreService venditoreService;
	
	@Autowired
	UtenteService utenteService;
	
	public DbInit(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	
	@Override
	public void run(String... args) {
		// Delete all users
		utenteService.deleteAllUsers();
		
		// Solo per prova connessione db postgre
//		TipoVenditore oTipoUser = new TipoVenditore(822, "UTENTE");
//		TipoVenditore oTipoAdmin = new TipoVenditore(824, "ADMIN");
//		
//		Venditore oggUser = new Venditore(823, "Alessio", "Di Giorgio", "Via Adige 11", "3341920213", oTipoUser);
//		Venditore oggAdmin = new Venditore(827, "Alex", "Prezioso", "Via Teramo", "3205689149", oTipoAdmin);
		
		
		
		
		
		Venditore oggUser = venditoreService.getVenditoreById(823);
		Venditore oggAdmin = venditoreService.getVenditoreById(825);
		
		
		
		// Crypting passwords
		User user = new User("Alessio", passwordEncoder.encode("123"), "USER", "ACCESS_USER", oggUser);
		User admin = new User("admin", passwordEncoder.encode("admin123"), "ADMIN", "ACCESS_ADMIN", oggAdmin);
		
		List<User> users = Arrays.asList(user, admin);
		
		// Save to db
		utenteService.saveAllUsers(users);	
	}

}
