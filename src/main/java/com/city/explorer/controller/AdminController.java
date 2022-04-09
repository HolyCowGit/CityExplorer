package com.city.explorer.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.city.explorer.dto.NewsDTO;
import com.city.explorer.dto.ProductDTO;
import com.city.explorer.global.GlobalData;
import com.city.explorer.model.Category;
import com.city.explorer.model.City;
import com.city.explorer.model.News;
import com.city.explorer.model.Product;
import com.city.explorer.service.CategoryService;
import com.city.explorer.service.CityService;
import com.city.explorer.service.CustomUserDetailService;
import com.city.explorer.service.NewsServices;
import com.city.explorer.service.ProductService;



@Controller
public class AdminController {
	
	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages"; //java lang system class

//	    To upload images to live site:
//		File path = new  File(ResourceUtils.getURL("classpath:static/productImages").getPath()).getAbsoluteFile();
//		String   uploadDir = path.getAbsolutePath();
	@Autowired
	CityService cityService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	CustomUserDetailService customUserDetailService;
	
	@Autowired
	NewsServices newsService;

	
	@GetMapping("/admin")
	public String adminHome(Model model) {
		model.addAttribute( "reportCount",GlobalData.report.size());
		return "adminHome";
	}

	@GetMapping("/admin/cities")
	public String getCit(Model model) {
		model.addAttribute("cities", cityService.getAllCity());
		return "cities";
	}

	@GetMapping("/admin/cities/add")
	public String getCitAdd(Model model) {
		model.addAttribute("city", new City());
		return "citiesAdd";
	}

	@PostMapping("/admin/cities/add")
	public String postCitAdd(@ModelAttribute("city") City city) {

		cityService.addCity(city);
		return "redirect:/admin/cities";
	}

	@GetMapping("/admin/cities/delete/{id}")
	public String deleteCit(@PathVariable int id) {
		cityService.removeCityById(id);
		return "redirect:/admin/cities";
	}

	@GetMapping("/admin/cities/update/{id}")
	public String updateCit(@PathVariable int id, Model model) {
		Optional<City> city = cityService.getCityById(id);
		if (city.isPresent()) {
			model.addAttribute("city", city.get());
			return "citiesAdd";
		} else
			return "404";
	}

	//category
	
	
	@GetMapping("/admin/categories")
	public String getCat(Model model) {
		model.addAttribute("categories", categoryService.getAllCategory());
		return "categories";
	}

	@GetMapping("/admin/categories/add")
	public String getCatAdd(Model model) {
		model.addAttribute("category", new Category());
		return "categoriesAdd";
	}

	@PostMapping("/admin/categories/add")
	public String postCatAdd(@ModelAttribute("category") Category category) {

		categoryService.addCategory(category);
		return "redirect:/admin/categories";
	}

	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCat(@PathVariable int id) {
		categoryService.removeCategoryById(id);
		return "redirect:/admin/categories";
	}

	@GetMapping("/admin/categories/update/{id}")
	public String updateCat(@PathVariable int id, Model model) {
		Optional<Category> category = categoryService.getCategoryById(id);
		if (category.isPresent()) {
			model.addAttribute("category", category.get());
			return "categoriesAdd";
		} else
			return "404";
	}
	
	
	
	//Product
	
	@GetMapping("/admin/products")
	public String products(Model model){
	model.addAttribute("products", productService.getAllProduct());
	return "products";
	}
	
	@GetMapping("/admin/products/add")
	public String productAddGet(Model model) {
	model.addAttribute("productDTO", new ProductDTO());
	model.addAttribute ("cities", cityService.getAllCity());
	model.addAttribute ("categories", categoryService.getAllCategory());
	return "productsAdd" ;
	}
	
	
	@PostMapping("/admin/products/add")
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

