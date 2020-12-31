package com.article.family.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Article extends BaseTimeEntity {

    @Id
    @GeneratedValue // @GeneratedValue(strategy = GenerationType.AUTO)와 같다
    @Column(name = "article_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ArticleCategory category;

    private String name;
    private String title;
    private String content;
    private int count;
    private String password;

}
