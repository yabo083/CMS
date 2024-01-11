package com.yabo.cms.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssociationListDto {

    private Integer id;

    private ArticleDto articleDto;

    private CategoryDto categoryDto;
}
