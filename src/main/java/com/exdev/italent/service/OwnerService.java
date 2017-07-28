package com.exdev.italent.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;

import com.exdev.italent.model.Owner;
import com.exdev.italent.obj.OwnerObj;
import com.exdev.italent.utils.Utils;
import com.exdev.italent.obj.ConfirmObj;

public class OwnerService extends BaseService {

	public OwnerObj registerOwner(OwnerObj obj, boolean sendsms) {
		Owner owner = null;
		try {
			owner = em.createNamedQuery("Owner.findByPhone", Owner.class).setParameter("phone", obj.getPhone())
					.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (owner == null) {
			owner = new Owner();
			owner.setCreateDate(new Date());

		} else {
			owner.setModifyDate(new Date());

		}
		String smscode = RandomStringUtils.randomNumeric(4);
		String code = DigestUtils.md5Hex(smscode);
		owner.setSmsCode(code);
		owner.setDisabled(true);
		owner.setPhone(obj.getPhone());
		owner.setName(obj.getName());
		owner.setNotes(obj.getNotes());
		owner.setUid(UUID.randomUUID().toString());
		owner.setCertified(obj.getCertified());

		em.getTransaction().begin();
		em.persist(owner);
		em.getTransaction().commit();
		obj.setSmsCode(smscode);
		obj.setId(owner.getId());
		if (sendsms)
			Utils.sendSMS(obj.getPhone(), "Confirmation code " + smscode);
		return obj;

	}

	public OwnerObj confirmSms(ConfirmObj obj) throws Exception {
		Owner owner = null;
		try {
			owner = em.createNamedQuery("Owner.findByPhone", Owner.class).setParameter("phone", obj.getMobileNo())
					.getSingleResult();
		} catch (Exception e) {
			throw new Exception("Owner not found");
		}

		String code = DigestUtils.md5Hex(obj.getSmsCode());
		if (owner.getSmsCode().equals(code)) {
			owner.setLoginDate(new Date());
			owner.setDisabled(false);
			em.getTransaction().begin();
			em.persist(owner);
			em.getTransaction().commit();
			OwnerObj ownerObj = new OwnerObj();
			fillOwnerObj(owner, ownerObj);
			return ownerObj;
		} else
			throw new Exception("SMS code not maches");
	}

	private void fillOwner(OwnerObj obj, Owner owner) {
		owner.setName(obj.getName());
		owner.setCertified(obj.getCertified());
		owner.setEmail(obj.getEmail());
		owner.setInstagram(obj.getInstagram());
		owner.setNotes(obj.getNotes());
		owner.setNotes(obj.getNotes());
		owner.setPhone(obj.getPhone());
		owner.setTwitter(obj.getTwitter());
	}

	private Owner findOwner(int id) {
		Owner owner = em.find(Owner.class, id);
		return owner;
	}

	public OwnerObj createOwner(OwnerObj obj) {
		Owner owner = new Owner();
		fillOwner(obj, owner);
		owner.setUid(UUID.randomUUID().toString());
		owner.setCreateDate(new Date());
		em.getTransaction().begin();
		em.persist(owner);
		em.getTransaction().commit();

		obj.setId(owner.getId());
		return obj;
	}

	public void updateOwner(int id, OwnerObj obj) {
		Owner owner = findOwner(id);
		fillOwner(obj, owner);
		owner.setModifyDate(new Date());
		em.getTransaction().begin();
		em.persist(owner);
		em.getTransaction().commit();
	}

	public OwnerObj getOwner(int id) {
		Owner owner = findOwner(id);
		if (owner == null)
			return null;
		OwnerObj obj = new OwnerObj();
		fillOwnerObj(owner, obj);
		return obj;
	}

	public List<OwnerObj> listOwners(int start, int max) {
		List<Owner> owners = em.createNamedQuery("Owner.findAll").setFirstResult(start).setMaxResults(max)
				.getResultList();
		List<OwnerObj> objs = new ArrayList<OwnerObj>(owners.size());
		for (Owner owner : owners) {
			OwnerObj obj = new OwnerObj();
			fillOwnerObj(owner, obj);
			objs.add(obj);
		}
		return objs;
	}

	public void deleteOwner(int id) {
		Owner owner = findOwner(id);
		em.getTransaction().begin();
		em.remove(owner);
		em.getTransaction().commit();
	}

}
