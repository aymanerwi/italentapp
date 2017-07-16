package com.exdev.italent.service;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.exdev.italent.model.Advertisement;
import com.exdev.italent.model.Owner;
import com.exdev.italent.model.Work;
import com.exdev.italent.obj.AdvertisementObj;
import com.exdev.italent.obj.OwnerObj;
import com.exdev.italent.obj.WorkObj;

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

	protected void fillWorkObj(Work work, WorkObj obj) {
		obj.setCreateDate(work.getCreateDate());
		obj.setId(work.getId());
		obj.setLink(work.getLink());
		obj.setModifyDate(work.getModifyDate());
		obj.setNotes(work.getNotes());
		OwnerObj ownerObj = new OwnerObj();
		fillOwnerObj(work.getOwner(), new OwnerObj());
		obj.setOwner(ownerObj);
		obj.setTitle(work.getTitle());
	}

	protected void fillAdvertisementObj(Advertisement ad, AdvertisementObj obj) {
		obj.setId(ad.getId());
		obj.setCreateDate(ad.getCreateDate());
		obj.setModifyDate(obj.getModifyDate());
		obj.setExpireDate(ad.getExpireDate());
		obj.setDescription(ad.getDescription());
		obj.setDisabled(ad.getDisabled());
		obj.setLatitude(ad.getLatitude());
		obj.setLongitude(ad.getLongitude());
		obj.setNotes(ad.getNotes());
		OwnerObj ownerObj = new OwnerObj();
		fillOwnerObj(ad.getOwner(), ownerObj);
		obj.setOwner(ownerObj);
		obj.setPrice(ad.getPrice());
		obj.setUid(ad.getUid());
		obj.setUnit(ad.getUnit());
		obj.setTitle(ad.getTitle());
	}
}
