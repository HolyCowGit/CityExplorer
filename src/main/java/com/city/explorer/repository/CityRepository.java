package com.city.explorer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.city.explorer.model.City;

public interface CityRepository extends JpaRepository<City, Integer>{

	
	
}
