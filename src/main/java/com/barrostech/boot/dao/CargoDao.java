package com.barrostech.boot.dao;

import java.util.List;

import com.barrostech.boot.domian.Cargo;

public interface CargoDao {

	void save(Cargo cargo);
	
	void update(Cargo cargo);
	
	void delete(Long id);
	
	Cargo findById(Long id);
	
	List<Cargo>findAll();

}
