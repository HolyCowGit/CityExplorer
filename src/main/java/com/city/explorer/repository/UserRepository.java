package com.city.explorer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.city.explorer.model.Category;
import com.city.explorer.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

//	@Query(value= "update category set city_id=(select city_id from product where product.category_id=category.category_id)where category_id= :id")
//	List<Category>findAllByCity_Id(@Param("id") int id);
	
	Optional<User> findUserByEmail(String email);
	
	@Query(value="select * from users where id IN (select user_id from user_role where role_id=3)" ,nativeQuery=true)
	List<User> getAllSellers();
	
	@Query(value="select first_name from users where id=?1)" ,nativeQuery=true)
	List<User> getName(int id);
	
	
}