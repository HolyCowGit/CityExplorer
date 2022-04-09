package com.city.explorer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.city.explorer.model.CustomUserDetail;
import com.city.explorer.model.User;
import com.city.explorer.repository.UserRepository;


@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findUserByEmail(email);
		user.orElseThrow( () -> new UsernameNotFoundException ("User Not Found !! ")) ;
		return user.map(CustomUserDetail::new).get();
	}

	 public List<User> getSellers(){
		 return userRepository.getAllSellers();
	 }
	 
	 public List<User> getSell(){
		 return userRepository.findAll();
	 }
	 
	 public void removeSellById(int id) {
			userRepository.deleteById(id);
		}
	 
	 public List<User> findUser(int id) {
		return userRepository.getName(id);
	 }
}
