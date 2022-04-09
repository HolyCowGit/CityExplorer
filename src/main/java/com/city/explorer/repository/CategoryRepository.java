package com.city.explorer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.city.explorer.model.Category;
import com.city.explorer.model.City;
import com.city.explorer.model.Product;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
//Product p=new Product();
//	@Modifying
//	@Query(value= "update category set city_id=(select city_id from product where category_id=category.category_id)where category_id= 6"
//			,nativeQuery=true)
	
//	@Modifying
//	@Query(value= "update Category c set c.city_id= 5 where c.category_id=4"
//			,nativeQuery=true)
//	 
//	public void updateCat();
	//List<Category>findAllByCity_Id(@Param("id") int id);
	List<Category>findAllByCity_Id( int id);
	
	
	
}
