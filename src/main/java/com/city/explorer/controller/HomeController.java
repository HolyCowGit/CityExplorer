package com.city.explorer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.city.explorer.global.GlobalData;
import com.city.explorer.model.Category;
import com.city.explorer.model.City;
import com.city.explorer.model.Product;
import com.city.explorer.service.CategoryService;
import com.city.explorer.service.CityService;
import com.city.explorer.service.CustomUserDetailService;
import com.city.explorer.service.NewsServices;
import com.city.explorer.service.ProductService;

@Controller
public class HomeController {
	@Autowired
	CityService cityService;
	@Autowired
	ProductService productService;
	
	@Autowired
	NewsServices newsService;
	
	@Autowired
	CustomUserDetailService  customUserDetailService;
	
	
	Product pd;
	
	@Autowired
	CategoryService categoryService;

	@GetMapping({ "/", "/home " })
	public String home(Model model /* ,@PathVariable int id */ ) {
		model.addAttribute( "reportCount",GlobalData.report.size());
		//model.addAttribute("name", customUserDetailService.findUser(id));
		model.addAttribute("news", newsService.getRecentNews3());
		
		return "index";
	}

	@GetMapping("/shop")
	public String shop(Model model ) {
		model.addAttribute("cities", cityService.getAllCity());
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("products", productService.getAllProduct());
		model.addAttribute( "reportCount",GlobalData.report.size());
		return "shop";
	}
	
	@GetMapping("/shop/city/{id}")
	public String shopByCategory(Model model, @PathVariable int id) {
	model.addAttribute("cities", cityService.getAllCity());
	//model.addAttribute("categories", categoryService.getALLCategoriesByCityId(id));
	model.addAttribute( "reportCount",GlobalData.report.size());
	model.addAttribute ("products", productService.getALLProductsByCityId(id));
	return "shop";
	}
	
	//category
	
	@GetMapping("/shop/category/{id}")
	public String shopByCity(Model model, @PathVariable int id) {
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute( "reportCount",GlobalData.report.size());
//	model.addAttribute("categories", categoryService.getCategoryById(id).get());
	model.addAttribute ("products", productService.getALLProductsByCategoryId(id));
	return "shop";
	}
	
	
	
	//city to category
	
//	@GetMapping("/shop/city/{id}")
//	public String CategoryByCity(Model model, @PathVariable int id ) {
//	
//	model.addAttribute("cities", cityService.getCityById(id));
//	
//	//model.addAttribute ("categories", categoryService.getALLCategoriesByCityId(id));
//	model.addAttribute ("products", productService.getALLProductsByCityId(id));
//	
//	return "shop";
//	}
//	
//	@GetMapping("/shop/category/{id}")
//	public String ProductByCategory(Model model, @PathVariable int id ) {
//	
//	
//	model.addAttribute ("categories", categoryService.getCategoryById(id));
//	model.addAttribute ("products", productService.getALLProductsByCategoryId(id));
//	
//	return "shop";
//	}
	
//	@PutMapping("/shop")
//	public String cat(Model model , @PathVariable int id) {
//		model.getAttribute("product");
//		model.
//		return "shop";
//	}
	
	
	
	@GetMapping("/shop/viewproduct/{id}")
	public String viewProduct(Model model, @PathVariable int id){
		model.addAttribute("product", productService.getProductById(id).get());
	 
	return "viewProduct";
	}
	

	/*
	 * @GetMapping("/newsAll") public String viewNews(Model model){
	 * model.addAttribute(model);
	 * 
	 * 
	 * return "newsAll"; }
	 */
	 
	  @GetMapping("/newsAll")
		public String newsAll(Model model) {
			
			model.addAttribute("news", newsService.getAllNews());
			
			return "newsAll";
		}
	
		@GetMapping("/newsAll/viewnews/{id}")
		public String viewNews(Model model, @PathVariable int id){
			model.addAttribute("news", newsService.getNewsById(id).get());
			model.addAttribute("news1", newsService.getRecentNews());
		 
		return "viewNews";
		}
	
//		 @GetMapping("/newsAll/viewnews/recentPost")
//			public String recentPost(Model model) {
//				
//				model.addAttribute("news", newsService.getAllNews());
//				
//				return "redirect:/newsAll/viewnews/recentPost";
//			}
//		
		
		//about
		
		@GetMapping("/about")
		public String about(Model model) {
			
			model.addAttribute(model);
			
			return "about";
		}
		
		@GetMapping("/contact")
		public String contact(Model model) {
			
			model.addAttribute(model);
			
			return "contact";
		}
		
		//
		/*
		 * @GetMapping("/") public String getSinglrUser(Model model,@PathVariable int
		 * id) {
		 * 
		 * model.addAttribute("user", customUserDetailService.findUser(id));
		 * 
		 * return "/"; }
		 */
		
}
