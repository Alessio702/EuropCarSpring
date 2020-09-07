package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import com.example.demo.model.User;
import com.example.demo.service.UtenteService;
import com.example.demo.service.VenditoreService;

@Controller
@RequestMapping(value = "/Utente")
public class UtenteController {

	@Autowired
	UtenteService utenteService;
	
	@Autowired
	VenditoreService venditoreService;
	
	@GetMapping(value = "/ListaUtenti")
	public ModelAndView listaUtenti() {
		ModelAndView model = new ModelAndView("/Utente/ListaUtenti");		// jsp
		List<User> listaUtenti = utenteService.getAllUtenti();
		model.addObject("elencoUtenti", listaUtenti);
		
		
		return model;
	}
	
	@GetMapping(value = "/AddUtente")
	public ModelAndView aggiungiUtente() {
		ModelAndView model = new ModelAndView("/Utente/AddUtente");
		model.addObject("oggettoUtente", new User());
		model.addObject("elencoVenditori", venditoreService.getAllVenditori());
		
		return model;
	}
	
	@PostMapping(value = "/saveUtente")
	public ModelAndView salvaUtente(@Valid @ModelAttribute("oggettoUtente") User oUtente, BindingResult bindingresult) {
		ModelAndView model = new ModelAndView();
		
		if (bindingresult.hasErrors()) {
			model.setViewName("Utente/AddUtente");
			model.addObject("elencoVenditori", venditoreService.getAllVenditori());
		} else {
			//Cripto la password
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String passwordEncoded = passwordEncoder.encode(oUtente.getPassword());
			oUtente.setPassword(passwordEncoded);
			
			// Setto i campi rimanenti
			oUtente.setActive(1);
			
			if ("USER".equals(oUtente.getRoles())) {
				oUtente.setPermissions("ACCESS_USER");
			} else {
				oUtente.setPermissions("ACCESS_ADMIN");
			}
			
			utenteService.saveUser(oUtente);
			
			model.addObject("elencoUtenti", utenteService.getAllUtenti());
			model.setViewName("/Utente/ListaUtenti");
		}
		
		return model;
	}
	
	@GetMapping(value = "/DeleteUtente/{id}")
	public ModelAndView deleteUtenti(@PathVariable("id") Integer id, HttpSession sessionObject) {
		ModelAndView model = new ModelAndView("/Utente/ListaUtenti");
		Long idConverted = Long.valueOf(id);
		
		User utente = utenteService.getUtenteById(idConverted);
		String usernameUserLogged = (String) sessionObject.getAttribute("usernameUserLogged");
		String errorMsg = "";
		
		if (utente.getUsername().equals(usernameUserLogged)) {
			errorMsg = "Non è possibile eliminare l'utente attualmente loggato!";
		} else {
			utenteService.deleteById(idConverted);
		}
		
		model.addObject("errorMsg", errorMsg);
		model.addObject("elencoUtenti", utenteService.getAllUtenti());
		
		return model;
	}
}
