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

import com.dev.lojaVirtual.model.EstadoModel;
import com.dev.lojaVirtual.repository.EstadoRepository;

@Controller
public class EstadoController {
			
		@Autowired
		private EstadoRepository estadoRepositorio;
		
		
		@GetMapping("/administrativo/estados/cadastrar")
		public ModelAndView cadastrar(EstadoModel estado) {
			ModelAndView mv =  new ModelAndView("administrativo/estados/cadastro");
			mv.addObject("estado",estado);
			return mv;
		}
		
		@GetMapping("/administrativo/estados/listar")
		public ModelAndView listar() {
			ModelAndView mv=new ModelAndView("administrativo/estados/lista");
			mv.addObject("listaEstados", estadoRepositorio.findAll());
			return mv;
		}
		
		@GetMapping("/administrativo/estados/editar/{id}")
		public ModelAndView editar(@PathVariable("id") Long id) {
			Optional<EstadoModel> estado = estadoRepositorio.findById(id);
			return cadastrar(estado.get());
		}
		
		@GetMapping("/administrativo/estados/remover/{id}")
		public ModelAndView remover(@PathVariable("id") Long id) {
			Optional<EstadoModel> estado = estadoRepositorio.findById(id);
			estadoRepositorio.delete(estado.get());
			return listar();
		}
		
		@PostMapping("/administrativo/estados/salvar")
		public ModelAndView salvar(@Valid EstadoModel estado, BindingResult result) {
			
			if(result.hasErrors()) {
				return cadastrar(estado);
			}
			estadoRepositorio.saveAndFlush(estado);
			
			return cadastrar(new EstadoModel());
		}

	}