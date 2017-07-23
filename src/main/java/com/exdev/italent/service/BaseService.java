package com.exdev.italent.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.apache.commons.codec.binary.Base64;

import com.exdev.italent.model.Advertisement;
import com.exdev.italent.model.Comment;
import com.exdev.italent.model.Owner;
import com.exdev.italent.model.Work;
import com.exdev.italent.model.WorkDetails;
import com.exdev.italent.obj.AdvertisementObj;
import com.exdev.italent.obj.CommentObj;
import com.exdev.italent.obj.OwnerObj;
import com.exdev.italent.obj.WorkDetailsObj;
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
		
		List<Work> works = owner.getWorks();
		List<WorkObj> workObjs = toWorkObjsList(works);
		obj.setWorks(workObjs);
	}

	protected void fillWorkObj(Work work, WorkObj obj) {
		obj.setCreateDate(work.getCreateDate());
		obj.setId(work.getId());
		obj.setLink(work.getLink());
		obj.setModifyDate(work.getModifyDate());
		obj.setNotes(work.getNotes());
		OwnerObj ownerObj = new OwnerObj();
		fillOwnerObj(work.getOwner(), ownerObj);
		obj.setOwner(ownerObj);
		obj.setTitle(work.getTitle());
		
		List<WorkDetails> details = work.getWorkDetails();
		
		List<WorkDetailsObj> detailsObjs = toWorkDetailsObjsList(details);
		obj.setWorkDetails(detailsObjs);
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
		obj.setImage(encodeBytes(ad.getImage()));
		
		List<Comment> comments = ad.getComments();
		List<CommentObj> commentObjs = toCommentObjsList(comments);
		obj.setComments(commentObjs);
	}
	
	public byte[] decodeString(String str) {
		if (str == null)
			return null;
		return Base64.decodeBase64(str);
	}

	protected String encodeBytes(byte[] data) {
		if (data == null)
			return null;
		return Base64.encodeBase64String(data);
	}

	protected List<WorkObj> toWorkObjsList(List<Work> works) {
		List<WorkObj> objs = new ArrayList<WorkObj>(works.size());
	
		for (Work work : works) {
			WorkObj obj = new WorkObj();
			fillWorkObj(work, obj);
			objs.add(obj);
	
		}
		return objs;
	}

	protected List<WorkDetailsObj> toWorkDetailsObjsList(List<WorkDetails> details) {
		List<WorkDetailsObj> objs = new ArrayList<WorkDetailsObj>(details.size());
		for(WorkDetails detail : details) {
			WorkDetailsObj obj = new WorkDetailsObj();
			fillWorkDetailsObj(detail, obj);
			objs.add(obj);
		}
		return objs;
	}

	protected void fillWorkDetailsObj(WorkDetails details, WorkDetailsObj obj) {
		obj.setId(details.getId());
		obj.setImage(encodeBytes(details.getImage()));
		obj.setLink(details.getLink());
		obj.setNotes(details.getNotes());
		
		WorkObj workObj = new WorkObj();
		fillWorkObj(details.getWork(), workObj);
		
		obj.setWork(workObj);
	}

	protected void fillCommentObj(Comment comment, CommentObj obj) {
		obj.setId(comment.getId());
		obj.setRate(comment.getRate());
		obj.setCreateDate(comment.getCreateDate());
		obj.setDisabled(comment.getDisabled());
		obj.setName(comment.getName());
		obj.setComments(comment.getComments());
		
		AdvertisementObj advertisementObj = new AdvertisementObj();
		fillAdvertisementObj(comment.getAdvertisement(), advertisementObj);
		
		obj.setAdvertisement(advertisementObj);
	}

	public List<CommentObj> listComments(int adid, int start, int max) {
		
		List<Comment> comments = em.createNamedQuery("Comment.findAll",Comment.class).setParameter("adid", adid).setFirstResult(start).setMaxResults(max).getResultList();
		List<CommentObj> objs = toCommentObjsList(comments);
		return objs;
	}

	private List<CommentObj> toCommentObjsList(List<Comment> comments) {
		List<CommentObj> objs = new ArrayList<CommentObj>(comments.size());
		for (Comment comment : comments) {
			CommentObj obj = new CommentObj();
			fillCommentObj(comment, obj);
			objs.add(obj);
		}
		return objs;
	}
}
