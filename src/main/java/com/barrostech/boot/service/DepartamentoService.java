package com.barrostech.boot.service;

import java.util.List;

import com.barrostech.boot.domian.Departamento;

public interface DepartamentoService {
	
	boolean departamentoTemCargo(Long id);

	void salvar(Departamento departamento);
	
	void editar(Departamento departamento);
	
	void excluir(Long id);
	
	Departamento buscarPorId(Long id);
	
	List<Departamento> buscarTodos();

}
