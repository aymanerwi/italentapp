package com.exdev.italent.obj;

import java.util.Date;
import java.util.List;

import com.exdev.italent.model.Licence;

public class AdvertisementObj extends Obj {

	private Date createDate;

	private String description;

	private boolean disabled;

	private Date expireDate;

	private String uid;
	
	private String image;

	private double latitude;

	private double longitude;

	private Date modifyDate;

	private String notes;

	private String price;

	private String title;

	private String unit;

	private OwnerObj owner;

	private List<CommentObj> comments;
	private List<WorkObj> works;
	private List<LicenceObj> licences;
	
	public AdvertisementObj() {
	
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}


	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public OwnerObj getOwner() {
		return owner;
	}

	public void setOwner(OwnerObj owner) {
		this.owner = owner;
	}

	public List<CommentObj> getComments() {
		return comments;
	}

	public void setComments(List<CommentObj> comments) {
		this.comments = comments;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<WorkObj> getWorks() {
		return works;
	}

	public void setWorks(List<WorkObj> works) {
		this.works = works;
	}

	public List<LicenceObj> getLicences() {
		return licences;
	}

	public void setLicences(List<LicenceObj> licences) {
		this.licences = licences;
	}
	
	
}
