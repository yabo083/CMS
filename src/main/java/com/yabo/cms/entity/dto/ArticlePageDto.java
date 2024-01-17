package com.yabo.cms.entity.dto;

import com.yabo.cms.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticlePageDto {

    private Integer total;

    private List<Article> articleList;

}
