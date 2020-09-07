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
import com.example.demo.model.Fornitore;
import com.example.demo.model.OrdineAcquisto;
import com.example.demo.model.OrdineAcquistoDettaglio;
import com.example.demo.model.SottoCategoria;
import com.example.demo.service.FornitoreService;
import com.example.demo.service.OrdineAcquistoDettaglioService;
import com.example.demo.service.OrdineAcquistoService;
import com.example.demo.service.ProgettoService;
import com.example.demo.service.SottoCategoriaService;
import com.example.demo.service.SpesaInvestimentoService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@RequestMapping(value = "/OrdineAcquisto")
public class OrdineAcquistoController {
	
	@Autowired
	OrdineAcquistoService ordineAcquistoService;
	
	@Autowired
	FornitoreService fornitoreService;
	
	@Autowired
	SpesaInvestimentoService spesaInvestimentoService;
	
	@Autowired
	ProgettoService progettoService;
	
	@Autowired
	SottoCategoriaService sottoCatService;
	
	@Autowired
	OrdineAcquistoDettaglioService dettaglioService;
	
	private boolean edit = false;
	private int count = 0;
	
	
	@GetMapping(value = "/Menu")
	public ModelAndView list() {
		
		ModelAndView model = new ModelAndView("Menu/ListaGestioneOrdineAcquisto");
		return model;
	}
	
	@GetMapping(value = "/ListaOrdiniAcquisto")
	public ModelAndView filtraOrdiniPerFornitore(HttpSession sessionObject) {
		ModelAndView model = new ModelAndView();
		String path = "OrdineAcquisto/ListaOrdiniAcquisto";
		List<Fornitore> listaFornitori = fornitoreService.getAllFornitori();
		
		model.addObject("elencoFornitori", listaFornitori);
		model.addObject("oggettoFornitore", new Fornitore());
		sessionObject.setAttribute("elencoFornitori", listaFornitori);
		sessionObject.setAttribute("url", path);
		
		return model;
	}

