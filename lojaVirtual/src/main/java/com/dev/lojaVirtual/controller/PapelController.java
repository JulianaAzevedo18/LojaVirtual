package com.dev.lojaVirtual.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dev.lojaVirtual.model.PapelModel;
import com.dev.lojaVirtual.repository.PapelRepository;

@Controller
public class PapelController {
	
		@Autowired
		private PapelRepository papelRepositorio;
		
		@GetMapping("/administrativo/papeis/cadastrar")
		public ModelAndView cadastrar(PapelModel papel) {
			ModelAndView mv =  new ModelAndView("administrativo/papeis/cadastro");
			mv.addObject("papel",papel);
			return mv;
		}
		
		@GetMapping("/administrativo/papeis/listar")
		public ModelAndView listar() {
			ModelAndView mv=new ModelAndView("administrativo/papeis/lista");
			mv.addObject("listaPapeis", papelRepositorio.findAll());
			return mv;
		}
		
		@GetMapping("/administrativo/papeis/editar/{id}")
		public ModelAndView editar(@PathVariable("id") Long id) {
			Optional<PapelModel> papel = papelRepositorio.findById(id);
			return cadastrar(papel.get());
		}
		
		@GetMapping("/administrativo/papeis/remover/{id}")
		public ModelAndView remover(@PathVariable("id") Long id) {
			Optional<PapelModel> papel = papelRepositorio.findById(id);
			papelRepositorio.delete(papel.get());
			return listar();
		}
		
		@PostMapping("/administrativo/papeis/salvar")
		public ModelAndView salvar(@Valid PapelModel papel, BindingResult result) {
			
			if(result.hasErrors()) {
				return cadastrar(papel);
			}
			papelRepositorio.saveAndFlush(papel);
			
			return cadastrar(new PapelModel());
		}

	}