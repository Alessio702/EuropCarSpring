package com.example.demo.controller;

import java.util.List;

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

import com.example.demo.model.Fornitore;
import com.example.demo.service.FornitoreService;

import io.swagger.annotations.Api;

@Controller
@RequestMapping(value = "/Fornitore")
@Api(value = "fornitore", tags = "Controller operazioni di gestione dati fornitori")
public class FornitoreController {

	@Autowired
	FornitoreService fornitoreService;

	@GetMapping (value = "/ListaFornitori")
	public ModelAndView listaFornitori() {
		ModelAndView model = new ModelAndView("Fornitore/ListaFornitori");
		List<Fornitore> listaFornitori = fornitoreService.getAllFornitori();
		
		model.addObject("elencoFornitori", listaFornitori);
		
		return model;
	}

	@GetMapping (value = "/AddFornitore")
	public ModelAndView addFornitore() {
		ModelAndView model = new ModelAndView("/Fornitore/AddEditFornitore");
		
		model.addObject("oggettoFornitore", new Fornitore());
		
		return model;
	}

	@GetMapping (value = "/EditFornitore/{id}")
	public ModelAndView editFornitore(@PathVariable("id") Integer id) {
		ModelAndView model = new ModelAndView("Fornitore/AddEditFornitore");
		Fornitore oFornitore = fornitoreService.getFornitoreById(id);
		
		model.addObject("oggettoFornitore", oFornitore);
		
		return model;
	}

	@PostMapping (value = "/SaveFornitore")
	public ModelAndView saveFornitore(@Valid @ModelAttribute("oggettoFornitore") Fornitore oFornitore, BindingResult bindingresult) {
		
		if (bindingresult.hasErrors()) {
			ModelAndView model = new ModelAndView("Fornitore/AddEditFornitore");
			
			return model;
		} else {
			fornitoreService.saveOrUpdate(oFornitore);
			
			return new ModelAndView("redirect:/Fornitore/ListaFornitori");
		}
	}
	
	@GetMapping (value = "/DeleteFornitore/{id}")
	public ModelAndView deleteFornitore(@PathVariable("id") Integer id) {
		fornitoreService.deleteFornitoreById(id);
		
		return new ModelAndView("redirect:/Fornitore/ListaFornitori");
	}
}