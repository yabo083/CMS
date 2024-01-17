package com.yabo.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yabo.cms.entity.Article;
import com.yabo.cms.entity.Association;
import com.yabo.cms.entity.Category;
import com.yabo.cms.entity.dto.*;
import com.yabo.cms.entity.response.R;
import com.yabo.cms.mapper.ArticleMapper;
import com.yabo.cms.mapper.AssociationMapper;
import com.yabo.cms.mapper.CategoryMapper;
import com.yabo.cms.service.ArticleService;
import com.yabo.cms.service.AssociationService;
import com.yabo.cms.service.CategoryService;
import jakarta.annotation.Resource;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * (Association)表服务实现类
 *
 * @author makejava
 * @since 2024-01-07 19:37:19
 */
@Service("AssociationService")
public class AssociationServiceImpl extends ServiceImpl<AssociationMapper, Association> implements
    AssociationService {

    @Resource
    private AssociationMapper associationMapper;

    @Resource
    private ArticleService articleService;

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private CategoryService categoryService;

    @Resource
    private CategoryMapper categoryMapper;

//    @Override
//    public boolean update(AssociationDto associationDto) {
//        LambdaUpdateWrapper<Association> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
//        lambdaUpdateWrapper.eq(Association::getArticleId, associationDto.getArticleId())
//            .eq(Association::getCategoryId, associationDto.getOldCategoryId())
//            .set(Association::getCategoryId, associationDto.getNewCategoryId());
//        // the above code will generate under sql statement:
//        // update article_category set category_id = newCategoryId where article_id = articleId and category_id = oldCategoryId
//        int count = associationMapper.update(null, lambdaUpdateWrapper);
//
//        return count > 0;
//
//    }

    @Override
    public Object associationList() {
        // first, by list(), get all the associations
        List<Association> association = list();

        // Get all articles and categories
        List<Article> allArticles = articleService.list();
        List<Category> allCategories = categoryService.list();

        // Convert to map for quick lookup
        Map<Integer, Article> articleMap = allArticles.stream()
            .collect(Collectors.toMap(Article::getArticleId, Function.identity()));
        Map<Integer, Category> categoryMap = allCategories.stream()
            .collect(Collectors.toMap(Category::getCategoryId, Function.identity()));

        // second, by stream(), map(), collect() and builder(), get the associationListDto
        return association.stream().map(association1 -> {
                Article article = articleMap.get(association1.getArticleId());
                Category category = categoryMap.get(association1.getCategoryId());
                // Check if the article or category has been logically deleted
                if (article == null || category == null) {
                    return null;
                }
                return AssociationListDto.builder()
                    .id(association1.getId())
                    .articleDto(ArticleDto.builder()
                        .articleId(association1.getArticleId())
                        .title(article.getTitle())
                        .build())
                    .categoryDto(CategoryDto.builder()
                        .categoryId(association1.getCategoryId())
                        .categoryName(category.getCategoryName())
                        .build())
                    .build();
            })
            // Filter out null values
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    @Override
    public Object allAvailableCategory() {
        List<Category> categoryList = categoryService.list();

        if (categoryList != null && !categoryList.isEmpty()) {
            return categoryList.stream().map(category -> CategoryDto.builder()
                    .categoryId(category.getCategoryId())
                    .categoryName(category.getCategoryName())
                    .build())
                .collect(Collectors.toList());
        }

        return R.error(500, "没有可用的栏目！");
    }

    @Override
    public Object allAvailableArticle(String categoryId) {
        // first, get all the associations by categoryId
        LambdaQueryWrapper<Association> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Association::getCategoryId, categoryId);
        List<Association> associationList = associationMapper.selectList(lambdaQueryWrapper);
        // second, get all the articles by articleService.list()
        List<Article> articleList = articleService.list();
        // third, remove the articles which are already in the associationList
        // concretely, by articleId, remain the articles which are not in the associationList
        List<Article> articleList1 = articleList.stream().filter(article -> {
            for (Association association : associationList) {
                if (association.getArticleId().equals(article.getArticleId())) {
                    return false;
                }
            }
            return true;
        }).toList();
        // finally, convert the articleList1 to articleDtoList
        return articleList1.stream().map(article -> ArticleDto.builder()
                .articleId(article.getArticleId())
                .title(article.getTitle())
                .build())
            .collect(Collectors.toList());
    }

    @Override
    public boolean save(AssociationAddDto associationAddDto) {
        // first, check which attribute is only by length in associationAddDto
        List<ArticleDto> articleDtoList = associationAddDto.getSelectedArticleDtoList();
        List<CategoryDto> categoryDtoList = associationAddDto.getSelectedCategoryDtoList();
        List<Association> associationList;
        if (articleDtoList.size() == 1) {
            // second, batch save the associations<articleId, categoryId>
            // by stream(), map(), collect() and builder()
            associationList = categoryDtoList.stream().map(categoryDto -> Association.builder()
                    .articleId(articleDtoList.get(0).getArticleId())
                    .categoryId(categoryDto.getCategoryId())
                    .isDeleted(0)
                    .build())
                .toList();
        } else {
            // third, batch save the associations<articleId, categoryId>
            // by stream(), map(), collect() and builder()
            associationList = articleDtoList.stream().map(articleDto -> Association.builder()
                    .articleId(articleDto.getArticleId())
                    .categoryId(categoryDtoList.get(0).getCategoryId())
                    .isDeleted(0)
                    .build())
                .toList();
        }

        // Filter out the existing associations
        List<Association> newAssociations = associationList.stream()
            .filter(association -> {
                LambdaQueryWrapper<Association> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(Association::getArticleId, association.getArticleId())
                    .eq(Association::getCategoryId, association.getCategoryId());
                return associationMapper.selectOne(queryWrapper) == null;
            })
            .collect(Collectors.toList());
        System.out.println(newAssociations);

        // Save the new associations
        return ((AssociationService) AopContext.currentProxy()).saveBatch(newAssociations);
    }

    @Override
    public Object allConnectedArticle(String categoryId) {
        // first, get all the associations by categoryId
        LambdaQueryWrapper<Association> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Association::getCategoryId, categoryId);
        List<Association> associationList = associationMapper.selectList(lambdaQueryWrapper);
        // second, get all the articles by these associations' articleId
        List<Article> articleList = associationList.stream()
            .map(association -> articleMapper.selectById(association.getArticleId())).toList();
        // finally, convert the articleList to articleContentDto
        return articleList.stream().map(article -> ArticleContentDto.builder()
                .articleId(article.getArticleId())
                .title(article.getTitle())
                .content(article.getContent())
                .author(article.getAuthor())
                .build())
            .collect(Collectors.toList());
    }

}

