package com.bionic.edu.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bionic.edu.model.Article;
import com.bionic.edu.service.ArticleService;

//http://docs.spring.io/spring/docs/current/spring-framework-reference/html/validation.html#validation-mvc-configuring
@Component
public class ArticleFormValidator implements Validator {
	
	@Autowired
	ArticleService articleService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Article.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Article article = (Article) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "articleName", "NotEmpty.articleForm.articleName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "articleUrl", "NotEmpty.articleForm.articleUrl");
	}

}