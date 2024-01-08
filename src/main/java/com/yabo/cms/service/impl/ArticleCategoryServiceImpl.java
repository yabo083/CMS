package com.yabo.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yabo.cms.entity.ArticleCategory;
import com.yabo.cms.mapper.ArticleCategoryMapper;
import com.yabo.cms.service.ArticleCategoryService;
import org.springframework.stereotype.Service;

/**
 * (ArticleCategory)表服务实现类
 *
 * @author makejava
 * @since 2024-01-07 19:37:19
 */
@Service("articleCategoryService")
public class ArticleCategoryServiceImpl extends ServiceImpl<ArticleCategoryMapper, ArticleCategory> implements
    ArticleCategoryService {

}

