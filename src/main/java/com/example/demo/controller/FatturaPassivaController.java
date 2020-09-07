package com.example.demo.controller;

import java.util.ArrayList;
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
import com.example.demo.model.FatturaPassiva;
import com.example.demo.model.FatturaPassivaDettaglio;
import com.example.demo.model.Fornitore;
import com.example.demo.service.AliquotaIvaService;
import com.example.demo.service.FatturaPassivaDettaglioService;
import com.example.demo.service.FatturaPassivaService;
import com.example.demo.service.FornitoreService;
import com.example.demo.service.PreventivoService;
import com.example.demo.service.SpesaInvestimentoService;

@Controller
@RequestMapping(value = "/FatturaPassiva")
public class FatturaPassivaController {
	
	@Autowired
	FatturaPassivaService fatturaPassivaService;
	
	@Autowired
	FornitoreService fornitoreService;
	
	@Autowired
	AliquotaIvaService aliquotaService;
	
	@Autowired
	SpesaInvestimentoService spesaInvService;
	
	@Autowired
	PreventivoService preventivoService;
	
	@Autowired
	FatturaPassivaDettaglioService dettaglioService;
	
	
	private boolean edit = false;
	private int count = 0;
	
	
	
	@GetMapping(value = "/ListaFatture")
	public ModelAndView listaFatture(HttpSession sessionObject) {
//		ModelAndView model = new ModelAndView("Menu/ListaGestioneFatturaPassiva");
		ModelAndView model = new ModelAndView();
		String path = "FatturaPassiva/ListaFatturaPassiva";
		model.setViewName(path);
		List<Fornitore> listaFornitori = fornitoreService.getAllFornitori();
		
		model.addObject("elencoFornitori", listaFornitori);
		model.addObject("oggettoFornitore", new Fornitore());
		sessionObject.setAttribute("elencoFornitori", listaFornitori);
		sessionObject.setAttribute("url", path);
		
		return model;
	}
	
	@PostMapping(value = "/CercaFatturaPerFornitore")
	public ModelAndView listaFatturePerFornitore(@ModelAttribute("oggettoFornitore") Fornitore oFornitore, HttpSession sessionObject) {
		ModelAndView model = new ModelAndView("FatturaPassiva/ListaFatturaPassiva");
		@SuppressWarnings("unchecked")
		List<Fornitore> listaFornitori = (List<Fornitore>) sessionObject.getAttribute("elencoFornitori");
		
		model.addObject("elencoFornitori", listaFornitori);
		model.addObject("oggettoFornitore", oFornitore);
		sessionObject.setAttribute("oggettoFornitore", oFornitore);
		
		List<FatturaPassiva> listaFatture = fatturaPassivaService.getFatturePassivePerFornitore(oFornitore.getIdFornitore());
		model.addObject("elencoFatturePassive", listaFatture);
		
		return model;
	}
	
	// Nuova fattura
	@GetMapping(value = "/AddPrimaFatturaPassiva")
	public ModelAndView addPrimaFattura(HttpSession sessionObject) {
		ModelAndView model = new ModelAndView("FatturaPassiva/AddFatturaPassiva");
		@SuppressWarnings("unchecked")
		List<Fornitore> listaFornitori = (List<Fornitore>) sessionObject.getAttribute("elencoFornitori");
		model.addObject("elencoFornitori", listaFornitori);
		
		FatturaPassiva oFattura = new FatturaPassiva();
		model.addObject("oggettoFornitore", (Fornitore) sessionObject.getAttribute("oggettoFornitore"));
		model.addObject("oggettoFattura", oFattura);
		
		
		return model;
	}
	
