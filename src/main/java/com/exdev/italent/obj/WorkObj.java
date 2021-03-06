package com.exdev.italent.obj;

import java.util.Date;

public class WorkObj extends Obj{

	private Date createDate;

	private String link;

	private Date modifyDate;

	private String notes;

	private String title;
	
	private String image;
	
	private AdvertisementObj ad;
	
	
	public WorkObj() {
	
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public AdvertisementObj getAd() {
		return ad;
	}

	public void setAd(AdvertisementObj ad) {
		this.ad = ad;
	}

	
	
	
}
