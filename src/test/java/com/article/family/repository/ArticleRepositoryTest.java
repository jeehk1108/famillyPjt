package com.article.family.repository;

import com.article.family.domain.Article;
import com.article.family.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import javax.persistence.EntityManager;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
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

    @Test
    public void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.of(2019, 12, 6, 0, 0, 0);
        articleRepository.save(new Article());

        //when
        List<Article> aritcles = articleRepository.findNewsAll();

        //then
        Article article = aritcles.get(0);

        System.out.println(">>>>> createDate = "+article.getCreatedDate() + ", modifiedDate = "+article.getModifiedDate());

        assertThat(article.getCreatedDate()).isAfter(now);
        assertThat(article.getModifiedDate()).isAfter(now);
    }




}