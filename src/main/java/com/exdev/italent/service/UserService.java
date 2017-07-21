package com.exdev.italent.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;

import com.exdev.italent.model.User;
import com.exdev.italent.obj.UserObj;
import com.exdev.italent.utils.Utils;

public class UserService extends BaseService {

	public UserObj registerUser(UserObj obj) {
		User user = null;
		try {
			user = em.createNamedQuery("User.findByMobileNo", User.class).setParameter("mobileNo", obj.getMobileNo())
					.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (user == null) {
			user = new User();

			user.setMobileNo(obj.getMobileNo());
			user.setName(obj.getName());
			user.setToken(obj.getToken());
			user.setNotes(obj.getNotes());
			user.setUid(UUID.randomUUID().toString());
			user.setCreateDate(new Date());

		} else {
			user.setMobileNo(obj.getMobileNo());
			user.setName(obj.getName());
			user.setToken(obj.getToken());
			user.setNotes(obj.getNotes());
			user.setModifyDate(new Date());
		}
		String smscode = RandomStringUtils.randomNumeric(4);
		String code = DigestUtils.md5Hex(smscode);
		user.setCode(code);
		user.setDisabled(true);
		obj.setSmsCode(smscode);
		obj.setId(user.getId());
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		//Utils.sendSMS(obj.getMobileNo(), "Confirmation code " + smscode);
		return obj;

	}

	public UserObj confirmSms(UserObj obj) {
		User user = null;
		try {
			user = em.createNamedQuery("User.findByMobileNo", User.class).setParameter("mobileNo", obj.getMobileNo())
					.getSingleResult();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		if (user == null)
			return null;
		String code = DigestUtils.md5Hex(obj.getSmsCode());
		if (user.getCode().equals(code)) {
			user.setLoginDate(new Date());
			user.setDisabled(false);
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
			return getUser(user.getId());
		} else
			return null;
	}

	private void createUser(UserObj obj) {
		User user = new User();

		user.setMobileNo(obj.getMobileNo());
		user.setName(obj.getName());
		user.setToken(obj.getToken());
		user.setNotes(obj.getNotes());
		user.setUid(UUID.randomUUID().toString());
		user.setCreateDate(new Date());

		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();

	}

	public void updateUser(int id, UserObj obj) {
		User user = em.find(User.class, id);

		user.setMobileNo(obj.getMobileNo());
		user.setName(obj.getName());
		user.setToken(obj.getToken());
		user.setNotes(obj.getNotes());
		user.setModifyDate(new Date());

		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();

	}

	public UserObj getUser(int id) {
		User user = em.find(User.class, id);
		if(user == null) return null;
		UserObj obj = new UserObj();

		fillUserObj(user, obj);
		return obj;
	}

	private void fillUserObj(User user, UserObj obj) {
		obj.setDisabled(user.isDisabled());
		obj.setId(user.getId());
		obj.setLoginDate(user.getLoginDate());
		obj.setLogoutDate(user.getLogoutDate());
		obj.setMobileNo(user.getMobileNo());
		obj.setName(user.getName());
		obj.setNotes(user.getNotes());
		obj.setToken(user.getToken());
		obj.setUid(user.getUid());
	}

	public List<UserObj> listUsers(int start, int max) {
		List<User> users = em.createNamedQuery("User.findAll", User.class).setFirstResult(start).setMaxResults(max)
				.getResultList();
		List<UserObj> objs = new ArrayList<UserObj>(users.size());
		for (User user : users) {
			UserObj obj = new UserObj();
			fillUserObj(user, obj);
			objs.add(obj);
		}
		return objs;
	}

	public User login(UserObj obj) {

		return null;
	}

	public void deleteUser(int id) {
		User user = em.find(User.class, id);
		em.getTransaction().begin();
		em.remove(user);
		em.getTransaction().commit();
	}

}
