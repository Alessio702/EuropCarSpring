package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {
	
	@Autowired
	UtenteService utenteService;
	
	public UserPrincipalDetailsService() {
//		this.utenteRepository = utenteRepository;
	}
			
			
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User utente = utenteService.getUtenteByUsername(username);
		UserPrincipal userPrincipal = new UserPrincipal(utente);
		
		
		return userPrincipal;
	}

}
