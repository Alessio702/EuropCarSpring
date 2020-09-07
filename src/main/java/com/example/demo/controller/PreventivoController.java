package com.example.demo.controller;

import java.util.List;

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

import com.example.demo.model.Fornitore;
import com.example.demo.model.Preventivo;
import com.example.demo.service.FornitoreService;
import com.example.demo.service.PreventivoService;

@Controller
@RequestMapping(value = "/Preventivo")
public class PreventivoController {
	
	@Autowired
	PreventivoService preventivoService;
	
	@Autowired
	FornitoreService fornitoreService;
	
	@GetMapping(value = "/ListaPreventivi")
	public ModelAndView lista() {
		ModelAndView model = new ModelAndView("/Preventivo/ListaPreventivi");
		
		model.addObject("oggettoFornitore", new Fornitore());
		model.addObject("elencoFornitori", fornitoreService.getAllFornitori());
		model.addObject("elencoPreventivi", preventivoService.getAllPreventivi());
		
		return model;
	}
	
	@PostMapping(value = "/CercaPreventiviPerFornitore")
	public ModelAndView filtraPerFornitore(@ModelAttribute("oggettoFornitore") Fornitore oFornitore, HttpSession sessionObject) {
		ModelAndView model = new ModelAndView("/Preventivo/ListaPreventivi");
		List<Fornitore> listaFornitori = fornitoreService.getAllFornitori();
		
		model.addObject("elencoFornitori", listaFornitori);
		model.addObject("oggettoFornitore", oFornitore);
		model.addObject("elencoPreventivi", preventivoService.getPreventiviPerFornitore(oFornitore.getIdFornitore()));
		sessionObject.setAttribute("oggettoFornitore", oFornitore);		
		
		return model;
	}
	
	@GetMapping(value = "/AddPreventivo")
	public ModelAndView addPreventivo() {
		ModelAndView model = new ModelAndView("/Preventivo/AddEditPreventivo");

		model.addObject("oggettoPreventivo", new Preventivo());
		model.addObject("elencoFornitori", fornitoreService.getAllFornitori());
		
		return model;
	}
	
	@GetMapping(value = "/EditPreventivo/{id}")
	public ModelAndView editPreventivo(@PathVariable("id") Integer id) {
		ModelAndView model = new ModelAndView("/Preventivo/AddEditPreventivo");
		
		model.addObject("oggettoPreventivo", preventivoService.getPreventivoById(id));
		model.addObject("elencoFornitori", fornitoreService.getAllFornitori());
		
		return model;
	}
	
	@PostMapping(value = "/SavePreventivo")
	public ModelAndView savePreventivo(@Valid @ModelAttribute("oggettoPreventivo") Preventivo oPreventivo, BindingResult bindingResult, HttpSession sessionObject) {
		ModelAndView model = new ModelAndView();
		model.addObject("elencoFornitori", fornitoreService.getAllFornitori());
		
		if (bindingResult.hasErrors()) {
			model.setViewName("/Preventivo/AddEditPreventivo");
		} else {
			preventivoService.saveOrUpdate(oPreventivo);
			Fornitore oFornitore = (Fornitore) sessionObject.getAttribute("oggettoFornitore");
			model.addObject("oggettoFornitore", oFornitore);
			model.addObject("elencoPreventivi", preventivoService.getPreventiviPerFornitore(oFornitore.getIdFornitore()));
			model.setViewName("/Preventivo/ListaPreventivi");
		}
		
		return model;
	}
	
	@GetMapping (value = "/DeletePreventivo/{id}")
	public ModelAndView deletePreventivo(@PathVariable("id") Integer id, HttpSession sessionObject) {
		ModelAndView model = new ModelAndView();
		
		model.addObject("oggettoFornitore", (Fornitore) sessionObject.getAttribute("oggettoFornitore"));
		preventivoService.deletePreventivoById(id);
		model.setViewName("redirect:/Preventivo/ListaPreventivi");
		
		return model;
	}
	
	
}
