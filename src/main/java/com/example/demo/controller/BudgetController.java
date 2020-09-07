package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.AnnoContabile;
import com.example.demo.model.AreaInvestimento;
import com.example.demo.model.OggettoRiconciliazione;
import com.example.demo.model.OrdineAcquistoDettaglio;
import com.example.demo.model.SottoCategoria;
import com.example.demo.service.AreaInvestimentoService;
import com.example.demo.service.OrdineAcquistoDettaglioService;
import com.example.demo.service.SottoCategoriaService;

@Controller
@RequestMapping(value = "/Budget")
public class BudgetController {
	
	@Autowired
	SottoCategoriaService sottoCatService;
	
	@Autowired
	OrdineAcquistoDettaglioService dettaglioService;
	
	@Autowired
	AreaInvestimentoService areaInvService;
	
	@GetMapping(value = "/Lista")
	public ModelAndView list(HttpSession sessionObject) {
		ModelAndView model = new ModelAndView();
		String path = "/Menu/ListaBudget";
		model.setViewName(path);
		sessionObject.setAttribute("url", path);
		
		return model;
	}
	
	@GetMapping(value = "/Riconcilia")
	public ModelAndView riconciliazione() {
		ModelAndView model = new ModelAndView("/Budget/RiconciliaBudget");
		model.addObject("oggettoRiconciliazione", new OggettoRiconciliazione());
		
		return model;
	}
	
	@PostMapping(value = "/saveRiconciliazione")
	public ModelAndView saveRiconciliazione(@Valid @ModelAttribute("oggettoRiconciliazione") OggettoRiconciliazione oRiconcilia, HttpSession sessionObject) {
		ModelAndView model = new ModelAndView();
		
		List<OrdineAcquistoDettaglio> dettagli = new ArrayList<OrdineAcquistoDettaglio>();
		
		try {
			AnnoContabile oAnno = (AnnoContabile) sessionObject.getAttribute("oggettoAnno");
			dettagli = dettaglioService.getDettagliPerSottoCategorieEAnno(oAnno.getIdAnno(), oRiconcilia.getDataInizio(), oRiconcilia.getDataFine());
			boolean check = sottoCatService.riconcilia(dettagli);
			
			if (check) 
				model.addObject("esito", "Riconciliazione effettuata con successo!");
		} catch (Exception e) {
			e.printStackTrace();
			model.addObject("esito", "Failed!");
		}
		
		model.setViewName("Budget/RiconciliaBudget");
		return model;
	}
	
	@GetMapping(value = "/Avanzamento")
	public ModelAndView avanzamento(HttpSession sessionObject) {
		ModelAndView model = new ModelAndView("/Budget/Avanzamento");
		AnnoContabile oAnno = (AnnoContabile) sessionObject.getAttribute("oggettoAnno");
		model.addObject("elencoAree", areaInvService.getAreaInvPerAnno(oAnno.getIdAnno()));
		model.addObject("oggettoArea", new AreaInvestimento());
		
		return model;
	}
	
	@PostMapping(value = "/showAvanzamento")
	public ModelAndView showAvanzamento(@ModelAttribute("oggettoArea") AreaInvestimento oArea, HttpSession sessionObject) {
		ModelAndView model = new ModelAndView("/Budget/Avanzamento");
		AnnoContabile oAnno = (AnnoContabile) sessionObject.getAttribute("oggettoAnno");
		model.addObject("elencoAree", areaInvService.getAreaInvPerAnno(oAnno.getIdAnno()));
		List<SottoCategoria> elencoSottoCategorie = sottoCatService.getAllSottoCategoriePerAreaEAnno(oAnno.getIdAnno(), oArea.getIdAreaInvestimento());
		
		for (int i = 0; i < elencoSottoCategorie.size(); i++) {
			SottoCategoria oSottoCat = elencoSottoCategorie.get(i);
			oSottoCat.setAvanzamento(oSottoCat.getBudget() - oSottoCat.getBudgetSpeso());
		}
		
		model.addObject("elencoSottoCategoriePerArea", elencoSottoCategorie);
		
		return model;
	}
	
	@GetMapping(value = "/Definizione")
	public ModelAndView definizione(HttpSession sessionObject) {
		ModelAndView model = new ModelAndView("/Budget/Definizione");
		AnnoContabile oAnno = (AnnoContabile) sessionObject.getAttribute("oggettoAnno");
		model.addObject("elencoAree", areaInvService.getAreaInvPerAnno(oAnno.getIdAnno()));
		model.addObject("oggettoArea", new AreaInvestimento());
		
		return model;
	}
	
	@PostMapping(value = "/showDefinizione")
	public ModelAndView showDefinizione(@ModelAttribute("oggettoArea") AreaInvestimento oArea, HttpSession sessionObject) {
		ModelAndView model = new ModelAndView("/Budget/Definizione");
		AnnoContabile oAnno = (AnnoContabile) sessionObject.getAttribute("oggettoAnno");
		model.addObject("elencoAree", areaInvService.getAreaInvPerAnno(oAnno.getIdAnno()));
		List<SottoCategoria> elencoSottoCategorie = sottoCatService.getAllSottoCategoriePerAreaEAnno(oAnno.getIdAnno(), oArea.getIdAreaInvestimento());
		
		model.addObject("oggettoArea", oArea);
		model.addObject("elencoSottoCategoriePerArea", elencoSottoCategorie);
		sessionObject.setAttribute("oggettoArea", oArea);		
		
		return model;
	}
	
	@GetMapping (value = "/EditBudget/{id}")
	public ModelAndView editBudget(@PathVariable("id") Integer id, HttpSession sessionObject) {
		ModelAndView model = new ModelAndView("/Budget/EditBudgetSottoCategoria");
		SottoCategoria oSottoCat = sottoCatService.getSottoCategoriaById(id);
		if (oSottoCat != null)
			model.addObject("oggettoSottoCategoria", oSottoCat);
		
		model.addObject("elencoFornitori", model);
		AreaInvestimento oggArea =  (AreaInvestimento) sessionObject.getAttribute("oggettoArea");
		AreaInvestimento oArea = areaInvService.getAreaInvById(oggArea.getIdAreaInvestimento());
		model.addObject("nomeArea", oArea.getAreaInvestimento());
		
		return model;
	}
	
	@PostMapping(value = "/saveDefinizione")
	public ModelAndView saveDefinizione(@ModelAttribute("oggettoSottoCategoria") SottoCategoria oSottoCategoria, HttpSession sessionObject) {
		ModelAndView model = new ModelAndView("/Budget/Definizione");
		
		sottoCatService.saveOrUpdate(oSottoCategoria);

		AnnoContabile oAnno = (AnnoContabile) sessionObject.getAttribute("oggettoAnno");
		model.addObject("elencoAree", areaInvService.getAreaInvPerAnno(oAnno.getIdAnno()));
		model.addObject("oggettoSottoCategoria", oSottoCategoria);
		AreaInvestimento oArea = (AreaInvestimento) sessionObject.getAttribute("oggettoArea");
		model.addObject("oggettoArea", oArea);
		model.addObject("elencoSottoCategoriePerArea", sottoCatService.getAllSottoCategoriePerAreaEAnno(oAnno.getIdAnno(), oArea.getIdAreaInvestimento()));
		
		return model;
	}
	
	
}
