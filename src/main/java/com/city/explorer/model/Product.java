package com.city.explorer.model;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;

import com.city.explorer.*;


@Entity

public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	@ManyToOne(fetch = FetchType.LAZY )
	//@ManyToOne( cascade=Cascade.ALL , mappedBy="category")
	@JoinColumn(name = "city_id", referencedColumnName = "city_id")
	private City city;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", referencedColumnName = "category_id")
	private Category category;

	
	private long contactNumber;
	private String email;
	private String address;
	private String detailDescription;
	private String introDescription;
	private String imageName;
	
	
	
	
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
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public long getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
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
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", city=" + city + ", category=" + category + ", contactNumber="
				+ contactNumber + ", email=" + email + ", address=" + address + ", detailDescription="
				+ detailDescription + ", introDescription=" + introDescription + ", imageName=" + imageName + "]";
	}
	
	
	
	
	

}