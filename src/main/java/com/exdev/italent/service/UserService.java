package com.exdev.italent.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;

import com.exdev.italent.model.User;
import com.exdev.italent.obj.UserObj;
import com.exdev.italent.utils.Utils;

public class UserService extends BaseService {

	private UserObj registerUser(UserObj obj) {
		User user = em.createNamedQuery("User.findByMobileNo", User.class).setParameter("mobileNo", obj.getMobileNo())
				.getSingleResult();

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
		obj.setSmsCode(smscode);
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		Utils.sendSMS(obj.getMobileNo(), "Confirmation code " + smscode);
		return obj;

	}

	public UserObj confirmSms(UserObj obj) {
		User user = em.createNamedQuery("User.findByMobileNo", User.class).setParameter("mobileNo", obj.getMobileNo())
				.getSingleResult();
		String code = DigestUtils.md5Hex(obj.getSmsCode());
		if (user.getCode().equals(code))
			return getUser(user.getId());
		else
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

	private void updateUser(int id, UserObj obj) {
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

	private UserObj getUser(int id) {
		User user = em.find(User.class, id);

		UserObj obj = new UserObj();

		obj.setDisabled(user.isDisabled());
		obj.setId(user.getId());
		obj.setLoginDate(user.getLoginDate());
		obj.setLogoutDate(user.getLogoutDate());
		obj.setMobileNo(user.getMobileNo());
		obj.setName(user.getName());
		obj.setNotes(user.getNotes());
		obj.setToken(user.getToken());
		obj.setUid(user.getUid());
		return obj;
	}

	private List<UserObj> listUsers(int start, int max) {
		return null;
	}

	private User login(UserObj obj) {
		return null;
	}
}
