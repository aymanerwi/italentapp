package com.exdev.italent.service;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.exdev.italent.model.Owner;
import com.exdev.italent.obj.OwnerObj;

public class BaseService {

	protected EntityManager em;
	
	public BaseService() {
		em = Persistence.createEntityManagerFactory("italentapp").createEntityManager();
	}
	
	public void close() {
		if(em.isOpen())
			em.close();
	}

	protected void fillOwnerObj(Owner owner, OwnerObj obj) {
		obj.setId(owner.getId());
		obj.setCertified(owner.getCertified());
		obj.setCreateDate(owner.getCreateDate());
		obj.setModifyDate(owner.getModifyDate());
		obj.setEmail(owner.getEmail());
		obj.setInstagram(owner.getInstagram());
		obj.setUid(owner.getUid());
		obj.setName(owner.getName());
		obj.setNotes(owner.getNotes());
		obj.setPhone(owner.getPhone());
		obj.setTwitter(owner.getTwitter());
	}
}
