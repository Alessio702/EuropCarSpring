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
import com.example.demo.model.AreaInvestimento;
import com.example.demo.model.SottoCategoria;
import com.example.demo.service.AreaInvestimentoService;
import com.example.demo.service.SottoCategoriaService;

@Controller
@RequestMapping(value = "/SottoCategoria")
public class SottoCategoriaController {
	
	@Autowired
	SottoCategoriaService sottoCatService;
	
	@Autowired
	AreaInvestimentoService areaInvService;
	
	@GetMapping(value = "/ListaSottoCategorie")
	public ModelAndView listaSottoCategorie(HttpSession sessionObject) {
		ModelAndView model = new ModelAndView("SottoCategoria/ListaSottoCategorie");
		
		AnnoContabile oAnno = (AnnoContabile) sessionObject.getAttribute("oggettoAnno");
		List<SottoCategoria> listaSottoCategorie = sottoCatService.getSottoCategoriePerAnno(oAnno.getIdAnno());
		
		model.addObject("elencoSottoCategorie", listaSottoCategorie);
		sessionObject.setAttribute("elencoSottoCategorie", listaSottoCategorie);
		
		return model;
	}
	
	@GetMapping (value = "/AddSottoCategoria")
	public ModelAndView addSottoCategoria(HttpSession sessionObject) {
		ModelAndView model = new ModelAndView("SottoCategoria/AddEditSottoCategoria");
		SottoCategoria sottoCategoria = new SottoCategoria();
		AnnoContabile oAnno = (AnnoContabile) sessionObject.getAttribute("oggettoAnno");
		List<AreaInvestimento> listaAree = areaInvService.getAreaInvPerAnno(oAnno.getIdAnno());
		
		model.addObject("elencoAree", listaAree);
		model.addObject("oggettoSottoCategoria", sottoCategoria);
		
		return model;
	}
	
	@GetMapping (value = "/EditSottoCategoria/{id}")
	public ModelAndView editSottoCategoria(@PathVariable("id") Integer id, HttpSession sessionObject) {
		ModelAndView model = new ModelAndView("SottoCategoria/AddEditSottoCategoria");
		SottoCategoria sottoCategoria = sottoCatService.getSottoCategoriaById(id);
		AnnoContabile oAnno = (AnnoContabile) sessionObject.getAttribute("oggettoAnno");
		List<AreaInvestimento> listaAree = areaInvService.getAreaInvPerAnno(oAnno.getIdAnno());
		
		model.addObject("oAreaInvestimento", sottoCategoria.getoAreaInvestimento());
		model.addObject("elencoAree", listaAree);
		model.addObject("oggettoSottoCategoria", sottoCategoria);
		
		return model;
	}
	
	@PostMapping(value = "/SaveSottoCategoria")
	public ModelAndView saveSottoCategoria(@Valid @ModelAttribute("oggettoSottoCategoria") SottoCategoria oSottoCategoria, BindingResult bindingResult) {
		ModelAndView model = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			model.setViewName("SottoCategoria/AddEditSottoCategoria");
			
			return model;
		} else {
			sottoCatService.saveOrUpdate(oSottoCategoria);
			
			model.setViewName("redirect:/SottoCategoria/ListaSottoCategorie");
		}
		
		return model;
	}
	
	@GetMapping (value = "/DeleteSottoCategoria/{id}")
	public ModelAndView deleteSottoCategoria(@PathVariable("id") Integer id, HttpSession sessionObject) {
		ModelAndView model = new ModelAndView("/SottoCategoria/ListaSottoCategorie");
		boolean esito = sottoCatService.deleteSottoCategoriaById(id);
		String descrizione = "";
		
		if (esito)
			descrizione = "La sottocategoria è presente in un ordine acquisto dettaglio o in una spesa investimento!";
		else
			descrizione = "Sottocategoria eliminata con successo";
		
		model.addObject("esito", descrizione);
		AnnoContabile oAnno = (AnnoContabile) sessionObject.getAttribute("oggettoAnno");
		List<SottoCategoria> listaSottoCategorie = sottoCatService.getSottoCategoriePerAnno(oAnno.getIdAnno());
		model.addObject("elencoSottoCategorie", listaSottoCategorie);
		sessionObject.setAttribute("elencoSottoCategorie", listaSottoCategorie);
		
		return model;
	}
}
