package com.city.explorer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.city.explorer.model.News;
import com.city.explorer.model.User;



public interface NewsRepository  extends JpaRepository<News, Long>  {

//Optional<User> findUserByEmail(String email);
//	
//	@Query(value="select * from users where id IN (select user_id from user_role where role_id=3)" ,nativeQuery=true)
//	List<User> getAllSellers();
//	
	
	@Query(value=" SELECT * FROM news ORDER BY id DESC limit 5",nativeQuery=true)
	List<News>getRecentNews();
	
	@Query(value=" SELECT * FROM news ORDER BY id DESC limit 3",nativeQuery=true)
	List<News>getRecentNews3();
	
}
