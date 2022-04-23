package com.dev.lojaVirtual.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dev.lojaVirtual.model.Cliente;
import com.dev.lojaVirtual.repository.CidadeRepository;
import com.dev.lojaVirtual.repository.ClienteRepository;

@Controller
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepositorio;
	
	@Autowired
	private CidadeRepository cidadeRepositorio;
	
	
	@GetMapping("/cliente/cadastrar")
	public ModelAndView cadastrar(Cliente cliente) {
		ModelAndView mv =  new ModelAndView("cliente/cadastrar");
		mv.addObject("cliente",cliente);
		mv.addObject("listaCidades",cidadeRepositorio.findAll());
		return mv;
	}
	
	@GetMapping("/cliente/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Cliente> cliente = clienteRepositorio.findById(id);
		return cadastrar(cliente.get());
	}

	
	@PostMapping("/cliente/salvar")
	public ModelAndView salvar(@Valid Cliente cliente, BindingResult result) {
		
		if(result.hasErrors()) {
			return cadastrar(cliente);
		}
		//	cliente.setSenha(new BCryptPasswordEncoder().encode(cliente.getSenha()));
		//clienteRepositorio.saveAndFlush(cliente);
		
		return cadastrar(new Cliente());
	}

}
