package com.exdev.italent.service;

import java.util.ArrayList;
import java.util.List;

import com.exdev.italent.model.Licence;
import com.exdev.italent.model.Owner;
import com.exdev.italent.obj.LicenceObj;
import com.exdev.italent.obj.OwnerObj;

public class LicenceService extends BaseService {

	public void createLicence(LicenceObj obj) {
		Licence licence = new Licence();
		fillLicence(obj, licence);
		em.getTransaction().begin();
		em.persist(licence);
		em.getTransaction().commit();
		
		obj.setId(licence.getId());
	}

	private void fillLicence(LicenceObj obj, Licence licence) {
		licence.setImage(decodeString(obj.getImage()));
		licence.setNotes(obj.getNotes());
		Owner owner = em.find(Owner.class, obj.getOwner().getId());
		licence.setOwner(owner);
	}

	public void updateLicence(int id, LicenceObj obj) {
		Licence licence = em.find(Licence.class, id);
		fillLicence(obj, licence);
		em.getTransaction().begin();
		em.persist(licence);
		em.getTransaction().commit();
	}

	public LicenceObj getLicence(int id) {
		Licence licence = em.find(Licence.class, id);
		if(licence == null) return null;
		LicenceObj obj = new LicenceObj();
		fillLicenceObj(licence, obj);
		
		return obj;
	}

	private void fillLicenceObj(Licence licence, LicenceObj obj) {
		obj.setId(licence.getId());
		obj.setImage(encodeBytes(licence.getImage()));
		obj.setNotes(licence.getNotes());
		OwnerObj ownerObj = new OwnerObj();
		fillOwnerObj(licence.getOwner(), ownerObj);
		obj.setOwner(ownerObj);
	}

	public List<LicenceObj> listLicences(int ownerid,int start, int max) {
		List<Licence> licences = em.createNamedQuery("Licence.findAll").setParameter("ownerid", ownerid).setFirstResult(start).setMaxResults(max).getResultList();
		List<LicenceObj> objs = new ArrayList<LicenceObj>(licences.size());
		for (Licence licence : licences) {
			LicenceObj obj = new LicenceObj();
			fillLicenceObj(licence, obj);
			objs.add(obj);
		}
		return objs;
	}

	public void deleteLicence(int id) {
		Licence licence = em.find(Licence.class, id);
		em.getTransaction().begin();
		em.remove(licence);
		em.getTransaction().commit();
	}
}
