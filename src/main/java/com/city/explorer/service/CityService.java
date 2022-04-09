package com.city.explorer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.city.explorer.model.City;
import com.city.explorer.repository.CityRepository;

@Service
public class CityService {
	@Autowired
	CityRepository cityRepository;

	public List<City> getAllCity() {
		return cityRepository.findAll();
	}

	public void addCity(City City) {
		cityRepository.save(City);
	}
	
	public void removeCityById(int id) {
		cityRepository.deleteById(id);
	}
	
	public Optional<City> getCityById(int id) {
		return cityRepository.findById(id);
		}
	
}