package com.yabo.cms.entity.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssociationAddDto {

    private List<ArticleDto> selectedArticleDtoList;

    private List<CategoryDto> selectedCategoryDtoList;
}
