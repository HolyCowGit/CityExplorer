package com.city.explorer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.city.explorer.global.GlobalData;
import com.city.explorer.model.Product;
import com.city.explorer.service.ProductService;

@Controller
public class ReportController {

	@Autowired
	ProductService productService;
	
	@GetMapping ("/addToReport/{id}")
	public String addToReport(@PathVariable int id ){
	GlobalData.report.add(productService.getProductById(id).get());
	return "redirect:/shop";
	}
	
	
	@GetMapping ("/report")
	public String reportGet(Model model) {
	model.addAttribute( "reportCount",GlobalData.report.size());
	//model.addAttribute ( "total", GlobalData.report.stream().mapToDouble(Product::getPrice).sum());
	model.addAttribute ("report", GlobalData.report);
	return "report";
	}
	
	 @GetMapping("/report/removeItem/{index}")
		public String reportItemRemove(@PathVariable int index) {
			GlobalData.report.remove(index);
			return "redirect:/report";	
	 }
			
			@GetMapping("/checkout")
			public String checkout(Model model) {
		//	model.addAttribute("total", GlobalData.report.stream().mapToDouble(Product::getPrice).sum());
			return "checkout";
					
			}
	
			@GetMapping("/report/viewproduct/{id}")
			public String viewProduct(Model model, @PathVariable int id){
				model.addAttribute("product", productService.getProductById(id).get());
			 
			return "viewProduct";
			}
	
			@GetMapping("/report/delete/{id}")
			public String deleteProduct(@PathVariable long id) {   //long
			productService.removeProductById(id);
			
			return "redirect:/report" ;
			}
	
	
}
