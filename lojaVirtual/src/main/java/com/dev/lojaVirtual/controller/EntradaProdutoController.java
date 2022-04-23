package com.dev.lojaVirtual.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dev.lojaVirtual.model.EntradaItens;
import com.dev.lojaVirtual.model.EntradaProduto;
import com.dev.lojaVirtual.model.Produto;
import com.dev.lojaVirtual.repository.EntradaItensRepository;
import com.dev.lojaVirtual.repository.EntradaProdutoRepository;
import com.dev.lojaVirtual.repository.FuncionarioRepository;
import com.dev.lojaVirtual.repository.ProdutoRepository;

@Controller
public class EntradaProdutoController {
	
		private List<EntradaItens> listaEntrada = new ArrayList<EntradaItens>();

		@Autowired
		private EntradaProdutoRepository entradaProdutoRepository;

		@Autowired
		private EntradaItensRepository entradaItensRepository;

		@Autowired
		private FuncionarioRepository funcionarioRepository;

		@Autowired
		private ProdutoRepository produtoRepository;

		@GetMapping("/administrativo/entrada/cadastrar")
		public ModelAndView cadastrar(EntradaProduto entrada, EntradaItens entradaItens) {
			ModelAndView mv = new ModelAndView("administrativo/entrada/cadastro");
			mv.addObject("entrada", entrada);
			mv.addObject("listaEntradaItens", this.listaEntrada);
			mv.addObject("entradaItens", entradaItens);
			mv.addObject("listaFuncionarios", funcionarioRepository.findAll());
			mv.addObject("listaProdutos", produtoRepository.findAll());
			return mv;
		}

/*		@GetMapping("/administrativo/estados/listar")
//		public ModelAndView listar() {
//			ModelAndView mv=new ModelAndView("administrativo/estados/lista");
//			mv.addObject("listaEstados", estadoRepositorio.findAll());
//			return mv;
//		}

//		@GetMapping("/administrativo/estados/editar/{id}")
//		public ModelAndView editar(@PathVariable("id") Long id) {
//			Optional<Estado> estado = estadoRepositorio.findById(id);
//			return cadastrar(estado.get());
//		}

//		@GetMapping("/administrativo/estados/remover/{id}")
//		public ModelAndView remover(@PathVariable("id") Long id) {
//			Optional<Estado> estado = estadoRepositorio.findById(id);
//			estadoRepositorio.delete(estado.get());
//			return listar();
//		}*/

		@PostMapping("/administrativo/entrada/salvar")
		public ModelAndView salvar(String acao, EntradaProduto entrada, EntradaItens entradaItens) {

			if (acao.equals("itens")) {
				this.listaEntrada.add(entradaItens);
			} else if (acao.equals("salvar")) {
				entradaProdutoRepository.saveAndFlush(entrada);
				for (EntradaItens it : listaEntrada) {
					it.setEntrada(entrada);
					entradaItensRepository.saveAndFlush(it);
					Optional<Produto> prod = produtoRepository.findById(it.getProduto().getId());
					Produto produto = prod.get();
					produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + it.getQuantidade());
					produto.setValorVenda(it.getValorVenda());
					produtoRepository.saveAndFlush(produto);
					this.listaEntrada = new ArrayList<>();
				}
				return cadastrar(new EntradaProduto(), new EntradaItens());
			}

			return cadastrar(entrada, new EntradaItens());
		}

	}

