package com.bionic.edu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bionic.edu.dao.ArticleDao;
import com.bionic.edu.model.Article;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    ArticleDao articleDao;

    @Autowired
    public void setUserDao(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    @Override
    public Article findById(Integer id) {
        return articleDao.findById(id);
    }

    @Override
    public List<Article> findAll() {
        return articleDao.findAll();
    }

    @Override
    public void saveOrUpdate(Article article) {

        if (findById(article.getArticleId()) == null) {
            articleDao.save(article);
        } else {
            articleDao.update(article);
        }

    }

    @Override
    public void delete(int id) {
        articleDao.delete(id);
    }

}
