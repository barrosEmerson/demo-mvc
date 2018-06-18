package com.barrostech.boot.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.*;

public abstract class AbstractDao<T,PK extends Serializable> {
	
	@SuppressWarnings("unchecked")
	private final Class<T>entityClass = 
			(Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

	@PersistenceContext
	private EntityManager entityManeger;

	protected EntityManager getEntityManeger() {
		return entityManeger;
	}

	public void save(T persist) {
		entityManeger.persist(persist);
	}
	
	public void update(T persist) {
		entityManeger.merge(persist);
	}
	
	public void delete(PK id) {
		entityManeger.remove(entityManeger.getReference(entityClass, id));
	}
	
	public T findById(PK id) {
		return entityManeger.find(entityClass, id);
	}
	
	public List<T> findAll(){
		return entityManeger.createQuery("from "+ entityClass.getSimpleName(),entityClass).getResultList();
	}
	
	protected List<T> createQuery(String jpql, Object... params){
		TypedQuery<T> query = entityManeger.createQuery(jpql, entityClass);
		for (int i=0;i < params.length;i++) {
			query.setParameter(i+1, params[i]);
		}
		return query.getResultList();
		}

	}

