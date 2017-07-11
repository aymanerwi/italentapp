package com.exdev.italent.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.exdev.italent.model.Owner;
import com.exdev.italent.obj.OwnerObj;

public class OwnerService extends BaseService {

	private void fillOwner(OwnerObj obj, Owner owner) {
		owner.setId(obj.getId());
		owner.setName(obj.getName());
		owner.setCertified(obj.getCertified());
		owner.setEmail(owner.getEmail());
		owner.setInstagram(obj.getInstagram());
		owner.setNotes(obj.getNotes());
		owner.setNotes(obj.getNotes());
		owner.setPhone(obj.getPhone());
		owner.setTwitter(obj.getTwitter());
	}

	private void fillOwnerObj(Owner owner, OwnerObj obj) {
		obj.setId(owner.getId());
		obj.setCertified(owner.getCertified());
		obj.setCreateDate(owner.getCreateDate());
		obj.setModifyDate(owner.getModifyDate());
		obj.setEmail(owner.getEmail());
		obj.setInstagram(owner.getInstagram());
		obj.setKey(owner.getUid());
		obj.setName(owner.getName());
		obj.setNotes(owner.getNotes());
		obj.setPhone(owner.getPhone());
		obj.setTwitter(owner.getTwitter());
	}

	private Owner findOwner(int id) {
		Owner owner = em.find(Owner.class, id);
		return owner;
	}

	public OwnerObj createOwner(OwnerObj obj) {
		Owner owner = new Owner();
		fillOwner(obj, owner);
		owner.setUid(UUID.randomUUID().toString());
		owner.setCreateDate(new Date());
		em.getTransaction().begin();
		em.persist(owner);
		em.getTransaction().commit();

		obj.setId(owner.getId());
		return obj;
	}

	public void updateOwner(int id, OwnerObj obj) {
		Owner owner = findOwner(id);
		fillOwner(obj, owner);
		owner.setModifyDate(new Date());
		em.getTransaction().begin();
		em.persist(owner);
		em.getTransaction().commit();
	}

	public OwnerObj getOwner(int id) {
		Owner owner = findOwner(id);
		OwnerObj obj = new OwnerObj();
		fillOwnerObj(owner, obj);

		return obj;
	}

	public List<OwnerObj> listOwners(int start, int max) {
		List<Owner> owners = em.createNamedQuery("Owner.findAll").setFirstResult(start).setMaxResults(max)
				.getResultList();
		List<OwnerObj> objs = new ArrayList<OwnerObj>(owners.size());
		for(Owner owner : owners) {
			OwnerObj obj = new OwnerObj();
			fillOwnerObj(owner, obj);
			objs.add(obj);
		}
		return objs;
	}
	
	public void delete(int id) {
		Owner owner = findOwner(id);
		em.getTransaction().begin();
		em.remove(owner);
		em.getTransaction().commit();
	}

}
