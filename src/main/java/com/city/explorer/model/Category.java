package com.city.explorer.model;


import javax.persistence.*;

import org.springframework.web.bind.annotation.Mapping;

@Entity

public class Category{
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column
(name = "category_id" )
private int id;

//inverseJoinColumns = @JoinColumn(name = "city_id", referencedColumnName = "city_id")
//@JoinTable(name = "product",
//joinColumns = @JoinColumn(name = "city_iid", referencedColumnName = "city_iid"),
//inverseJoinColumns = @JoinColumn(name = "city_id", referencedColumnName = "city_id")
//)

@ManyToOne(fetch = FetchType.LAZY )
@JoinColumn(name = "city_id", referencedColumnName = "city_id")
private City city;



public City getCity() {
	return city;
}
public void setCity(City city) {
	this.city = city;
}
private String name;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
@Override
public String toString() {
	return "Category [id=" + id + ", city=" + city + ", name=" + name + "]";
}



}

