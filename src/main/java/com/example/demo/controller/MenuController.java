package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.AnnoContabile;


@Controller
@RequestMapping(value = "/Menu")
public class MenuController {

	@GetMapping(value = "/List")
	public ModelAndView list(HttpSession sessionObject) {
		ModelAndView model = new ModelAndView("Menu/List");
		
		AnnoContabile oAnno = (AnnoContabile) sessionObject.getAttribute("oggettoAnno");
		if (oAnno != null)
			model.addObject("anno", oAnno.getDescrizione());
		
		boolean isAdmin = (boolean) sessionObject.getAttribute("isAdmin");
		model.addObject("isAdmin", isAdmin);
		
		return model;
	}
	
	@GetMapping(value = "/ListaBudget")
	public ModelAndView listaBudget() {
		ModelAndView model = new ModelAndView("Menu/ListaBudget");
		
		return model;
	}
}
