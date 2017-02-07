package com.bionic.edu.web;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bionic.edu.model.Article;
import com.bionic.edu.service.ArticleService;
import com.bionic.edu.validator.ArticleFormValidator;
//import javax.validation.Valid;

//http://www.tikalk.com/redirectattributes-new-feature-spring-mvc-31/
//https://en.wikipedia.org/wiki/Post/Redirect/Get
//http://www.oschina.net/translate/spring-mvc-flash-attribute-example
@Controller
public class ArticleController {

    private final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    ArticleFormValidator articleFormValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(articleFormValidator);
    }

    private ArticleService articleService;

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        logger.debug("index()");
        return "redirect:/articles";
    }

    // list page
    @RequestMapping(value = "/articles", method = RequestMethod.GET)
    public String showAllArticles(Model model) {

        logger.debug("showAllArticles()");
        model.addAttribute("articles", articleService.findAll());
        return "articles/list";

    }

    // save or update article
    @RequestMapping(value = "/articles", method = RequestMethod.POST)
    public String saveOrUpdateArticle(@ModelAttribute("articleForm") @Validated Article article,
            BindingResult result, Model model, final RedirectAttributes redirectAttributes) {

        logger.debug("saveOrUpdateArticle() : {}", article);

        if (result.hasErrors()) {
            return "articles/articleform";
        } else {

            redirectAttributes.addFlashAttribute("css", "success");
            if (article.isNew()) {
                redirectAttributes.addFlashAttribute("msg", "Article added successfully!");
            } else {
                redirectAttributes.addFlashAttribute("msg", "Article updated successfully!");
            }

            articleService.saveOrUpdate(article);

            // POST/REDIRECT/GET
            return "redirect:/articles/" + article.getArticleId();

            // POST/FORWARD/GET
            // return "article/list";
        }

    }

    // show add article form
    @RequestMapping(value = "/articles/add", method = RequestMethod.GET)
    public String showAddArticleForm(Model model) {

        logger.debug("showAddArticleForm()");

        Article article = new Article();

        model.addAttribute("articleForm", article);

        return "articles/articleform";
    }

    // show update form
    @RequestMapping(value = "/articles/{id}/update", method = RequestMethod.GET)
    public String showUpdateArticleForm(@PathVariable("id") int id, Model model) {

        logger.debug("showUpdateArticleForm() : {}", id);

        Article article = articleService.findById(id);
        model.addAttribute("articleForm", article);

        return "articles/articleform";

    }

    // delete article
    @RequestMapping(value = "/articles/{id}/delete", method = RequestMethod.POST)
    public String deleteArticle(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {

        logger.debug("deleteArticle() : {}", id);

        articleService.delete(id);

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Article is deleted!");

        return "redirect:/articles";

    }

    // show article
    @RequestMapping(value = "/articles/{id}", method = RequestMethod.GET)
    public String showArticle(@PathVariable("id") int id, Model model) {

        logger.debug("showArticle() id: {}", id);

        Article article = articleService.findById(id);
        if (article == null) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "Article not found");
        }
        model.addAttribute("article", article);

        return "articles/show";

    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ModelAndView handleEmptyData(HttpServletRequest req, Exception ex) {

        logger.debug("handleEmptyData()");
        logger.error("Request: {}, error ", req.getRequestURL(), ex);

        ModelAndView model = new ModelAndView();
        model.setViewName("article/show");
        model.addObject("msg", "article not found");

        return model;

    }

}
