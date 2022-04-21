package com.dev.lojaVirtual.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dev.lojaVirtual.model.CidadeModel;

@Controller
public class LoginController {

		@GetMapping("/login")
		public ModelAndView cadastrar(CidadeModel cidade) {
			ModelAndView mv =  new ModelAndView("/login");
			
			return mv;
		}
	}
