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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/news/list")
    public String newsList(Model model) {
        List<Article> articles = articleService.findNewsArticles();
        model.addAttribute("articles", articles);
        return "article/news-list";
    }

    @GetMapping("/board/list")
    public String storyList(Model model) {
        List<Article> articles = articleService.findStoryArticles();
        model.addAttribute("articles", articles);
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

    @PostMapping(value = {"/news/write"})
    public String createNews(@Valid ArticleForm articleForm, BindingResult result) {

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

    @PostMapping(value = {"/board/write"})
    public String createStory(@Valid ArticleForm articleForm, BindingResult result) {

        if (result.hasErrors()) {
            return "article/board-write";
        }

        Article article = new Article();
        article.setCategory(articleForm.getCategory());
        article.setTitle(articleForm.getTitle());
        article.setContent(articleForm.getContent());
        article.setName(articleForm.getName());
        article.setPassword(articleForm.getPassword());

        articleService.saveArticle(article);

        return "redirect:/board/list";
    }

    @GetMapping("/news/{articleId}")
    public String newsViewForm(@PathVariable("articleId")Long articleId, Model model){

        Article article = articleService.findOne(articleId);

        ArticleForm articleForm = new ArticleForm();
        articleForm.setId(article.getId());
        articleForm.setCategory(article.getCategory());
        articleForm.setTitle(article.getTitle());
        articleForm.setContent(article.getContent());
        articleForm.setName(article.getName());
        articleForm.setPassword(article.getPassword());

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 a h시 m분");
        String createDate = article.getCreatedDate().format(dateTimeFormatter);
        String modifiedDate = article.getModifiedDate().format(dateTimeFormatter);

        articleForm.setCreatedDate(createDate);
        articleForm.setModifiedDate(modifiedDate);

        if (article.getCount() == 0) {
            article.setCount(1);
        }
        articleForm.setCount(article.getCount());
        articleService.countPlus(article.getId(), article.getCount() + 1);

        model.addAttribute("articleForm", articleForm);
        return "article/news-view";
    }

    @GetMapping("/board/{articleId}")
    public String storyViewForm(@PathVariable("articleId")Long articleId, Model model){

        Article article = articleService.findOne(articleId);

        ArticleForm articleForm = new ArticleForm();
        articleForm.setId(article.getId());
        articleForm.setCategory(article.getCategory());
        articleForm.setTitle(article.getTitle());
        articleForm.setContent(article.getContent());
        articleForm.setName(article.getName());
        articleForm.setPassword(article.getPassword());

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 a h시 m분");
        String createDate = article.getCreatedDate().format(dateTimeFormatter);
        String modifiedDate = article.getModifiedDate().format(dateTimeFormatter);

        articleForm.setCreatedDate(createDate);
        articleForm.setModifiedDate(modifiedDate);

        if (article.getCount() == 0) {
            article.setCount(1);
        }
        articleForm.setCount(article.getCount());
        articleService.countPlus(article.getId(), article.getCount() + 1);

        model.addAttribute("articleForm", articleForm);
        return "article/board-view";
    }

    @PostMapping("/news/{articleId}/cancel")
    public String deleteNews(@PathVariable("articleId")Long articleId) {
        articleService.delete(articleId);
        return "redirect:/news/list";
    }

    @PostMapping("/board/{articleId}/cancel")
    public String deleteBoard(@PathVariable("articleId")Long articleId) {
        articleService.delete(articleId);
        return "redirect:/board/list";
    }

    /**
     * 뉴스 수정 폼
     */
    @GetMapping("/news-edit/{articleId}")
    public String updateNewsForm(@PathVariable("articleId") Long articleId, Model model){
        Article article = articleService.findOne(articleId);

        ArticleForm articleForm = new ArticleForm();
        articleForm.setId(article.getId());
        articleForm.setCategory(article.getCategory());
        articleForm.setTitle(article.getTitle());
        articleForm.setContent(article.getContent());
        articleForm.setName(article.getName());

        model.addAttribute("articleId", articleId);
        model.addAttribute("articleForm", articleForm);
        return "article/news-edit";
    }

    /**
     * 이야기 수정 폼
     */
    @GetMapping("/board-edit/{articleId}")
    public String updateBoardForm(@PathVariable("articleId") Long articleId, Model model){
        Article article = articleService.findOne(articleId);

        ArticleForm articleForm = new ArticleForm();
        articleForm.setId(article.getId());
        articleForm.setCategory(article.getCategory());
        articleForm.setTitle(article.getTitle());
        articleForm.setContent(article.getContent());
        articleForm.setName(article.getName());

        model.addAttribute("articleId", articleId);
        model.addAttribute("articleForm", articleForm);
        return "article/board-edit";
    }

    /**
     * 뉴스 수정
     */
    @PostMapping("/news-edit/{articleId}")
    public String updateNews(@PathVariable("articleId") Long articleId, ArticleForm form){

        articleService.updateArticle(articleId, form.getName(), form.getContent(), form.getTitle(), form.getCategory());

        return "redirect:/news/list";
    }

    /**
     * 이야기 수정
     */
    @PostMapping("/board-edit/{articleId}")
    public String updateBoard(@PathVariable("articleId") Long articleId, ArticleForm form){

        articleService.updateArticle(articleId, form.getName(), form.getContent(), form.getTitle(), form.getCategory());

        return "redirect:/board/list";
    }


}

