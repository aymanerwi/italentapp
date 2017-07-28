package com.exdev.italent.obj;

import java.util.Date;

public class CommentObj extends Obj {

	private String comments;

	private Date createDate;

	private boolean disabled;

	private String name;

	private int rate;
	
	private AdvertisementObj ad;
	
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

	public AdvertisementObj getAd() {
		return ad;
	}

	public void setAd(AdvertisementObj ad) {
		this.ad = ad;
	}

	
	
}
