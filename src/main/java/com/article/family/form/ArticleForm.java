package com.article.family.form;

import com.article.family.domain.ArticleCategory;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class ArticleForm {

    private Long id;

//    @NotEmpty(message = "카테고리를 하나 선택해 주세요.")
    private ArticleCategory category;

    @NotEmpty(message = "작성자 이름은 필수 입니다.")
    private String name;

    @NotEmpty(message = "제목을 입력해 주세요.")
    private String title;

    @NotEmpty(message = "내용을 입력해 주세요.")
    private String content;

    private int count;

    @Size(min=3, max=10, message = "3자이상 10자미만으로 설정해주세요.")
    private String password;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;
}
