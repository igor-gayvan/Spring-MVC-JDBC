/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.edu.model;

/**
 *
 * @author Igor Gayvan
 */
public class Article {

    private Integer articleId;
    private String articleUrl;
    private String articleName;

    public Article() {
    }

    public Article(Integer articleId) {
        this.articleId = articleId;
    }

    public Article(Integer articleId, String articleUrl, String articleName) {
        this.articleId = articleId;
        this.articleUrl = articleUrl;
        this.articleName = articleName;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (articleId != null ? articleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Article)) {
            return false;
        }
        Article other = (Article) object;
        if ((this.articleId == null && other.articleId != null) || (this.articleId != null && !this.articleId.equals(other.articleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bionic.edu.model.Article[ articleId=" + articleId + " ]";
    }

    public boolean isNew() {
        return (this.articleId == null);
    }
}
