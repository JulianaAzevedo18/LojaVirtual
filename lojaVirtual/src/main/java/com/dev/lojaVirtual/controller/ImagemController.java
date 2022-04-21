package com.dev.lojaVirtual.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ImagemController {

		private static String caminhoImagens = "/home/frank/Documents/imagens/";


		@GetMapping("/mostrarImagem/{imagem}")
		@ResponseBody
		public byte[] retornarImagem(@PathVariable("imagem") String imagem) throws IOException {
//			System.out.println(imagem);
			File imagemArquivo = new File(caminhoImagens + imagem);
			if (imagem != null || imagem.trim().length() > 0) {
				System.out.println("No IF");
				return Files.readAllBytes(imagemArquivo.toPath());
			}
			return null;
		}
}