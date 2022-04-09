package com.city.explorer.dto;

import java.sql.Date;

public class NewsDTO {

	private Long id;
	private String headline;
	private String detailDescription;
	private String introDescription;
	private String imageName;
	private Date date;
	
	
	
	

	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
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
	
	
}
