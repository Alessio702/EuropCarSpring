package com.example.demo.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Venditore;
import com.example.demo.service.TipoVenditoreService;
import com.example.demo.service.VenditoreService;

@Controller
@RequestMapping(value = "/Venditore")
public class VenditoreController {
	
	@Autowired
	VenditoreService venditoreService;
	
	@Autowired
	TipoVenditoreService tipoVenditoreService;
	
	
//	@Autowired
//	BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping(value = "/ListaVenditori")
	public ModelAndView lista() {
		ModelAndView model = new ModelAndView("/Venditore/ListaVenditori");
		model.addObject("elencoVenditori", venditoreService.getAllVenditori());
		
		return model;
	}
	
	@GetMapping(value = "/AddVenditore")
	public ModelAndView addVenditore() {
		ModelAndView model = new ModelAndView("/Venditore/AddEditVenditore");
		model.addObject("oggettoVenditore", new Venditore());
		model.addObject("elencoTipoVenditore", tipoVenditoreService.getAllTipiVenditore());
		
		return model;
	}
	
	@GetMapping(value = "/EditVenditore/{id}")
	public ModelAndView editVenditore(@PathVariable("id") Integer id, HttpSession sessionObject) {
		ModelAndView model = new ModelAndView("/Venditore/AddEditVenditore");
		Venditore oVenditore = venditoreService.getVenditoreById(id);
		model.addObject("oggettoVenditore", oVenditore);
		model.addObject("elencoTipoVenditore", tipoVenditoreService.getAllTipiVenditore());
		
		// Criptare password utente
//		Utente oUtente = (Utente) sessionObject.getAttribute("oggettoUtente");
//		if (oUtente != null) {
//			oUtente.setoVenditore(oVenditore);
//			oUtente.setPassword(passwordEncoder.encode(oUtente.getPassword()));
//			
//			utenteService.saveOrUpdate(oUtente);
//		}
		
		return model;
	}
	
	@PostMapping(value = "/SaveVenditore")
	public ModelAndView saveVenditore(@Valid @ModelAttribute("oggettoVenditore") Venditore oVenditore, BindingResult bindingResult, HttpSession sessionObject) {
		ModelAndView model = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			model.setViewName("/Venditore/AddEditVenditore");
		} else {
			venditoreService.saveOrUpdate(oVenditore);
			model.addObject("elencoVenditori", venditoreService.getAllVenditori());
			model.setViewName("/Venditore/ListaVenditori");
		}
		
		return model;
	}
	
	@GetMapping (value = "/DeleteVenditore/{id}")
	public ModelAndView deleteVenditore(@PathVariable("id") Integer id, HttpSession sessionObject) {
		ModelAndView model = new ModelAndView();
		
		venditoreService.deleteVenditoreById(id);
		model.setViewName("redirect:/Venditore/ListaVenditori");
		
		return model;
	}
	
	
}
