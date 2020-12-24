package com.article.familly.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    @GetMapping("/news")
    public String articleNews() {
        return "article/news-list";
    }

    @GetMapping("/story")
    public String articleStory() {
        return "article/story-list";
    }
}

