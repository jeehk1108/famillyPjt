package com.article.familly.repository;

import com.article.familly.domain.Article;
import com.article.familly.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ArticleRepositoryTest {

    @Autowired
    ArticleService articleService;

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    EntityManager em;

    @Test()
    @Rollback(value = false)
    public void 게시글작성() throws Exception {
        //given
        Article article = new Article();
        article.setName("지현근");
        article.setTitle("결혼식 일자 정보를 공유 합니다.");

        //when
        Long savedId = articleService.saveArticle(article);

        //then
        assertEquals(article, articleRepository.findOne(savedId));
    }




}