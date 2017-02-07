package com.bionic.edu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.bionic.edu.model.Article;

@Repository
public class ArticleDaoImpl implements ArticleDao {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Article findById(Integer id) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("article_id", id);

        String sql = "SELECT * FROM articles WHERE article_id=:article_id";

        Article result = null;
        try {
            result = namedParameterJdbcTemplate.queryForObject(sql, params, new ArticleMapper());
        } catch (EmptyResultDataAccessException e) {
            // do nothing, return null
        }

        return result;

    }

    @Override
    public List<Article> findAll() {

        String sql = "SELECT * FROM articles";
        List<Article> result = namedParameterJdbcTemplate.query(sql, new ArticleMapper());

        return result;

    }

    @Override
    public void save(Article article) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO articles(ARTICLE_NAME,ARTICLE_URL) "
                + "VALUES ( :article_name, :article_url)";

        namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(article), keyHolder);
        article.setArticleId(keyHolder.getKey().intValue());

    }

    @Override
    public void update(Article article) {

        String sql = "UPDATE articles SET ARTICLE_NAME=:article_name, ARTICLE_URL=:article_url "
                + "WHERE article_id=:article_id";

        namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(article));

    }

    @Override
    public void delete(Integer id) {

        String sql = "DELETE FROM articles WHERE article_id= :article_id";
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("article_id", id));

    }

    private SqlParameterSource getSqlParameterByModel(Article article) {

        // Unable to handle List<String> or Array
        // BeanPropertySqlParameterSource
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("article_id", article.getArticleId());
        paramSource.addValue("article_name", article.getArticleName());
        paramSource.addValue("article_url", article.getArticleUrl());

        return paramSource;
    }

    private static final class ArticleMapper implements RowMapper<Article> {

        public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
            Article article = new Article();
            article.setArticleId(rs.getInt("article_id"));
            article.setArticleName(rs.getString("article_name"));
            article.setArticleUrl(rs.getString("article_url"));

            return article;
        }
    }

}
