package com.article.family.controller;

import com.article.family.domain.Article;
import com.article.family.form.ArticleForm;
import com.article.family.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/news/list")
    public String newsList(Model model) {
        List<Article> articles = articleService.findArticles();
        model.addAttribute("articles", articles);
        return "article/news-list";
    }

    @GetMapping("/board/list")
    public String storyList() {
        return "article/board-list";
    }

    @GetMapping("/news/write")
    public String newsCreateForm(Model model) {
        model.addAttribute("articleForm", new ArticleForm());
        return "article/news-write";
    }

    @GetMapping("/board/write")
    public String storyCreateForm(Model model) {
        model.addAttribute("articleForm", new ArticleForm());
        return "article/board-write";
    }

    @PostMapping(value = {"/news/write" , "/baord/write"})
    public String create(@Valid ArticleForm articleForm, BindingResult result) {

        if (result.hasErrors()) {
            return "article/news-write";
        }

        Article article = new Article();
        article.setCategory(articleForm.getCategory());
        article.setTitle(articleForm.getTitle());
        article.setContent(articleForm.getContent());
        article.setName(articleForm.getName());
        article.setPassword(articleForm.getPassword());

        articleService.saveArticle(article);

        return "redirect:/news/list";
    }

    @GetMapping("/news/view/{articleId}")
    public String newsViewForm(@PathVariable("articleId")Long articleId, Model model){

        Article article = articleService.findOne(articleId);

        ArticleForm articleForm = new ArticleForm();
        articleForm.setCategory(article.getCategory());
        articleForm.setTitle(article.getTitle());
        articleForm.setContent(article.getContent());
        articleForm.setName(article.getName());
        articleForm.setPassword(article.getPassword());
        articleForm.setCreatedDate(article.getCreatedDate());
        articleForm.setModifiedDate(article.getModifiedDate());

        if (article.getCount() == 0) {
            article.setCount(1);
        }
        articleForm.setCount(article.getCount());
        articleService.countPlus(article.getId(), article.getCount() + 1);

        model.addAttribute("articleForm", articleForm);
        return "article/news-view";
    }

}

