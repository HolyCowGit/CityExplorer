package com.city.explorer.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class News {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String headline;
	
	private Date date;
	private String detailDescription;
	private String introDescription;
	private String imageName;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getHeadline() {
		return headline;
	}
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDetailDescription() {
		return detailDescription;
	}
	public void setDetailDescription(String detailDescription) {
		this.detailDescription = detailDescription;
	}
	public String getIntroDescription() {
		return introDescription;
	}
	public void setIntroDescription(String introDescription) {
		this.introDescription = introDescription;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	@Override
	public String toString() {
		return "news [id=" + id + ", headline=" + headline + ", date=" + date + ", detailDescription="
				+ detailDescription + ", introDescription=" + introDescription + ", imageName=" + imageName + "]";
	}
	
	
	
}
