package com.exdev.italent.service;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class BaseService {

	protected EntityManager em;
	
	public BaseService() {
		em = Persistence.createEntityManagerFactory("italentapp").createEntityManager();
	}
	
	public void close() {
		if(em.isOpen())
			em.close();
	}
}
