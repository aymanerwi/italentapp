package com.exdev.italent.obj;

import java.util.Date;
import java.util.List;

import javax.persistence.OneToMany;

import com.exdev.italent.model.Advertisement;
import com.exdev.italent.model.Licence;
import com.exdev.italent.model.Work;


public class OwnerObj extends Obj {

	private boolean disabled;
	private boolean certified;

	private String email;

	private String instagram;

	private String name;

	private String phone;

	private String twitter;
	
	private String key;
	
	private String notes;
	
	private Date createDate;
	
	private Date modifyDate;
	
	private List<AdvertisementObj> advertisements;

	private List<LicenceObj> licences;

	private List<WorkObj> works;
	
	public OwnerObj() {
	
	}
	public boolean getCertified() {
		return certified;
	}

	public void setCertified(boolean certified) {
		this.certified = certified;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public List<AdvertisementObj> getAdvertisements() {
		return advertisements;
	}
	public void setAdvertisements(List<AdvertisementObj> advertisements) {
		this.advertisements = advertisements;
	}
	public List<LicenceObj> getLicences() {
		return licences;
	}
	public void setLicences(List<LicenceObj> licences) {
		this.licences = licences;
	}
	public List<WorkObj> getWorks() {
		return works;
	}
	public void setWorks(List<WorkObj> works) {
		this.works = works;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}

	
}
