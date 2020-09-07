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

import com.example.demo.model.Progetto;
import com.example.demo.service.ProgettoService;

@Controller
@RequestMapping(value = "/Progetto")
public class ProgettoController {

	@Autowired
	ProgettoService progettoService;

	@GetMapping (value = "/ListaProgetti")
	public ModelAndView listaProgetti() {
		ModelAndView model = new ModelAndView("Progetto/ListaProgetti");
		List<Progetto> listaProgetti = progettoService.getAllProgetti();
		
		model.addObject("elencoProgetti", listaProgetti);
		
		return model;
	}

	@GetMapping (value = "/AddProgetto")
	public ModelAndView addProgetto() {
		ModelAndView model = new ModelAndView("/Progetto/AddEditProgetto");
		Progetto oProgetto = new Progetto();
		
		model.addObject("oggettoProgetto", oProgetto);
		
		return model;
	}

	@GetMapping (value = "/EditProgetto/{id}")
	public ModelAndView editProgetto(@PathVariable("id") Integer id) {
		ModelAndView model = new ModelAndView("Progetto/AddEditProgetto");
		Progetto oProgetto = progettoService.getProgettoById(id);
		
		model.addObject("oggettoProgetto", oProgetto);
		
		return model;
	}

	@PostMapping (value = "/SaveProgetto")
	public ModelAndView saveProgetto(@Valid @ModelAttribute("oggettoProgetto") Progetto oProgetto, BindingResult bindingresult) {
		
		if (bindingresult.hasErrors()) {
			ModelAndView model = new ModelAndView("Progetto/AddEditProgetto");
			
			return model;
		} else {
			progettoService.saveOrUpdate(oProgetto);
			
			return new ModelAndView("redirect:/Progetto/ListaProgetti");
		}
	}
	
	@GetMapping (value = "/DeleteProgetto/{id}")
	public ModelAndView deleteArea(@PathVariable("id") Integer id) {
		progettoService.deleteProgettoById(id);
		
		return new ModelAndView("redirect:/Progetto/ListaProgetti");
	}
}