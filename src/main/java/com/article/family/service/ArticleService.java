package com.article.family.service;

import com.article.family.domain.Article;
import com.article.family.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleService {

    private final ArticleRepository articleRepository;


    /**
     * 게시판 글쓰기
     */
    @Transactional
    public Long saveArticle(Article article) {
        articleRepository.save(article);
        return article.getId();
    }

    /**
     * 게시글 전체조회
     */
    public List<Article> findArticles() {
        return articleRepository.findAll();
    }

    /**
     * 단건 조회
     */
    public Article findOne(Long articleId) {
        return articleRepository.findOne(articleId);
    }

    /**
     * 조회수 증가
     */
    @Transactional
    public void countPlus(Long articleId, int count) {
        Article article = articleRepository.findOne(articleId);
        article.setCount(count);
    }

}
