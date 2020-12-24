package com.article.familly.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Article {

    @Id
    @GeneratedValue
    @Column(name = "article_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ArticleCategory category;

    private String name;
    private String title;
    private String content;
    private int count;
    private String password;
    private LocalDateTime registDate;

}
