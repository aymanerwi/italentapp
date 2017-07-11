package com.exdev.italent.obj;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class CommentObj extends Obj {

	private String comments;

	private Date createDate;

	private boolean disabled;

	private String name;

	private int rate;
	
	private AdvertisementObj advertisement;
	
	public CommentObj() {
	
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public AdvertisementObj getAdvertisement() {
		return advertisement;
	}

	public void setAdvertisement(AdvertisementObj advertisement) {
		this.advertisement = advertisement;
	}
	
	
}
