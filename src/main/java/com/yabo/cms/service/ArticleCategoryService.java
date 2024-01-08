package com.yabo.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yabo.cms.entity.ArticleCategory;
import com.yabo.cms.entity.dto.ArticleCategoryDto;


/**
 * (ArticleCategory)表服务接口
 *
 * @author makejava
 * @since 2024-01-07 19:37:19
 */
public interface ArticleCategoryService extends IService<ArticleCategory> {

    boolean update(ArticleCategoryDto articleCategoryDto);

    boolean delete(ArticleCategory articleCategory);
}

