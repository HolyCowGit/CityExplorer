package com.city.explorer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.city.explorer.model.Category;
import com.city.explorer.model.City;
import com.city.explorer.model.Product;
import com.city.explorer.repository.CategoryRepository;
import com.city.explorer.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	

	public List<Product> getAllProduct() {
		return productRepository.findAll();
	}

	public void addProduct(Product product) {
		productRepository.save(product);
	}

	public void removeProductById(long id) { //long
		productRepository.deleteById(id);
	}

	public Optional<Product> getProductById(long id) { //long
		return productRepository.findById(id);
	}

	public List<Product> getALLProductsByCityId(int id) {
		return productRepository.findAllByCity_Id(id);
	}

	public List<Product> getALLProductsByCategoryId(int id) {
		return productRepository.findAllByCategory_Id(id);
	}
	
	public List<Product> getALLProductsByCategoryIdOrCityId(int id1 , int id2) {
		return productRepository.findAllByCategory_IdOrCity_Id(id1, id2);
	}

	//
//	public List<Category> getALLCategoryIdByProductId(int id) {
//		return productRepository.findAllCategory_IdBycity_Id(id);
//	}
//	
//	public List<City> getALLCityIdByProductId(int id) {
//		return productRepository.findAllCity_IdByproduct_Id(id);
//	}
	//

}
