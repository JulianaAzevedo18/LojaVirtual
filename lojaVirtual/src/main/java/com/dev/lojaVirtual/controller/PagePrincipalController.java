package com.dev.lojaVirtual.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagePrincipalController {
	@GetMapping ("/administrativo")
	public String acessarPagePrincipal(){
		return "administrativo/home";
	}

}
