package com.exdev.italent.service;

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

	public void updateLicence(int id, LicenceObj obj) {
		Licence licence = em.find(Licence.class, id);
		if(licence == null) return;
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

	public List<LicenceObj> listLicences(int ownerid,int start, int max) {
		List<Licence> licences = em.createNamedQuery("Licence.findAll").setParameter("ownerid", ownerid).setFirstResult(start).setMaxResults(max).getResultList();
		List<LicenceObj> objs = toLicenceObjsList(licences);
		return objs;
	}

	public void deleteLicence(int id) {
		Licence licence = em.find(Licence.class, id);
		em.getTransaction().begin();
		em.remove(licence);
		em.getTransaction().commit();
	}
}
