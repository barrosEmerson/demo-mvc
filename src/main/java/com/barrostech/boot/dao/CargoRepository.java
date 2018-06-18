package com.barrostech.boot.dao;

import org.springframework.stereotype.Repository;

import com.barrostech.boot.domian.Cargo;

@Repository
public class CargoRepository extends AbstractDao<Cargo, Long> implements CargoDao{


}
