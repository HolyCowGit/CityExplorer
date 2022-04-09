package com.city.explorer.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.city.explorer.global.GlobalData;
import com.city.explorer.model.Role;
import com.city.explorer.model.User;
import com.city.explorer.repository.RoleRepository;
import com.city.explorer.repository.UserRepository;

@Controller
public class LoginController {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;

	@GetMapping("/login")
	public String login() {
//		GlobalData.report.clear();
		return "login";
	}


	@GetMapping("/oauth2/authorization/google")
	public String google() {
//		GlobalData.report.clear();
		return "/";
	}
	
//	@PostMapping("/login")
//	public String login() {
//		return "login";
//	}
	@GetMapping("/register")
	public String registerGet() {
		return "register";
	}
	
	@PostMapping("/register")
	public String registerPost(@ModelAttribute("user") User user, HttpServletRequest request) throws ServletException {
	String password = user.getPassword();
	user.setPassword(bCryptPasswordEncoder.encode(password));
	List<Role> roles = new ArrayList<>();
	roles.add(roleRepository.findById(2).get());
	user.setRoles(roles);
	userRepository.save(user);
	request.login(user.getEmail(), password);
	return "redirect:/";
	}
	
	@GetMapping("/sellerRegister")
	public String sellerRegisterGet() {
		return "sellerRegister";
	}
	
	@PostMapping("/sellerRegister")
	public String sellerRegisterPost(@ModelAttribute("user") User user, HttpServletRequest request) throws ServletException {
	String password = user.getPassword();
	user.setPassword(bCryptPasswordEncoder.encode(password));
	List<Role> roles = new ArrayList<>();
	roles.add(roleRepository.findById(3).get());
	user.setRoles(roles);
	userRepository.save(user);
	request.login(user.getEmail(), password);
	return "redirect:/";
	}
	
}
