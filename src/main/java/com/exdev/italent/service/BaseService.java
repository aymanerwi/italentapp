package com.exdev.italent.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.apache.commons.codec.binary.Base64;

import com.exdev.italent.model.Advertisement;
import com.exdev.italent.model.Comment;
import com.exdev.italent.model.Licence;
import com.exdev.italent.model.Owner;
import com.exdev.italent.model.Work;
import com.exdev.italent.obj.AdvertisementObj;
import com.exdev.italent.obj.CommentObj;
import com.exdev.italent.obj.LicenceObj;
import com.exdev.italent.obj.OwnerObj;
import com.exdev.italent.obj.WorkObj;

public class BaseService {

	protected EntityManager em;

	public BaseService() {
		em = Persistence.createEntityManagerFactory("italentapp").createEntityManager();
	}

	public void close() {
		if (em.isOpen())
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
		obj.setTitle(work.getTitle());
		obj.setImage(encodeBytes(work.getImage()));
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

		List<Work> works = ad.getWorks();
		List<WorkObj> workObjs = toWorkObjsList(works);
		obj.setWorks(workObjs);

		List<Licence> licences = ad.getLicences();
		List<LicenceObj> licenceObjs = toLicenceObjsList(licences);
		obj.setLicences(licenceObjs);
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

	protected void fillCommentObj(Comment comment, CommentObj obj) {
		obj.setId(comment.getId());
		obj.setRate(comment.getRate());
		obj.setCreateDate(comment.getCreateDate());
		obj.setDisabled(comment.getDisabled());
		obj.setName(comment.getName());
		obj.setComments(comment.getComments());
		//
		// AdvertisementObj advertisementObj = new AdvertisementObj();
		// fillAdvertisementObj(comment.getAdvertisement(), advertisementObj);
		//
		// obj.setAdvertisement(advertisementObj);
	}

	public List<CommentObj> listComments(int adid, int start, int max) {

		List<Comment> comments = em.createNamedQuery("Comment.findAll", Comment.class).setParameter("adid", adid)
				.setFirstResult(start).setMaxResults(max).getResultList();
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

	protected List<LicenceObj> toLicenceObjsList(List<Licence> licences) {
		List<LicenceObj> objs = new ArrayList<LicenceObj>(licences.size());
		for (Licence licence : licences) {
			LicenceObj obj = new LicenceObj();
			fillLicenceObj(licence, obj);
			objs.add(obj);
		}
		return objs;
	}

	protected void fillLicenceObj(Licence licence, LicenceObj obj) {
		obj.setId(licence.getId());
		obj.setImage(encodeBytes(licence.getImage()));
		obj.setNotes(licence.getNotes());
		// AdvertisementObj adObj = new AdvertisementObj();
		// fillAdvertisementObj(licence.getAdvertisement(), adObj);
		// obj.setAd(adObj);
	}

	protected void fillWork(WorkObj obj, Work work) {
		work.setNotes(obj.getNotes());
		work.setTitle(obj.getTitle());
		work.setLink(obj.getLink());
		work.setImage(decodeString(obj.getImage()));

		AdvertisementObj adObj = obj.getAdvertisementObj();
		if (adObj == null)
			return;
		Advertisement ad = em.find(Advertisement.class, adObj.getId());
		work.setAdvertisement(ad);
	}

	protected void fillLicence(LicenceObj obj, Licence licence) {
		licence.setImage(decodeString(obj.getImage()));
		licence.setNotes(obj.getNotes());
		AdvertisementObj adObj = obj.getAd();
		if (adObj == null)
			return;
		Advertisement ad = em.find(Advertisement.class, adObj.getId());
		licence.setAdvertisement(ad);
	}
}
