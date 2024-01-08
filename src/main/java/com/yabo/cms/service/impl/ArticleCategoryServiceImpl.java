package com.yabo.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yabo.cms.entity.ArticleCategory;
import com.yabo.cms.entity.dto.ArticleCategoryDto;
import com.yabo.cms.mapper.ArticleCategoryMapper;
import com.yabo.cms.service.ArticleCategoryService;
import jakarta.annotation.Resource;
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

    @Resource
    private ArticleCategoryMapper articleCategoryMapper;

    @Override
    public boolean update(ArticleCategoryDto articleCategoryDto) {
        // this dto include three fields: articleId, oldCategoryId, and newCategoryName
        // we need to update the article_category table
        // first, we need to get the article_category object by articleId and oldCategoryId
        // then, we need to update the article_category object's categoryId to newCategoryId
        // finally, we need to update the article_category object
        LambdaUpdateWrapper<ArticleCategory> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(ArticleCategory::getArticleId, articleCategoryDto.getArticleId())
            .eq(ArticleCategory::getCategoryId, articleCategoryDto.getOldCategoryId())
            .set(ArticleCategory::getCategoryId, articleCategoryDto.getNewCategoryId());
        // the above code will generate under sql statement:
        // update article_category set category_id = newCategoryId where article_id = articleId and category_id = oldCategoryId
        int count = articleCategoryMapper.update(null, lambdaUpdateWrapper);

        return count > 0;

    }

    @Override
    public boolean delete(ArticleCategory articleCategory) {
        LambdaQueryWrapper<ArticleCategory> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ArticleCategory::getArticleId, articleCategory.getArticleId())
            .eq(ArticleCategory::getCategoryId, articleCategory.getCategoryId());

        int count = articleCategoryMapper.delete(lambdaQueryWrapper);

        return count > 0;
    }


}