	return "redirect:/admin/products" ;

	}
	
	
	@GetMapping("/admin/product/delete/{id}")
	public String deleteProduct(@PathVariable long id) {   //long
	productService.removeProductById(id);
	return "redirect:/admin/products" ;
	}
	
	@GetMapping("/admin/product/update/{id}")
	public String updateProductGet(@PathVariable long id, Model model) {  //long
	Product product = productService.getProductById(id).get();
	ProductDTO productDTO = new ProductDTO();
	productDTO.setId(product.getId());
	productDTO.setName(product.getName()) ;
	productDTO.setCityId((product.getCity().getId())) ;
	productDTO.setAddress(product.getAddress()) ;
	productDTO.setDetailDescription(product.getDetailDescription());
	productDTO.setIntroDescription(product.getIntroDescription());
	productDTO.setContactNumber(product.getContactNumber());
	productDTO.setEmail(product.getEmail());
//	productDTO.setPrice(product.getPrice());
//	productDTO.setWeight((product.getWeight()));
//	productDTO.setDescription(product.getDescription());
	model.addAttribute("categories", categoryService.getAllCategory());
	productDTO.setImageName(product.getImageName()) ;
	model.addAttribute("cities", cityService.getAllCity());
	model.addAttribute("productDTO", productDTO);
	
	return "productsAdd";
	}
	
	
	//sellers controller
	
	@GetMapping("/admin/sellers")
	public String getSell(Model model) {
		model.addAttribute("sellers", customUserDetailService.getSellers());
		return "sellers";
	}
	
	
	@GetMapping("/admin/sellers/delete/{id}")
	public String deleteSell(@PathVariable int id) {
		customUserDetailService.removeSellById(id);
		return "redirect:/admin/sellers";
	}
	
	
	
	//start news
	
	
	@GetMapping("/admin/news")
	public String news(Model model){
	model.addAttribute("news", newsService.getAllNews());
	return "news";
	}
	
	@GetMapping("/admin/news/add")
	public String newsAddGet(Model model) {
	model.addAttribute("newsDTO", new NewsDTO());
	
	return "newsAdd" ;
	}
	
	
	@PostMapping("/admin/news/add")
	public String newsAddPost(@ModelAttribute("newsDTO")NewsDTO newsDTO,
	                                      @RequestParam("productImage")MultipartFile file,
	                                    @RequestParam("imgName")String imgName) throws IOException {
	News news = new News();  //we can use mapping tech to merge both obj until there parameteres match 
	news.setId(newsDTO.getId());
	news.setHeadline(newsDTO.getHeadline());
	
	news.setDetailDescription(newsDTO.getDetailDescription());
	news.setIntroDescription(newsDTO.getIntroDescription());
    news.setDate(newsDTO.getDate());
	
	
	String imageUUID;
	if(!file.isEmpty()) {
	imageUUID = file.getOriginalFilename() ;
	Path fileNameAndPath = Paths.get(uploadDir, imageUUID) ;
	Files.write(fileNameAndPath, file.getBytes());
	}else 
	{
	imageUUID = imgName;	
	}
	
	news.setImageName(imageUUID);
	newsService.addNews(news);

	return "redirect:/admin/news" ;

	}
	
	
	@GetMapping("/admin/news/delete/{id}")
	public String deleteNews(@PathVariable long id) {   //long
		newsService.removeNewsById(id);
	return "redirect:/admin/news" ;
	}
	
	/*
	 * @GetMapping("/admin/product/update/{id}") public String
	 * updateProductGet(@PathVariable long id, Model model) { //long Product product
	 * = productService.getProductById(id).get(); ProductDTO productDTO = new
	 * ProductDTO(); productDTO.setId(product.getId());
	 * productDTO.setName(product.getName()) ;
	 * productDTO.setCityId((product.getCity().getId())) ;
	 * productDTO.setAddress(product.getAddress()) ;
	 * productDTO.setDetailDescription(product.getDetailDescription());
	 * productDTO.setIntroDescription(product.getIntroDescription());
	 * productDTO.setContactNumber(product.getContactNumber());
	 * productDTO.setEmail(product.getEmail()); //
	 * productDTO.setPrice(product.getPrice()); //
	 * productDTO.setWeight((product.getWeight())); //
	 * productDTO.setDescription(product.getDescription());
	 * model.addAttribute("categories", categoryService.getAllCategory());
	 * productDTO.setImageName(product.getImageName()) ;
	 * model.addAttribute("cities", cityService.getAllCity());
	 * model.addAttribute("productDTO", productDTO);
	 * 
	 * return "productsAdd"; }
	 */
	
	
	//end news
	
	
}
