package com.city.explorer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.city.explorer.model.News;
import com.city.explorer.model.Product;
import com.city.explorer.model.News;
import com.city.explorer.repository.CategoryRepository;
import com.city.explorer.repository.NewsRepository;

@Service
public class NewsServices {
	
	@Autowired
	NewsRepository newsRepository;
	

	public List<News> getAllNews() {
		return newsRepository.findAll();
	}

	
	public void addNews(News news) {
		newsRepository.save(news);
	}

	public void removeNewsById(long id) { //long
		newsRepository.deleteById(id);
	}

	public Optional<News> getNewsById(long id) { //long
		return newsRepository.findById(id);
	}

	public List<News>getRecentNews(){
		return newsRepository.getRecentNews();
	}
	
	public List<News>getRecentNews3(){
		return newsRepository.getRecentNews3();
	}

}
