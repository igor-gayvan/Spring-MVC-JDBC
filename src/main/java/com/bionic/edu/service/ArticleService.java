package com.bionic.edu.service;

import java.util.List;

import com.bionic.edu.model.Article;

public interface ArticleService {

	Article findById(Integer id);
	
	List<Article> findAll();

	void saveOrUpdate(Article article);
	
	void delete(int id);
	
}