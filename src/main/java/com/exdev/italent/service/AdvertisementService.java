package com.exdev.italent.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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

public class AdvertisementService extends BaseService {

	public void createAdvertisement(AdvertisementObj obj) {
		Advertisement ad = new Advertisement();

		ad.setCreateDate(new Date());

		fillAdvertisement(obj, ad);

		List<WorkObj> workObjs = obj.getWorks();
		List<Work> works = new ArrayList<>(workObjs.size());
		for (WorkObj workObj : workObjs) {
			Work work = new Work();
			fillWork(workObj, work);
			work.setAdvertisement(ad);
			works.add(work);
		}
		ad.setWorks(works);

		List<LicenceObj> licenceObjs = obj.getLicences();
		List<Licence> licences = new ArrayList<>(licenceObjs.size());
		for (LicenceObj licenceObj : licenceObjs) {
			Licence licence = new Licence();
			fillLicence(licenceObj, licence);
			licence.setAdvertisement(ad);
			licences.add(licence);
		}
		ad.setLicences(licences);

		List<CommentObj> commentObjs = obj.getComments();
		List<Comment> comments = new ArrayList<>(commentObjs.size());
		for (CommentObj commentObj : commentObjs) {
			Comment comment = new Comment();
			fillComment(commentObj, comment);
			comment.setAdvertisement(ad);
			comments.add(comment);
		}
		ad.setComments(comments);

		ad.setUid(UUID.randomUUID().toString());

		em.getTransaction().begin();
		em.persist(ad);
		em.getTransaction().commit();

		obj.setId(ad.getId());
	}

	private void fillAdvertisement(AdvertisementObj obj, Advertisement ad) {
		OwnerObj ownerObj = obj.getOwner();
		Owner owner = em.find(Owner.class, ownerObj.getId());
		if (owner == null)
			throw new NullPointerException("Owner cannot be null");
		ad.setOwner(owner);

		ad.setImage(decodeString(obj.getImage()));
		ad.setDescription(obj.getDescription());
		ad.setExpireDate(obj.getExpireDate());
		ad.setDisabled(obj.isDisabled());
		ad.setLatitude(obj.getLatitude());
		ad.setLongitude(obj.getLongitude());
		ad.setTitle(obj.getTitle());
		ad.setNotes(obj.getNotes());
		ad.setPrice(obj.getPrice());
		ad.setUnit(obj.getUnit());
	}

	public void updateAdvertisement(int id, AdvertisementObj obj) {
		Advertisement ad = em.find(Advertisement.class, id);
		ad.setModifyDate(new Date());

		fillAdvertisement(obj, ad);

		List<WorkObj> workObjs = obj.getWorks();
		List<Work> works = new ArrayList<>(workObjs.size());
		for (WorkObj workObj : workObjs) {
			Work work = em.find(Work.class, workObj.getId());
			if (work == null)
				work = new Work();
			fillWork(workObj, work);
			work.setAdvertisement(ad);
			works.add(work);
		}
		ad.setWorks(works);

		List<LicenceObj> licenceObjs = obj.getLicences();
		List<Licence> licences = new ArrayList<>(licenceObjs.size());
		for (LicenceObj licenceObj : licenceObjs) {
			Licence licence = em.find(Licence.class, licenceObj.getId());
			if (licence == null)
				licence = new Licence();
			fillLicence(licenceObj, licence);
			licence.setAdvertisement(ad);
			licences.add(licence);
		}
		ad.setLicences(licences);

		List<CommentObj> commentObjs = obj.getComments();
		List<Comment> comments = new ArrayList<>(commentObjs.size());
		for (CommentObj commentObj : commentObjs) {
			Comment comment = em.find(Comment.class, commentObj.getId());
			if (comment == null)
				comment = new Comment();
			fillComment(commentObj, comment);
			comment.setAdvertisement(ad);
			comments.add(comment);
		}
		ad.setComments(comments);

		em.getTransaction().begin();
		em.persist(ad);
		em.getTransaction().commit();
	}

	public AdvertisementObj getAdvertisement(int id) {
		Advertisement ad = em.find(Advertisement.class, id);
		if (ad == null)
			return null;
		AdvertisementObj obj = new AdvertisementObj();
		fillAdvertisementObj(ad, obj);
		return obj;
	}

	public List<AdvertisementObj> listAdvertisements(int start, int max) {
		List<Advertisement> ads = em.createNamedQuery("Advertisement.findAll").setFirstResult(start).setMaxResults(max)
				.getResultList();
		List<AdvertisementObj> objs = new ArrayList<AdvertisementObj>(ads.size());
		for (Advertisement ad : ads) {
			AdvertisementObj obj = new AdvertisementObj();
			fillAdvertisementObj(ad, obj);
			objs.add(obj);
		}
		return objs;
	}

	public List<AdvertisementObj> listAdvertisements(int ownerid, int start, int max) {
		List<Advertisement> ads = em.createNamedQuery("Advertisement.findByOwner").setParameter("ownerid", ownerid)
				.setFirstResult(start).setMaxResults(max).getResultList();
		List<AdvertisementObj> objs = new ArrayList<AdvertisementObj>(ads.size());
		for (Advertisement ad : ads) {
			AdvertisementObj obj = new AdvertisementObj();
			fillAdvertisementObj(ad, obj);
			objs.add(obj);
		}
		return objs;
	}

	public void deleteAdvertisement(int id) {
		Advertisement ad = em.find(Advertisement.class, id);
		em.getTransaction().begin();
		em.remove(ad);
		em.getTransaction().commit();
	}
}
