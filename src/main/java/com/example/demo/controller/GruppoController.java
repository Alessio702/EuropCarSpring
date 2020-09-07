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

import com.example.demo.model.Gruppo;
import com.example.demo.service.GruppoService;

@Controller
@RequestMapping(value = "/Gruppo")
public class GruppoController {
	
	@Autowired
	GruppoService gruppoService;
	
	@GetMapping(value = "/ListaGruppi")
	public ModelAndView listaGruppi() {
		ModelAndView model = new ModelAndView("Gruppo/ListaGruppo");
		
		List<Gruppo> elencoGruppi = gruppoService.getAllGruppi();
		
		model.addObject("listaGruppi", elencoGruppi);
		
		return model;
	}
	
	@GetMapping (value = "/AddGruppo")
	public ModelAndView addGruppo() {
		ModelAndView model = new ModelAndView("Gruppo/AddEditGruppo");
		Gruppo gruppo = new Gruppo();
		
		model.addObject("oggettoGruppo", gruppo);
		
		return model;
	}
	
	@GetMapping (value = "/EditGruppo/{id}")
	public ModelAndView editGruppo(@PathVariable("id") Integer id) {
		ModelAndView model = new ModelAndView("Gruppo/AddEditGruppo");
		Gruppo gruppo = gruppoService.getGruppoById(id);
		
		model.addObject("oggettoGruppo", gruppo);
		
		return model;
	}
	
	@PostMapping(value = "/SaveGruppo")
	public ModelAndView saveGruppo(@Valid @ModelAttribute("oggettoGruppo") Gruppo oGruppo, BindingResult bindingResult) {
				
		if (bindingResult.hasErrors()) {
			ModelAndView model = new ModelAndView("Gruppo/AddEditGruppo");
			
			return model;
		} else {
			gruppoService.saveOrUpdate(oGruppo);
			
			return new ModelAndView("redirect:/Gruppo/ListaGruppi");
		}
	}
	
	@GetMapping (value = "/DeleteGruppo/{id}")
	public ModelAndView deleteGruppo(@PathVariable("id") Integer id) {
				
		gruppoService.deleteGruppoById(id);
		return new ModelAndView("redirect:/Gruppo/ListaGruppi");
	}
}
