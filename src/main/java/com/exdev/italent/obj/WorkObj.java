package com.exdev.italent.obj;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.exdev.italent.model.Owner;
import com.exdev.italent.model.WorkDetails;

public class WorkObj extends Obj{

	private Date createDate;

	private String link;

	private Date modifyDate;

	private String notes;

	private String title;
	
	
	private OwnerObj owner;
	
	private List<WorkDetailsObj> workDetails;
	
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

	public OwnerObj getOwner() {
		return owner;
	}

	public void setOwner(OwnerObj owner) {
		this.owner = owner;
	}

	public List<WorkDetailsObj> getWorkDetails() {
		return workDetails;
	}

	public void setWorkDetails(List<WorkDetailsObj> workDetails) {
		this.workDetails = workDetails;
	}
	
	
	
}