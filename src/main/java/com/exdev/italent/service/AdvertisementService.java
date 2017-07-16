package com.exdev.italent.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.exdev.italent.model.Advertisement;
import com.exdev.italent.model.Owner;
import com.exdev.italent.obj.AdvertisementObj;
import com.exdev.italent.obj.OwnerObj;

public class AdvertisementService extends BaseService {

	public void createAdvertisement(AdvertisementObj obj) {
		Advertisement ad = new Advertisement();
		
		
		ad.setCreateDate(new Date());
		
		fillAdvertisement(obj, ad);
		
		em.getTransaction().begin();
		em.persist(ad);
		em.getTransaction().commit();
		
		obj.setId(ad.getId());
	}

	private void fillAdvertisement(AdvertisementObj obj, Advertisement ad) {
		OwnerObj ownerObj = obj.getOwner();
		Owner owner = em.find(Owner.class, ownerObj.getId());
		ad.setOwner(owner);
		ad.setDescription(obj.getDescription());
		ad.setUid(obj.getUid());
		ad.setExpireDate(obj.getExpireDate());
		ad.setDisabled(obj.isDisabled());
		ad.setLatitude(obj.getLatitude());
		ad.setLongitude(obj.getLongitude());
		ad.setId(obj.getId());
		ad.setTitle(obj.getTitle());
		ad.setNotes(obj.getNotes());
		ad.setPrice(obj.getPrice());
		ad.setUnit(obj.getUnit());
	}
	
	public void updateAdvertisement(int id, AdvertisementObj obj) {
		Advertisement ad = em.find(Advertisement.class, id);
		ad.setModifyDate(new Date());
		
		fillAdvertisement(obj, ad);
		
		em.getTransaction().begin();
		em.persist(ad);
		em.getTransaction().commit();
	}
	
	public AdvertisementObj getAdvertisement(int id) {
		Advertisement ad = em.find(Advertisement.class, id);
		
		AdvertisementObj obj = new AdvertisementObj();
		fillAdvertisementObj(ad, obj);
		return obj;
	}

	public List<AdvertisementObj> listAdvertisements(int start, int max) {
		List<Advertisement> ads = em.createNamedQuery("Advertisement.findAll").setFirstResult(start).setMaxResults(max).getResultList();
		List<AdvertisementObj> objs = new ArrayList<AdvertisementObj>(ads.size());
		for(Advertisement ad : ads) {
			AdvertisementObj obj = new AdvertisementObj();
			fillAdvertisementObj(ad, obj);
			objs.add(obj);
		}
		return objs;
	}
	
	public List<AdvertisementObj> listAdvertisements(int ownerid,int start, int max) {
		List<Advertisement> ads = em.createNamedQuery("Advertisement.findByOwner").setParameter("ownerid", ownerid).setFirstResult(start).setMaxResults(max).getResultList();
		List<AdvertisementObj> objs = new ArrayList<AdvertisementObj>(ads.size());
		for(Advertisement ad : ads) {
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
