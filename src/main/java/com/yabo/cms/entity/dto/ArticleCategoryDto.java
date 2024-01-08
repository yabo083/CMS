package com.yabo.cms.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleCategoryDto {

    private Integer articleId;

    private Integer oldCategoryId;

    private Integer newCategoryId;

}
