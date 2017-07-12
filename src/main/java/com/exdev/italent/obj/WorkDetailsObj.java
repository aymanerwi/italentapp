package com.exdev.italent.obj;

public class WorkDetailsObj extends Obj {
	private String image;

	private String link;
	
	private String notes;
	
	private WorkObj work;
	
	public WorkDetailsObj() {
		
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public WorkObj getWork() {
		return work;
	}

	public void setWork(WorkObj work) {
		this.work = work;
	}
	
	
}