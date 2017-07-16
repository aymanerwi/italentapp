package com.exdev.italent.service;

import java.util.ArrayList;
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

	private void fillWork(WorkObj obj, Work work) {
		work.setId(obj.getId());
		work.setNotes(obj.getNotes());
		work.setTitle(obj.getTitle());
		work.setLink(obj.getLink());

		OwnerObj ownerObj = obj.getOwner();
		Owner owner = em.find(Owner.class, ownerObj.getId());
		work.setOwner(owner);
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

		WorkObj obj = new WorkObj();

		fillWorkObj(work, obj);

		return obj;

	}

	public List<WorkObj> listWorks(int ownerid,int start, int max) {
		List<Work> works = em.createNamedQuery("Work.findAll", Work.class).setParameter("ownerid", ownerid).setFirstResult(start).setMaxResults(max)
				.getResultList();

		List<WorkObj> objs = new ArrayList<WorkObj>(works.size());

		for (Work work : works) {
			WorkObj obj = new WorkObj();
			fillWorkObj(work, obj);
			objs.add(obj);

		}

		return objs;
	}

	public void deleteWork(int id) {
		Work work = em.find(Work.class, id);
		em.getTransaction().begin();
		em.remove(work);
		em.getTransaction().commit();
	}

}
