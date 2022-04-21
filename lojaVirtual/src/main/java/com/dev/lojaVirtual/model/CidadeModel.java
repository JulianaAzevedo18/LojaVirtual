package com.dev.lojaVirtual.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cidade")
	public class CidadeModel implements Serializable {

		public CidadeModel() {
			super();
		}

		private static final long serialVersionUID = 1L;
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;

		private String nome;
		
		@ManyToOne
		private EstadoModel estado;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public EstadoModel getEstado() {
			return estado;
		}

		public void setEstado(EstadoModel estado) {
			this.estado = estado;
		}

		@Override
		public String toString() {
			return nome + "-"+estado.getSigla();
		}

}
