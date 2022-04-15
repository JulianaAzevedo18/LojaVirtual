package com.dev.lojaVirtual.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dev.lojaVirtual.model.FuncionarioModel;
import com.dev.lojaVirtual.repository.FuncionarioRepository;

@Controller
public class FuncionarioController {
	
	@Autowired
	public FuncionarioRepository funcionarioRepository;
	
	@GetMapping ("/administrativo/funcionarios/cadastrar")
	public ModelAndView cadastrar(FuncionarioModel funcionario) {
		ModelAndView mv = new ModelAndView("/administrativo/funcionarios/cadastro");
		mv.addObject("funcionario", funcionario);
		return mv;
	}
	
	@GetMapping ("/administrativo/funcionarios/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("/administrativo/funcionarios/lista");
		mv.addObject("listaFuncionario", funcionarioRepository.findAll());
		return mv;
	}

	@PostMapping("/administrativo/funcionarios/salvar")
	public ModelAndView salvar(@Valid FuncionarioModel funcionario, BindingResult result) {
		
		//System.out.println(result.getAllErrors());
		if(result.hasErrors()) {
			return cadastrar(funcionario);
		}

		funcionarioRepository.saveAndFlush(funcionario);
		
		return cadastrar(new FuncionarioModel());
	}
}
