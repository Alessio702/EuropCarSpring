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
import com.example.demo.model.SottoCategoria;
import com.example.demo.model.SpesaInvestimento;
import com.example.demo.service.SottoCategoriaService;
import com.example.demo.service.SpesaInvestimentoService;

@Controller
@RequestMapping(value = "/SpesaInvestimento")
public class SpesaInvestimentoController {
	
	@Autowired
	SpesaInvestimentoService spesaInvestimentoService;
	
	@Autowired
	SottoCategoriaService sottoCategoriaService;
	
	@GetMapping(value = "/ListaSpeseInvestimento")
	public ModelAndView listaSpeseInvestimento(HttpSession sessionObject) {
		ModelAndView model = new ModelAndView("SpesaInvestimento/ListaSpeseInvestimento");
		
		AnnoContabile oAnno = (AnnoContabile) sessionObject.getAttribute("oggettoAnno");
		List<SpesaInvestimento> listaSpeseInvestimento = spesaInvestimentoService.getSpeseInvestimentoPerAnno(oAnno.getIdAnno());
		
		model.addObject("elencoSpeseInvestimento", listaSpeseInvestimento);
		
		return model;
	}
	
	@GetMapping (value = "/AddSpesaInvestimento")
	public ModelAndView addSpesaInvestimento(HttpSession sessionObject) {
		ModelAndView model = new ModelAndView("SpesaInvestimento/AddEditSpesaInvestimento");
		SpesaInvestimento spesaInvestimento = new SpesaInvestimento();
		
		AnnoContabile oAnno = (AnnoContabile) sessionObject.getAttribute("oggettoAnno");
		List<SottoCategoria> listaSottoCategorie = sottoCategoriaService.getSottoCategoriePerAnno(oAnno.getIdAnno());
		
		model.addObject("elencoSottoCategorie", listaSottoCategorie);
		model.addObject("oggettoSpesaInvestimento", spesaInvestimento);
		
		return model;
	}
	
	@GetMapping (value = "/EditSpesaInvestimento/{id}")
	public ModelAndView editSpesaInvestimento(@PathVariable("id") Integer id, HttpSession sessionObject) {
		ModelAndView model = new ModelAndView("SpesaInvestimento/AddEditSpesaInvestimento");
		AnnoContabile oAnno = (AnnoContabile) sessionObject.getAttribute("oggettoAnno");
		List<SottoCategoria> listaSottoCategorie = sottoCategoriaService.getSottoCategoriePerAnno(oAnno.getIdAnno());
		SpesaInvestimento spesaInvestimento = spesaInvestimentoService.getSpesaInvestimentoById(id);
		
		model.addObject("oSottoCategoria", spesaInvestimento.getoSottoCategoria());
		model.addObject("elencoSottoCategorie", listaSottoCategorie);
		model.addObject("oggettoSpesaInvestimento", spesaInvestimento);
		
		return model;
	}
	
	@PostMapping(value = "/SaveSpesaInvestimento")
	public ModelAndView saveSpesaInvestimento(@Valid @ModelAttribute("oggettoSpesaInvestimento") SpesaInvestimento oSpesaInvestimento, BindingResult bindingResult) {
				
		if (bindingResult.hasErrors()) {
			ModelAndView model = new ModelAndView("SpesaInvestimento/AddEditSpesaInvestimento");
			
			return model;
		} else {
			spesaInvestimentoService.saveOrUpdate(oSpesaInvestimento);
			
			return new ModelAndView("redirect:/SpesaInvestimento/ListaSpeseInvestimento");
		}
	}
	
	@GetMapping (value = "/DeleteSpesaInvestimento/{id}")
	public ModelAndView deleteSpesaInvestimento(@PathVariable("id") Integer id) {
				
		spesaInvestimentoService.deleteSpesaInvestimentoById(id);
		return new ModelAndView("redirect:/SpesaInvestimento/ListaSpeseInvestimento");
	}
}
