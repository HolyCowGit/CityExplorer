package com.city.explorer.model;


import javax.persistence.*;



@Entity

public class City{
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column
(name = "city_id" )
private int id;

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



}

