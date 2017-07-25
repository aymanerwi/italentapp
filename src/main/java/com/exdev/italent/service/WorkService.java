package com.exdev.italent.service;

import java.util.Date;
import java.util.List;

import com.exdev.italent.model.Owner;
import com.exdev.italent.model.Work;
import com.exdev.italent.obj.OwnerObj;
import com.exdev.italent.obj.WorkObj;

public class WorkService extends BaseService {

	public void createWork(WorkObj obj) {
		Work work = new Work();

		work.setCreateDate(new Date());
		fillWork(obj, work);

		em.getTransaction().begin();
		em.persist(work);
		em.getTransaction().commit();

		obj.setId(work.getId());
	}

	public void updateWork(int id, WorkObj obj) {
		Work work = em.find(Work.class, id);

		work.setModifyDate(new Date());
		fillWork(obj, work);

		em.getTransaction().begin();
		em.persist(work);
		em.getTransaction().commit();

	}

	public WorkObj getWork(int id) {
		Work work = em.find(Work.class, id);
		if(work == null) return null;
		WorkObj obj = new WorkObj();

		fillWorkObj(work, obj);

		return obj;

	}

	public List<WorkObj> listWorks(int ownerid,int start, int max) {
		List<Work> works = em.createNamedQuery("Work.findAll", Work.class).setParameter("ownerid", ownerid).setFirstResult(start).setMaxResults(max)
				.getResultList();

		List<WorkObj> objs = toWorkObjsList(works);

		return objs;
	}

	public void deleteWork(int id) {
		Work work = em.find(Work.class, id);
		em.getTransaction().begin();
		em.remove(work);
		em.getTransaction().commit();
	}

}
