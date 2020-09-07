package com.example.demo.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.AnnoContabile;
import com.example.demo.model.User;
import com.example.demo.service.AnnoContabileService;
import com.example.demo.service.UserPrincipal;
import com.example.demo.service.UtenteService;
import com.example.demo.util.UDate;

@Controller
@RequestMapping(value = "/")
public class AnnoContabileController {
	
	@Autowired
	AnnoContabileService annoService;
	
	@Autowired
	UtenteService utenteService;
	
	@GetMapping //(value = "/ListaAnni")
	public ModelAndView listaAnniContabili(HttpSession sessionObjcet) {
		ModelAndView model = new ModelAndView("AnnoContabile/ListaAnniContabili");
		
		List<AnnoContabile> lista = annoService.getAllAnni();
		model.addObject("elencoAnni", lista);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal user = (UserPrincipal) auth.getPrincipal();
		sessionObjcet.setAttribute("usernameUserLogged", user.getUsername());
		sessionObjcet.setAttribute("oggettoUtente", utenteService.getUtenteByUsername(user.getUsername()));

		return model;
	}

	@GetMapping (value = "/SelezionaAnno/{id}")
	public ModelAndView selezionaAnno(@PathVariable("id") Integer idAnno, HttpSession sessionObject) {
		ModelAndView model = new ModelAndView("Menu/List");
		
		AnnoContabile oAnno = (AnnoContabile) annoService.getAnnoById(idAnno);
		model.addObject("oggettoAnno", oAnno);
		model.addObject("anno", oAnno.getDescrizione());
		sessionObject.setAttribute("oggettoAnno", oAnno);
		
		// controllo il ruolo del utente loggato
		String username = (String) sessionObject.getAttribute("usernameUserLogged");
		User oggUSer = utenteService.getUtenteByUsername(username);
		String ruoloUserLogged = oggUSer.getoVenditore().getoTipoVenditore().getTipoVenditore();
		boolean isAdmin = false;
		
		if (ruoloUserLogged.equals("ADMIN")) {
			isAdmin = true;
		}
		
		model.addObject("isAdmin", isAdmin);
		sessionObject.setAttribute("isAdmin", isAdmin);
		
		return model;
	}
	
	@GetMapping (value = "/GeneraPrimoAnnoContabile")
	public ModelAndView generaPrimoAnno(HttpSession sessionObject) {
		ModelAndView model = new ModelAndView();
		String path = "Menu/List";
		sessionObject.setAttribute("url", path);
		
		AnnoContabile oAnno = new AnnoContabile();
		Date oDate = new Date();
		@SuppressWarnings("deprecation")
		int annoCorrente = 1900 + oDate.getYear();
		int annoSuccessivo = annoCorrente + 1;
		
		try {
			oAnno.setDataInizio(UDate.ctrlData("01/07/" + annoCorrente));
			oAnno.setDataFine(UDate.ctrlData("30/06/" + annoSuccessivo));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		oAnno.setDescrizione(annoCorrente + "/" + annoSuccessivo);
		
		model.addObject("oggettoAnno", oAnno);
		sessionObject.setAttribute("oggettoAnno", oAnno);
		
		annoService.saveOrUpdate(oAnno);
		
		return model;
	}
	
	@SuppressWarnings("deprecation")
	@GetMapping (value = "/GeneraAnno")
	public ModelAndView generaAnno(HttpSession sessionObject) {
		ModelAndView model = new ModelAndView("Menu/List");
		
		List<AnnoContabile> listaAnni = annoService.getAllAnni();
		int max = 0;
		List<Integer> listaMax = new ArrayList<Integer>();
		
		for(int i = 0; i < listaAnni.size(); i++) {
			Integer year = 1900 + listaAnni.get(i).getDataFine().getYear();
			listaMax.add(year);
		}
		
		for(int i = 0; i < listaAnni.size(); i++) {
			if (listaMax.get(i) > max)
				max = listaMax.get(i);
		}
		
		AnnoContabile oAnno = new AnnoContabile();
		try {
			oAnno.setDataInizio(UDate.ctrlData("01/07/" + max));
			oAnno.setDataFine(UDate.ctrlData("30/06/" + (max + 1)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		oAnno.setDescrizione(max + "/" + (max + 1));
		
		annoService.saveOrUpdate(oAnno);
		model.addObject("messageAnnoGenerato", "Anno generato correttamente!");
		
		return model;
	}
	
	
	
}