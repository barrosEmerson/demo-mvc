package com.barrostech.boot.domian;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@SuppressWarnings("serial")
@Entity
@Table(name = "CARGOS")
public class Cargo extends AbstractEntity<Long> {
	
	@OneToMany(mappedBy = "cargo")
	private List<Funcionario>funcionario;

	public List<Funcionario> getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(List<Funcionario> funcionario) {
		this.funcionario = funcionario;
	}

	@NotBlank(message = "Informe o cargo")
	@Size( max = 60,message = "O nome do cargo deve ter no maximo 60 caracteres")
	@Column(name = "nome", nullable = false, unique = true, length = 60)
	private String nome;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	@NotNull(message = "Selecione departamento relativo ao cargo.")
	@ManyToOne
	@JoinColumn(name = "id_departamento_fk")
	private Departamento departamento;
}
