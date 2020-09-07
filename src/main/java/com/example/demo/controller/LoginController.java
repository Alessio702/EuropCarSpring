package com.example.demo.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/login")
public class LoginController {
	
	
	@GetMapping
	public ModelAndView getLoginPage(Principal principal, HttpSession sessionObject) {
		ModelAndView model = new ModelAndView();
		model.setViewName("/Login/login");		// login.jsp
		
		return model;
	}
	
	@GetMapping(value = "/logout")
	public ModelAndView logoutRedirect() {
		ModelAndView model = new ModelAndView();
		model.setViewName("/Login/login");		// login.jsp
		
		return model;
	}	
}
