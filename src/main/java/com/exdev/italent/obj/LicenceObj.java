package com.exdev.italent.obj;

public class LicenceObj extends Obj {

	
	private String image;

	private String notes;
	
	private OwnerObj owner;
	
	public LicenceObj() {
	
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public OwnerObj getOwner() {
		return owner;
	}

	public void setOwner(OwnerObj owner) {
		this.owner = owner;
	}
	
	
}
