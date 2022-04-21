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

import com.dev.lojaVirtual.model.PermissaoModel;
import com.dev.lojaVirtual.repository.FuncionarioRepository;
import com.dev.lojaVirtual.repository.PapelRepository;
import com.dev.lojaVirtual.repository.PermissaoRepository;

@Controller
public class PermissaoController {
			
		@Autowired
		private PermissaoRepository permissaoRepositorio;
		
		@Autowired
		private FuncionarioRepository funcionarioRepositorio;
		
		@Autowired
		private PapelRepository papelRepositorio;
		
		
		@GetMapping("/administrativo/permissoes/cadastrar")
		public ModelAndView cadastrar(PermissaoModel permissao) {
			ModelAndView mv =  new ModelAndView("administrativo/permissoes/cadastro");
			mv.addObject("permissao",permissao);
			mv.addObject("listaFuncionarios",funcionarioRepositorio.findAll());
			mv.addObject("listaPapeis", papelRepositorio.findAll());
			return mv;
		}
		
		@GetMapping("/administrativo/permissoes/listar")
		public ModelAndView listar() {
			ModelAndView mv=new ModelAndView("administrativo/permissoes/lista");
			mv.addObject("listaPermissoes", permissaoRepositorio.findAll());
			return mv;
		}
		
		@GetMapping("/administrativo/permissoes/editar/{id}")
		public ModelAndView editar(@PathVariable("id") Long id) {
			Optional<PermissaoModel> permissao = permissaoRepositorio.findById(id);
			return cadastrar(permissao.get());
		}
		
		@GetMapping("/administrativo/permissoes/remover/{id}")
		public ModelAndView remover(@PathVariable("id") Long id) {
			Optional<PermissaoModel> permissao = permissaoRepositorio.findById(id);
			permissaoRepositorio.delete(permissao.get());
			return listar();
		}
		
		@PostMapping("/administrativo/permissoes/salvar")
		public ModelAndView salvar(@Valid PermissaoModel permissao, BindingResult result) {
			
			if(result.hasErrors()) {
				return cadastrar(permissao);
			}
			permissaoRepositorio.saveAndFlush(permissao);
			
			return cadastrar(new PermissaoModel());
		}

	}