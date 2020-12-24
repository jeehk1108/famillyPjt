package com.article.familly.service;

import com.article.familly.domain.Article;
import com.article.familly.repository.ArticleRepository;
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

}
