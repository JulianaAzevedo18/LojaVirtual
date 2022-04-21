package com.dev.lojaVirtual.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dev.lojaVirtual.model.EntradaItensModel;
import com.dev.lojaVirtual.model.EntradaProdutoModel;
import com.dev.lojaVirtual.model.ProdutoModel;
import com.dev.lojaVirtual.repository.EntradaItensRepository;
import com.dev.lojaVirtual.repository.EntradaProdutoRepository;
import com.dev.lojaVirtual.repository.FuncionarioRepository;
import com.dev.lojaVirtual.repository.ProdutoRepository;

@Controller
public class EntradaProdutoController {
	
		private List<EntradaItensModel> listaEntrada = new ArrayList<EntradaItensModel>();

		@Autowired
		private EntradaProdutoRepository entradaProdutoRepository;

		@Autowired
		private EntradaItensRepository entradaItensRepository;

		@Autowired
		private FuncionarioRepository funcionarioRepository;

		@Autowired
		private ProdutoRepository produtoRepository;

		@GetMapping("/administrativo/entrada/cadastrar")
		public ModelAndView cadastrar(EntradaProdutoModel entrada, EntradaItensModel entradaItens) {
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
		public ModelAndView salvar(String acao, EntradaProdutoModel entrada, EntradaItensModel entradaItens) {

			if (acao.equals("itens")) {
				this.listaEntrada.add(entradaItens);
			} else if (acao.equals("salvar")) {
				entradaProdutoRepository.saveAndFlush(entrada);
				for (EntradaItensModel it : listaEntrada) {
					it.setEntrada(entrada);
					entradaItensRepository.saveAndFlush(it);
					Optional<ProdutoModel> prod = produtoRepository.findById(it.getProduto().getId());
					ProdutoModel produto = prod.get();
					produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + it.getQuantidade());
					produto.setValorVenda(it.getValorVenda());
					produtoRepository.saveAndFlush(produto);
					this.listaEntrada = new ArrayList<>();
				}
				return cadastrar(new EntradaProdutoModel(), new EntradaItensModel());
			}

			return cadastrar(entrada, new EntradaItensModel());
		}

	}

