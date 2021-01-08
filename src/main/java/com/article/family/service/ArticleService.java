package com.article.family.service;

import com.article.family.domain.Article;
import com.article.family.domain.ArticleCategory;
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
     * 뉴스 게시글 전체조회
     */
    public List<Article> findNewsArticles() {
        return articleRepository.findNewsAll();
    }

    /**
     * 뉴스 게시글 전체조회
     */
    public List<Article> findStoryArticles() {
        return articleRepository.findStoryAll();
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

    /**
     * 게시글삭제
     */
    @Transactional
    public void delete(Long articleId) {
//        articleRepository.delete(articleId);
        Article article = articleRepository.findOne(articleId);
        article.setCategory(null);
    }

    /**
     * 게시글 수정
     */
    @Transactional
    public void updateArticle(Long articleId, String name, String content, String title, ArticleCategory category) {
        Article article = articleRepository.findOne(articleId);
        article.setContent(content);
        article.setName(name);
        article.setCategory(category);
        article.setTitle(title);
    }

}