	@ApiOperation(value = "Ricerca ordini per fornitore",
				  notes = "Restituisce i dati dell'ordine in formato JSON",
				  response = OrdineAcquisto.class,
				  produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ordine trovato!"),
			@ApiResponse(code = 404, message = "L'articolo cercato NON è stato trovato!"),
			@ApiResponse(code = 403, message = "Non sei AUTORIZZATO!"),
			@ApiResponse(code = 401, message = "Non sei AUTENTICATO!")
	})
	
	
	
	@PostMapping(value = "/CercaOrdinePerFornitore")
	public ModelAndView listaOrdiniPerFornitore(@ModelAttribute("oggettoFornitore") Fornitore oFornitore, HttpSession sessionObject) {
		ModelAndView model = new ModelAndView("OrdineAcquisto/ListaOrdiniAcquisto");
		@SuppressWarnings("unchecked")
		List<Fornitore> listaFornitori = (List<Fornitore>) sessionObject.getAttribute("elencoFornitori");
		
		model.addObject("elencoFornitori", listaFornitori);
		model.addObject("oggettoFornitore", oFornitore);
		sessionObject.setAttribute("oggettoFornitore", oFornitore);
		
		List<OrdineAcquisto> listaOrdiniAcquisto = ordineAcquistoService.getOrdiniAcquistoPerFornitore(oFornitore.getIdFornitore());
		model.addObject("elencoOrdiniAcquisto", listaOrdiniAcquisto);
		
		return model;
	}
	
	// Nuovo ordine
	@GetMapping(value = "/AddPrimoOrdineAcquisto")
	public ModelAndView addPrimoOrdineAcquisto(HttpSession sessionObject) {
		ModelAndView model = new ModelAndView("OrdineAcquisto/AddOrdineAcquisto");
		@SuppressWarnings("unchecked")
		List<Fornitore> listaFornitori = (List<Fornitore>) sessionObject.getAttribute("elencoFornitori");
		model.addObject("elencoFornitori", listaFornitori);
		
		OrdineAcquisto oOrdine = new OrdineAcquisto();
		model.addObject("oggettoFornitore", (Fornitore) sessionObject.getAttribute("oggettoFornitore"));
		model.addObject("oggettoOrdineAcquisto", oOrdine);
//		sessionObject.setAttribute("oggettoOrdineAcquisto", oOrdine);
		
		return model;
	}
	
	@PostMapping(value = "/AddOrdineAcquisto")
	public ModelAndView addOrdineAcquisto(@Valid @ModelAttribute("oggettoOrdineAcquisto") OrdineAcquisto oOrdine, HttpSession sessionObject) {
		ModelAndView model = new ModelAndView("OrdineAcquisto/AddOrdineAcquisto");
		@SuppressWarnings("unchecked")
		List<Fornitore> listaFornitori = (List<Fornitore>) sessionObject.getAttribute("elencoFornitori");
		model.addObject("elencoFornitori", listaFornitori);
		
		model.addObject("oggettoOrdineAcquisto", oOrdine);
		sessionObject.setAttribute("oggettoOrdineAcquisto", oOrdine);
		
		return model;
	}
	
	// Nuovo dettaglio
	@PostMapping(value = "/AddPrimoOrdineAcquistoDettaglio")
	public ModelAndView addPrimoDettaglio(@Valid @ModelAttribute("oggettoOrdineAcquisto") OrdineAcquisto oOrdine, BindingResult bindingResult, HttpSession sessionObject) {
		ModelAndView model = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			model.setViewName("OrdineAcquisto/AddOrdineAcquisto");
		} else {
			model.setViewName("/OrdineAcquistoDettaglio/AddEditOrdineAcquistoDettaglio");
			model.addObject("oggettoOrdineAcquisto", oOrdine);
			model.addObject("elencoDettagli", oOrdine.getDettagli());
			sessionObject.setAttribute("oggettoOrdineAcquisto", oOrdine);
			sessionObject.setAttribute("elencoDettagli", oOrdine.getDettagli());
			
			OrdineAcquistoDettaglio oDettaglio = new OrdineAcquistoDettaglio();
			model.addObject("oggettoOrdineAcquistoDettaglio", oDettaglio);
			AnnoContabile oAnno = (AnnoContabile) sessionObject.getAttribute("oggettoAnno");
			model.addObject("elencoSpeseInvestimento", spesaInvestimentoService.getSpeseInvestimentoPerAnno(oAnno.getIdAnno()));
			model.addObject("elencoProgetti", progettoService.getAllProgetti());
		}
		return model;
	}
	
	
	@GetMapping(value = "/AddOrdineAcquistoDettaglio")
	public ModelAndView addDettaglio(HttpSession sessionObject) {
		ModelAndView model = new ModelAndView();
		
//		if (bindingResult.hasErrors()) {
//				model.setViewName("OrdineAcquisto/AddOrdineAcquisto");
//		} else {
		model.setViewName("OrdineAcquistoDettaglio/AddEditOrdineAcquistoDettaglio");
		AnnoContabile oAnno = (AnnoContabile) sessionObject.getAttribute("oggettoAnno");
		model.addObject("elencoSpeseInvestimento", spesaInvestimentoService.getSpeseInvestimentoPerAnno(oAnno.getIdAnno()));
		model.addObject("elencoProgetti", progettoService.getAllProgetti());
		OrdineAcquistoDettaglio oDettaglio = new OrdineAcquistoDettaglio();
	
		model.addObject("oggettoOrdineAcquistoDettaglio", oDettaglio);
//		}
		return model;
	}
	
	@PostMapping(value = "/SaveDettaglio")
	public ModelAndView aggiungiDettaglio(@Valid @ModelAttribute("oggettoOrdineAcquistoDettaglio") OrdineAcquistoDettaglio oDettaglio, BindingResult bindingResult, HttpSession sessionObject) {
		ModelAndView model = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			AnnoContabile oAnno = (AnnoContabile) sessionObject.getAttribute("oggettoAnno");
			model.addObject("elencoSpeseInvestimento", spesaInvestimentoService.getSpeseInvestimentoPerAnno(oAnno.getIdAnno()));
			model.addObject("elencoProgetti", progettoService.getAllProgetti());
			model.addObject("oggettoOrdineAcquistoDettaglio", oDettaglio);
			model.setViewName("/OrdineAcquistoDettaglio/AddEditOrdineAcquistoDettaglio");
		} else {
			if (!edit) 
				model.setViewName("/OrdineAcquisto/AddOrdineAcquistoConDettagli");
			else
				model.setViewName("/OrdineAcquisto/EditOrdineAcquisto");
			
			OrdineAcquisto oOrdine = (OrdineAcquisto) sessionObject.getAttribute("oggettoOrdineAcquisto");
			oDettaglio.setoOrdineAcquisto(oOrdine);
			oOrdine.getDettagli().add(oDettaglio);
			count = count + 1;
			oDettaglio.setIdentifier(count);
			
			
			@SuppressWarnings("unchecked")
			List<Fornitore> listaFornitori = (List<Fornitore>) sessionObject.getAttribute("elencoFornitori");
			model.addObject("elencoFornitori", listaFornitori);
			model.addObject("oggettoOrdineAcquisto", oOrdine);
			sessionObject.setAttribute("oggettoOrdineAcquisto", oOrdine);
			sessionObject.setAttribute("elencoDettagli", oOrdine.getDettagli());
		}
		
		return model;
	}
	
	@PostMapping(value = "/SaveOrdineAcquisto")
	public ModelAndView saveOrdineAcquisto(@Valid @ModelAttribute("oggettoOrdineAcquisto") OrdineAcquisto oOrdine, BindingResult bindingResult, HttpSession sessionObject) {
		ModelAndView model = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			if (edit) 
				model.setViewName("/OrdineAcquisto/EditOrdineAcquisto");
			else 
				model.setViewName("/OrdineAcquisto/AddOrdineAcquistoConDettagli");
			
			@SuppressWarnings("unchecked")
			List<Fornitore> listaFornitori = (List<Fornitore>) sessionObject.getAttribute("elencoFornitori");
			model.addObject("elencoFornitori", listaFornitori);
			model.addObject("oggettoOrdineAcquisto", (OrdineAcquisto) sessionObject.getAttribute("oggettoOrdineAcquisto"));
		} else {
			model.setViewName("/OrdineAcquisto/ListaOrdiniAcquisto");
			
			@SuppressWarnings("unchecked")
			List<OrdineAcquistoDettaglio> listaDettagli = (List<OrdineAcquistoDettaglio>) sessionObject.getAttribute("elencoDettagli");
			Fornitore oFornitore = (Fornitore) sessionObject.getAttribute("oggettoFornitore");
			model.addObject("oggettoFornitore", oFornitore);
			
			ordineAcquistoService.saveOrUpdate(oOrdine);
			
			for(int k = 0; k < listaDettagli.size(); k++) {
				OrdineAcquistoDettaglio oDettaglio = listaDettagli.get(k);
				oDettaglio.setoOrdineAcquisto(oOrdine);
				
				dettaglioService.saveOrUpdate(oDettaglio);
			}
			
			// Check dettagli da eliminare in caso di edit
			@SuppressWarnings("unchecked")
			List<OrdineAcquistoDettaglio> listaDettagliTemporanea =  (List<OrdineAcquistoDettaglio>) sessionObject.getAttribute("dettagliTemporanei");
			if (listaDettagliTemporanea != null) {
				int size = listaDettagliTemporanea.size();
				int j = 0;
				List<OrdineAcquistoDettaglio> dettagliDaEliminare = new ArrayList<OrdineAcquistoDettaglio>();
				
				for (int i = 0; i < size; i++) {
					for (j = 0; j < listaDettagli.size(); j++) {
						if (listaDettagliTemporanea.get(i).getIdOrdineAcquistoDettaglio() == listaDettagli.get(j).getIdOrdineAcquistoDettaglio()) {
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
					OrdineAcquistoDettaglio oDettaglio = dettagliDaEliminare.get(x);
					oDettaglio.setoOrdineAcquisto(oOrdine);
					
					// Correzione budgetSpeso in caso il dettaglio fosse stato riconciliato
					SottoCategoria oSottoCat = oDettaglio.getoSpesaInvestimento().getoSottoCategoria();
					float importo = oDettaglio.getImporto();
					oSottoCat.setBudgetSpeso(oSottoCat.getBudgetSpeso() - importo);
					sottoCatService.saveOrUpdate(oSottoCat);
					
					dettaglioService.deleteDettaglio(oDettaglio);
				}
			}
			//---------------
			
			@SuppressWarnings("unchecked")
			List<Fornitore> listaFornitori = (List<Fornitore>) sessionObject.getAttribute("elencoFornitori");
			model.addObject("elencoFornitori", listaFornitori);
			model.addObject("oggettoFornitore", new Fornitore());
			sessionObject.removeAttribute("elencoDettagli");
			sessionObject.removeAttribute("oggettoFornitore");
			sessionObject.removeAttribute("oggettoOrdineAcquisto");
		}
		
		return model;
	}
	
	@GetMapping (value = "/EditOrdineAcquisto/{id}")
	public ModelAndView editOrdineAcquisto(@ApiParam("Id univoco dell'ordine") @PathVariable("id") Integer id, HttpSession sessionObject) {
		ModelAndView model = new ModelAndView("OrdineAcquisto/EditOrdineAcquisto");
		edit = true;
		OrdineAcquisto ordineAcquisto = ordineAcquistoService.getOrdineAcquistoById(id);
		List<OrdineAcquistoDettaglio> dettagli = new ArrayList<OrdineAcquistoDettaglio>();
		
		for(int i = 0; i < ordineAcquisto.getDettagli().size(); i++) {
			dettagli.add(ordineAcquisto.getDettagli().get(i));
		}
		
		@SuppressWarnings("unchecked")
		List<Fornitore> listaFornitori = (List<Fornitore>) sessionObject.getAttribute("elencoFornitori");
		model.addObject("elencoFornitori", listaFornitori);
		model.addObject("oggettoOrdineAcquisto", ordineAcquisto);
		model.addObject("elencoDettagli", ordineAcquisto.getDettagli());
		sessionObject.setAttribute("oggettoOrdineAcquisto", ordineAcquisto);
		sessionObject.setAttribute("oggettoFornitore", ordineAcquisto.getoFornitore());
		sessionObject.setAttribute("elencoDettagli", ordineAcquisto.getDettagli());
		sessionObject.setAttribute("dettagliTemporanei", dettagli);
		
		return model;
	}
	
	@GetMapping (value = "/DeleteOrdineAcquisto/{id}")
	public ModelAndView deleteOrdineAcquisto(@PathVariable("id") Integer id) {
				
		ordineAcquistoService.deleteOrdineAcquistoById(id);
		return new ModelAndView("redirect:/OrdineAcquisto/ListaOrdiniAcquisto");
	}
	
	@GetMapping(value = "/DeleteOrdineAcquistoDettaglio/{id}/{identifier}")
	public ModelAndView deleteDettaglio(@PathVariable(name = "id") Integer id, @PathVariable(name = "identifier") Integer identifier, HttpSession sessionObject) {
		ModelAndView model = new ModelAndView();
		
		OrdineAcquisto oOrdine = (OrdineAcquisto) sessionObject.getAttribute("oggettoOrdineAcquisto");
		List<OrdineAcquistoDettaglio> dettagli = oOrdine.getDettagli();
		boolean check = false;
		
		if (edit && (id != 0)) {
			for(int i = 0; i < dettagli.size() && !check; i++) {
				if (id == dettagli.get(i).getIdOrdineAcquistoDettaglio()) {
					oOrdine.getDettagli().remove(dettagli.get(i));
					check = true;
				}
			}
		} else {
			for (int i = 0; i < dettagli.size(); i++) {
				if (identifier == dettagli.get(i).getIdentifier()) {
					oOrdine.getDettagli().remove(dettagli.get(i));
				}
			}
		}
		
		model.setViewName("/OrdineAcquisto/EditOrdineAcquisto");
		model.addObject("oggettoOrdineAcquisto", oOrdine);
		model.addObject("elencoDettagli", oOrdine.getDettagli());
		@SuppressWarnings("unchecked")
		List<Fornitore> listaFornitori = (List<Fornitore>) sessionObject.getAttribute("elencoFornitori");
		model.addObject("elencoFornitori", listaFornitori);
		model.addObject("oggettoFornitore", oOrdine.getoFornitore());
		sessionObject.setAttribute("elencoDettagli", oOrdine.getDettagli());
		sessionObject.setAttribute("oggettoOrdineAcquisto", oOrdine);
		
		return model;
	}
	
}
