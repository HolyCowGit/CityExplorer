package com.city.explorer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.city.explorer.model.Category;
import com.city.explorer.model.City;
import com.city.explorer.model.Product;

public interface ProductRepository  extends JpaRepository<Product, Long>  { //Long
	//@Query(value= "update category set city_id=(select city_id from product where category_id=category.category_id)where category_id= 6")
    
	
//	@Query(value= "select city_id from product where id=:id")
//	List<Product> findpId();
	
//	@Modifying
//	@Query(value= "update category set city_id=(select city_id from product where category_id=category.category_id)where category_id= 12"
//			,nativeQuery=true)
//	List<Product> getCitId(int id);
	
	List<Product> findAllByCity_Id(int id);
	List<Product> findAllByCategory_Id(int id);
	
	List<Product> findAllByCategory_IdOrCity_Id(int id1 , int id2);	
	//**
//	List<Category> findAllCategory_IdBycity_Id(int id);
//	List<City> findAllCity_IdByproduct_Id(int id);
	//**
}
