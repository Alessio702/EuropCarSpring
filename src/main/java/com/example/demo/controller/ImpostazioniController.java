package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.User;
import com.example.demo.service.UtenteService;

@Controller
@RequestMapping(value = "/Impostazioni")
public class ImpostazioniController {
	
	@Autowired
	UtenteService utenteService;
	
	
	@GetMapping(value = "/Lista")
	public ModelAndView lista() {
		ModelAndView model = new ModelAndView();
		model.setViewName("/Menu/ListaImpostazioni");
		
		return model;
	}
	
	@GetMapping(value = "/CambiaPassword")
	public ModelAndView cambiaPassword(HttpSession sessionObject) {
		ModelAndView model = new ModelAndView();
		model.setViewName("/Impostazioni/EditPassword");
		
		User utente = (User) sessionObject.getAttribute("oggettoUtente");
		model.addObject("userUsername", utente.getUsername());
		model.addObject("oldPassword", utente.getPassword());
		model.addObject("oggettoUtente", new User());
		
		
		return model;
	}
	
	@PostMapping(value = "/savePassword")
	public ModelAndView savePassword(@ModelAttribute("oggettoUtente") User oUtente, HttpSession sessionObject) {
		ModelAndView model = new ModelAndView();
		model.setViewName("/Menu/ListaImpostazioni");
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		User utente = (User) sessionObject.getAttribute("oggettoUtente");
		String passwordEncoded = passwordEncoder.encode(oUtente.getPassword());
		utenteService.setPasswordAndSave(passwordEncoded, utente);
		
		model.addObject("esitoEditPassword", "Password modificata con successo!");
		
		return model;
	}
	
}
