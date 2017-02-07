package com.bionic.edu.dao;

import java.util.List;

import com.bionic.edu.model.Article;

public interface ArticleDao {

	Article findById(Integer id);

	List<Article> findAll();

	void save(Article article);

	void update(Article article);

	void delete(Integer id);

}