	@PostMapping(value = "/AddFatturaPassiva")
	public ModelAndView addFatturaPassiva(@Valid @ModelAttribute("oggettoFattura") FatturaPassiva oFattura, HttpSession sessionObject) {
		ModelAndView model = new ModelAndView("FatturaPassiva/AddFatturaPassiva");
		@SuppressWarnings("unchecked")
		List<Fornitore> listaFornitori = (List<Fornitore>) sessionObject.getAttribute("elencoFornitori");
		model.addObject("elencoFornitori", listaFornitori);
		model.addObject("oggettoFornitore", (Fornitore) sessionObject.getAttribute("oggettoFornitore"));
		
		model.addObject("oggettoFattura", oFattura);
		sessionObject.setAttribute("oggettoFattura", oFattura);
		
		return model;
	}
	
	// Nuovo dettaglio
	@PostMapping(value = "/AddPrimaFatturaPassivaDettaglio")
	public ModelAndView addPrimoDettaglio(@Valid @ModelAttribute("oggettoFattura") FatturaPassiva oFattura, BindingResult bindingResult, HttpSession sessionObject) {
		ModelAndView model = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			model.setViewName("FatturaPassiva/AddFatturaPassiva");
		} else {
			model.setViewName("/FatturaPassiva/AddEditFatturaPassivaDettaglio");
			model.addObject("oggettoFattura", oFattura);
			model.addObject("elencoDettagli", oFattura.getDettagli());
			sessionObject.setAttribute("oggettoFattura", oFattura);
			sessionObject.setAttribute("elencoDettagli", oFattura.getDettagli());
			
			FatturaPassivaDettaglio oDettaglio = new FatturaPassivaDettaglio();
			model.addObject("oggettoFatturaDettaglio", oDettaglio);
			AnnoContabile oAnno = (AnnoContabile) sessionObject.getAttribute("oggettoAnno");
			model.addObject("elencoSpeseInvestimento", spesaInvService.getSpeseInvestimentoPerAnno(oAnno.getIdAnno()));
			model.addObject("elencoPreventivi", preventivoService.getAllPreventivi());
			model.addObject("elencoAliquote", aliquotaService.getAllAliquoteIva());
			
		}
		return model;
	}
	
	
	@GetMapping(value = "/AddFatturaDettaglio")
	public ModelAndView addDettaglio(HttpSession sessionObject) {
		ModelAndView model = new ModelAndView();
		
//		if (bindingResult.hasErrors()) {
//				model.setViewName("FatturaPassiva/AddFatturaPassiva");
//		} else {
		model.setViewName("FatturaPassiva/AddEditFatturaPassivaDettaglio");
		AnnoContabile oAnno = (AnnoContabile) sessionObject.getAttribute("oggettoAnno");
		model.addObject("elencoSpeseInvestimento", spesaInvService.getSpeseInvestimentoPerAnno(oAnno.getIdAnno()));
		model.addObject("elencoPreventivi", preventivoService.getAllPreventivi());
		model.addObject("elencoAliquote", aliquotaService.getAllAliquoteIva());
		FatturaPassivaDettaglio oDettaglio = new FatturaPassivaDettaglio();
	
		model.addObject("oggettoFatturaDettaglio", oDettaglio);
//		}
		return model;
	}
	
	@PostMapping(value = "/SaveDettaglio")
	public ModelAndView aggiungiDettaglio(@Valid @ModelAttribute("oggettoFatturaDettaglio") FatturaPassivaDettaglio oDettaglio, BindingResult bindingResult, HttpSession sessionObject) {
		ModelAndView model = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			AnnoContabile oAnno = (AnnoContabile) sessionObject.getAttribute("oggettoAnno");
			model.addObject("elencoSpeseInvestimento", spesaInvService.getSpeseInvestimentoPerAnno(oAnno.getIdAnno()));
			model.addObject("elencoPreventivi", preventivoService.getAllPreventivi());
			model.addObject("elencoAliquote", aliquotaService.getAllAliquoteIva());
			model.addObject("oggettoFatturaDettaglio", oDettaglio);
			model.setViewName("/FatturaPassiva/AddEditFatturaPassivaDettaglio");
		} else {
			if (!edit) 
				model.setViewName("/FatturaPassiva/AddFatturaPassivaConDettagli");
			else
				model.setViewName("/FatturaPassiva/EditFatturaPassiva");
			
			FatturaPassiva oFattura = (FatturaPassiva) sessionObject.getAttribute("oggettoFattura");
			oDettaglio.setoFatturaPassiva(oFattura);
			oFattura.getDettagli().add(oDettaglio);
			count = count + 1;
			oDettaglio.setIdentifier(count);
			
			
			@SuppressWarnings("unchecked")
			List<Fornitore> listaFornitori = (List<Fornitore>) sessionObject.getAttribute("elencoFornitori");
			model.addObject("elencoFornitori", listaFornitori);
			model.addObject("oggettoFattura", oFattura);
			sessionObject.setAttribute("oggettoFattura", oFattura);
			sessionObject.setAttribute("elencoDettagli", oFattura.getDettagli());
		}
		
		return model;
	}
	
	@PostMapping(value = "/SaveFatturaPassiva")
	public ModelAndView saveFatturaPassiva(@Valid @ModelAttribute("oggettoFattura") FatturaPassiva oFattura, BindingResult bindingResult, HttpSession sessionObject) {
		ModelAndView model = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			if (edit) 
				model.setViewName("/FatturaPassiva/EditFatturaPassiva");
			else 
				model.setViewName("/FatturaPassiva/AddFatturaPassivaConDettagli");
			
			@SuppressWarnings("unchecked")
			List<Fornitore> listaFornitori = (List<Fornitore>) sessionObject.getAttribute("elencoFornitori");
			model.addObject("elencoFornitori", listaFornitori);
			model.addObject("oggettoFattura", (FatturaPassiva) sessionObject.getAttribute("oggettoFattura"));
		} else {
			model.setViewName("/FatturaPassiva/ListaFatturaPassiva");
			
			@SuppressWarnings("unchecked")
			List<FatturaPassivaDettaglio> listaDettagli = (List<FatturaPassivaDettaglio>) sessionObject.getAttribute("elencoDettagli");
			Fornitore oFornitore = (Fornitore) sessionObject.getAttribute("oggettoFornitore");
			model.addObject("oggettoFornitore", oFornitore);
			
			fatturaPassivaService.saveOrUpdate(oFattura);
			
			for(int k = 0; k < listaDettagli.size(); k++) {
				FatturaPassivaDettaglio oDettaglio = listaDettagli.get(k);
				oDettaglio.setoFatturaPassiva(oFattura);
				
				dettaglioService.saveOrUpdate(oDettaglio);
			}
			
			// Check dettagli da eliminare in caso di edit
			@SuppressWarnings("unchecked")
			List<FatturaPassivaDettaglio> listaDettagliTemporanea =  (List<FatturaPassivaDettaglio>) sessionObject.getAttribute("dettagliTemporanei");
			if (listaDettagliTemporanea != null) {
				int size = listaDettagliTemporanea.size();
				int j = 0;
				List<FatturaPassivaDettaglio> dettagliDaEliminare = new ArrayList<FatturaPassivaDettaglio>();
				
				for (int i = 0; i < size; i++) {
					for (j = 0; j < listaDettagli.size(); j++) {
						if (listaDettagliTemporanea.get(i).getIdDettaglioFattura() == listaDettagli.get(j).getIdDettaglioFattura()) {
							i++;
							j = 0;
							if (i >= size)
								break;
						}
					}
					
					j = 0;
					if (i < size)
						dettagliDaEliminare.add(listaDettagliTemporanea.get(i));
				}
				
				
				for(int x = 0; x < dettagliDaEliminare.size(); x++) {
					FatturaPassivaDettaglio oDettaglio = dettagliDaEliminare.get(x);
					oDettaglio.setoFatturaPassiva(oFattura);
					
					dettaglioService.deleteDettaglio(oDettaglio);
				}
			}
			//---------------
			
			@SuppressWarnings("unchecked")
			List<Fornitore> listaFornitori = (List<Fornitore>) sessionObject.getAttribute("elencoFornitori");
			model.addObject("elencoFornitori", listaFornitori);
//			int chiaveFornitore = 0;
//			if (oFornitore != null)
//				chiaveFornitore = oFornitore.getIdFornitore();
//			model.addObject("elencoFatturePassive", fatturaPassivaService.getFatturePassivePerFornitore(chiaveFornitore));
			model.addObject("oggettoFornitore", new Fornitore());
			sessionObject.removeAttribute("elencoDettagli");
			sessionObject.removeAttribute("oggettoFornitore");
			sessionObject.removeAttribute("oggettoFattura");
		}
		
		return model;
	}
	
	@GetMapping (value = "/EditFatturaPassiva/{id}")
	public ModelAndView editOrdineAcquisto(@PathVariable("id") Integer id, HttpSession sessionObject) {
		ModelAndView model = new ModelAndView("FatturaPassiva/EditFatturaPassiva");
		edit = true;
		FatturaPassiva fattura = fatturaPassivaService.getFatturaPassivaById(id);
		List<FatturaPassivaDettaglio> dettagli = new ArrayList<FatturaPassivaDettaglio>();
		
		for(int i = 0; i < fattura.getDettagli().size(); i++) {
			dettagli.add(fattura.getDettagli().get(i));
		}
		
		@SuppressWarnings("unchecked")
		List<Fornitore> listaFornitori = (List<Fornitore>) sessionObject.getAttribute("elencoFornitori");
		model.addObject("elencoFornitori", listaFornitori);
		model.addObject("oggettoFattura", fattura);
		model.addObject("elencoDettagli", fattura.getDettagli());
		sessionObject.setAttribute("oggettoFattura", fattura);
		sessionObject.setAttribute("oggettoFornitore", fattura.getoFornitore());
		sessionObject.setAttribute("elencoDettagli", fattura.getDettagli());
		sessionObject.setAttribute("dettagliTemporanei", dettagli);
		
		return model;
	}
	
	@GetMapping (value = "/DeleteFatturaPassiva/{id}")
	public ModelAndView deleteFatturaPassiva(@PathVariable("id") Integer id, HttpSession sessionObject) {
		ModelAndView model = new ModelAndView("/FatturaPassiva/ListaFatturaPassiva");
		
		model.addObject("oggettoFornitore", new Fornitore());
		fatturaPassivaService.deleteFatturaPassivaById(id);
		
		return model;
	}
	
	@GetMapping(value = "/DeleteFatturaPassivaDettaglio/{id}/{identifier}")
	public ModelAndView deleteDettaglio(@PathVariable(name = "id") Integer id, @PathVariable(name = "identifier") Integer identifier, HttpSession sessionObject) {
		ModelAndView model = new ModelAndView();
		
		FatturaPassiva oFattura = (FatturaPassiva) sessionObject.getAttribute("oggettoFattura");
		List<FatturaPassivaDettaglio> dettagli = oFattura.getDettagli();
		boolean check = false;
		
		if (edit && (id != 0)) {
			for(int i = 0; i < dettagli.size() && !check; i++) {
				if (id == dettagli.get(i).getIdDettaglioFattura()) {
					oFattura.getDettagli().remove(dettagli.get(i));
					check = true;
				}
			}
		} else {
			for (int i = 0; i < dettagli.size(); i++) {
				if (identifier == dettagli.get(i).getIdentifier()) {
					oFattura.getDettagli().remove(dettagli.get(i));
				}
			}
		}
		
		model.setViewName("/FatturaPassiva/EditFatturaPassiva");
		model.addObject("oggettoFattura", oFattura);
		model.addObject("elencoDettagli", oFattura.getDettagli());
		@SuppressWarnings("unchecked")
		List<Fornitore> listaFornitori = (List<Fornitore>) sessionObject.getAttribute("elencoFornitori");
		model.addObject("elencoFornitori", listaFornitori);
		model.addObject("oggettoFornitore", oFattura.getoFornitore());
		sessionObject.setAttribute("elencoDettagli", oFattura.getDettagli());
		sessionObject.setAttribute("oggettoFattura", oFattura);
		
		return model;
	}
	
}