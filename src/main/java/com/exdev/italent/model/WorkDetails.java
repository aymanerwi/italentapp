package com.exdev.italent.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the work_details database table.
 * 
 */
@Entity
@Table(name="work_details")
@NamedQuery(name="WorkDetails.findAll", query="SELECT w FROM WorkDetails w")
public class WorkDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private byte[] image;

	private String link;
	
	private String notes;

	//bi-directional many-to-one association to Work
	@ManyToOne
	private Work work;

	public WorkDetails() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getImage() {
		return this.image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Work getWork() {
		return this.work;
	}

	public void setWork(Work work) {
		this.work = work;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

}