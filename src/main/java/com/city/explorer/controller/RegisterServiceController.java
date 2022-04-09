package com.city.explorer.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.city.explorer.dto.ProductDTO;
import com.city.explorer.model.Product;
import com.city.explorer.service.CategoryService;
import com.city.explorer.service.CityService;
import com.city.explorer.service.ProductService;


@Controller
public class RegisterServiceController {

	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";
	
	@Autowired
	CityService cityService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;

	@GetMapping("/registerService")
	public String regServ(Model model){
		model.addAttribute("productDTO", new ProductDTO());
		model.addAttribute ("cities", cityService.getAllCity());
		model.addAttribute ("categories", categoryService.getAllCategory());
	return "registerService";
	}
	
	@PostMapping("/registerService/add")
	public String productAddPost(@ModelAttribute("productDTO")ProductDTO productDTO,
	                                      @RequestParam("productImage")MultipartFile file,
	                                    @RequestParam("imgName")String imgName) throws IOException {
	Product product = new Product();  //we can use mapping tech to merge both obj until there parameteres match 
	product.setId(productDTO.getId());
	product.setName(productDTO.getName());
	product.setCity(cityService.getCityById(productDTO.getCityId()).get());
	product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
	product.setAddress(productDTO.getAddress());
	product.setDetailDescription(productDTO.getDetailDescription());
	product.setIntroDescription(productDTO.getIntroDescription());
	product.setContactNumber(productDTO.getContactNumber());
	product.setEmail(productDTO.getEmail());
//	product.setPrice(productDTO.getPrice());
//	product.setWeight(productDTO.getWeight());
//	product.setDescription(productDTO.getDescription());
	//
//	Category category = categoryService.getCategoryById(productDTO.getCategoryId()).get();
//	category.setCity(cityService.getCityById(productDTO.getCityId()).get());
//	categoryService.addCategory(category);
	//
	
	
	String imageUUID;
	if(!file.isEmpty()) {
	imageUUID = file.getOriginalFilename() ;
	Path fileNameAndPath = Paths.get(uploadDir, imageUUID) ;
	Files.write(fileNameAndPath, file.getBytes());
	}else 
	{
	imageUUID = imgName;	
	}
	
	product.setImageName(imageUUID);
	productService.addProduct(product);

	return "redirect:/shop" ;

	}
	
}
