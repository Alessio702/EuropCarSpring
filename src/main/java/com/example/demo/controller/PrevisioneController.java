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

import com.example.demo.model.AnnoContabile;
import com.example.demo.model.Previsione;
import com.example.demo.service.AreaService;
import com.example.demo.service.AziendaService;
import com.example.demo.service.PrevisioneService;
import com.example.demo.service.SottoCategoriaService;
import com.example.demo.service.VenditoreService;

@Controller
@RequestMapping(value = "/Previsione")
public class PrevisioneController {

	@Autowired
	PrevisioneService previsioneService;
	
	@Autowired
	SottoCategoriaService sottoCatService;
	
	@Autowired
	AziendaService aziendaService;
	
	@Autowired
	AreaService areaService;
	
	@Autowired
	VenditoreService venditoreService;

	@GetMapping (value = "/ListaPrevisioni")
	public ModelAndView listaPrevisioni(HttpSession sessionObject) {
		ModelAndView model = new ModelAndView();
		String path = "Previsione/ListaPrevisioni";
		List<Previsione> listaPrevisioni = previsioneService.getAllPrevisioni();
		
		model.addObject("elencoPrevisioni", listaPrevisioni);
		sessionObject.setAttribute("url", path);
		
		return model;
	}

	@GetMapping (value = "/AddPrevisione")
	public ModelAndView addPrevisione(HttpSession sessionObject) {
		ModelAndView model = new ModelAndView();
		AnnoContabile oAnno = (AnnoContabile) sessionObject.getAttribute("oggettoAnno");
		Previsione oPrevisione = new Previsione();
		oPrevisione.setAnnoDiRiferimento(oAnno.getDescrizione());
			
		model.addObject("oggettoPrevisione", oPrevisione);
		model.addObject("elencoAreeGeo", areaService.getAllAree());
		model.addObject("elencoSottoCategorie", sottoCatService.getAllSottoCategorie());
		model.addObject("elencoAziende", aziendaService.getAllAziende());
		model.addObject("elencoVenditori", venditoreService.getAllVenditori());
		model.setViewName("/Previsione/AddEditPrevisione");
		
		return model;
	}

	@GetMapping (value = "/EditPrevisione/{id}")
	public ModelAndView editPrevisione(@PathVariable("id") Integer id) {
		ModelAndView model = new ModelAndView("Previsione/AddEditPrevisione");
		Previsione oPrevisione = previsioneService.getPrevisioneById(id);
		
		model.addObject("oggettoPrevisione", oPrevisione);
		model.addObject("elencoAreeGeo", areaService.getAllAree());
		model.addObject("elencoSottoCategorie", sottoCatService.getAllSottoCategorie());
		model.addObject("elencoAziende", aziendaService.getAllAziende());
		model.addObject("elencoVenditori", venditoreService.getAllVenditori());
		model.setViewName("/Previsione/AddEditPrevisione");
		
		return model;
	}

	@PostMapping (value = "/SavePrevisione")
	public ModelAndView savePrevisione(@Valid @ModelAttribute("oggettoPrevisione") Previsione oPrevisione, BindingResult bindingresult) {
		
		oPrevisione.getDataVisita();
		
		if (bindingresult.hasErrors()) {
			ModelAndView model = new ModelAndView();
			model.setViewName("Previsione/AddEditPrevisione");
			
			return model;
		} else {
			previsioneService.saveOrUpdate(oPrevisione);
			
			return new ModelAndView("redirect:/Previsione/ListaPrevisioni");
		}
	}
	
	@GetMapping (value = "/DeletePrevisione/{id}")
	public ModelAndView deletePrevisione(@PathVariable("id") Integer id) {
		previsioneService.deletePrevisioneById(id);
		
		return new ModelAndView("redirect:/Previsione/ListaPrevisioni");
	}
}