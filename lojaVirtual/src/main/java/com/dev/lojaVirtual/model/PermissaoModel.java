package com.dev.lojaVirtual.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "permissoes")
public class PermissaoModel implements Serializable {

	public PermissaoModel() {
		super();
	}

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Temporal(TemporalType.DATE)
	private Date dataCadastro = new Date();
	
	@ManyToOne
	private FuncionarioModel funcionario;
	
	@ManyToOne
	private PapelModel papel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public FuncionarioModel getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioModel funcionario) {
		this.funcionario = funcionario;
	}

	public PapelModel getPapel() {
		return papel;
	}

	public void setPapel(PapelModel papel) {
		this.papel = papel;
	}
}