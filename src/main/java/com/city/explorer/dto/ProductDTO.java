package com.city.explorer.dto;



public class ProductDTO {

	private Long id;
	private String name;
	private int cityId;
	private int categoryId;
	private long contactNumber;
	private String email;
	
	private String address;
	private String detailDescription;
	private String introDescription;
	private String imageName;
	
	
	
	
	public long getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int categoryId) {
		this.cityId = categoryId;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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