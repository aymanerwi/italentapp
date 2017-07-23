package com.exdev.italent.service;

import java.util.List;

import com.exdev.italent.model.Work;
import com.exdev.italent.model.WorkDetails;
import com.exdev.italent.obj.WorkDetailsObj;

public class WorkDetailsService extends BaseService {
	public void createWorkDetails(WorkDetailsObj obj) {
		
		WorkDetails details = new WorkDetails();
		fillWorkDetails(obj, details);
		
		em.getTransaction().begin();
		em.persist(details);
		em.getTransaction().commit();
		
		obj.setId(details.getId());

	}

	private void fillWorkDetails(WorkDetailsObj obj, WorkDetails details) {
		details.setImage(decodeString(obj.getImage()));
		details.setLink(obj.getLink());
		details.setNotes(obj.getNotes());
		Work work = em.find(Work.class, obj.getWork().getId());
		details.setWork(work);
	}

	public void updateWorkDetails(int id, WorkDetailsObj obj) {
		WorkDetails details = em.find(WorkDetails.class, id);
		fillWorkDetails(obj, details);
		

		em.getTransaction().begin();
		em.persist(details);
		em.getTransaction().commit();
		
		
	}

	public WorkDetailsObj getWorkDetails(int id) {
		WorkDetails details = em.find(WorkDetails.class, id);
		if(details == null) return null;
		WorkDetailsObj obj = new WorkDetailsObj();
		fillWorkDetailsObj(details, obj);
		return obj;
	}

	public List<WorkDetailsObj> listWorkDetails(int workid,int start, int max) {
		
		List<WorkDetails> details = em.createNamedQuery("WorkDetails.findAll",WorkDetails.class).setParameter("workid", workid).setFirstResult(start).setMaxResults(max).getResultList();
		
		List<WorkDetailsObj> objs = toWorkDetailsObjsList(details);
		return objs;
	}

	public void deleteWorkDetails(int id) {
		WorkDetails details = em.find(WorkDetails.class, id);
		em.getTransaction().begin();
		em.remove(details);
		em.getTransaction().commit();
	}
}
