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
import com.example.demo.service.AnnoContabileService;
//import com.example.demo.service.AnnoContabileService;
import com.example.demo.service.AreaInvestimentoService;

@Controller
@RequestMapping(value = "/AreaInvestimento")
public class AreaInvestimentoController {
	
	@Autowired
	AreaInvestimentoService areaInvService;
	
	@Autowired
	AnnoContabileService annoService;
	
	@GetMapping (value = "/ListaAreeInvestimento")
	public ModelAndView listaAreeInv(HttpSession sessionObject) {
		ModelAndView model = new ModelAndView("AreaInvestimento/ListaAreeInvestimento");
		AnnoContabile oAnno = (AnnoContabile) sessionObject.getAttribute("oggettoAnno");
		List<AreaInvestimento> listaAreeInv = areaInvService.getAreaInvPerAnno(oAnno.getIdAnno());
		
		model.addObject("elencoAreeInv", listaAreeInv);
		
		return model;
	}
	
	@GetMapping (value = "/AddAreaInvestimento")
	public ModelAndView addAreaInv() {
		ModelAndView model = new ModelAndView();
		AreaInvestimento areaInv = new AreaInvestimento();
		List<AnnoContabile> listaAnni = annoService.getAllAnni(); 
		
		model.addObject("elencoAnni", listaAnni);
		model.addObject("oggettoAreaInv", areaInv);
		model.setViewName("AreaInvestimento/AddEditAreaInvestimento");
		
		return model;
	}
	
	@GetMapping (value = "/EditAreaInvestimento/{id}")
	public ModelAndView editAreaInv(@PathVariable("id") Integer id) {
		ModelAndView model = new ModelAndView();
		AreaInvestimento areaInv = areaInvService.getAreaInvById(id);
		List<AnnoContabile> listaAnni = annoService.getAllAnni();
		
		model.addObject("oAnnoContabile", areaInv.getoAnnoContabile());
		model.addObject("elencoAnni", listaAnni);
		model.addObject("oggettoAreaInv", areaInv);
		model.setViewName("AreaInvestimento/AddEditAreaInvestimento");
		
		return model;
	}
	
	@PostMapping(value = "/SaveAreaInvestimento")
	public ModelAndView saveAreaInv(@Valid @ModelAttribute("oggettoAreaInv") AreaInvestimento oAreaInv, BindingResult bindingResult) {
				
		if (bindingResult.hasErrors()) {
			ModelAndView model = new ModelAndView();
			model.setViewName("AreaInvestimento/AddEditAreaInvestimento");
			
			return model;
		} else {
			areaInvService.saveOrUpdate(oAreaInv);
			
			return new ModelAndView("redirect:/AreaInvestimento/ListaAreeInvestimento");
		}
	}
	
	@GetMapping (value = "/DeleteAreaInvestimento/{id}")
	public ModelAndView deleteAreaInv(@PathVariable("id") Integer id) {
				
		areaInvService.deleteAreaInvestimentoById(id);
		return new ModelAndView("redirect:/AreaInvestimento/ListaAreeInvestimento");
	}
	
}
