package com.yabo.cms.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yabo.cms.entity.Article;
import com.yabo.cms.entity.dto.ArticlePageDto;
import com.yabo.cms.mapper.ArticleMapper;
import com.yabo.cms.mapper.AssociationMapper;
import com.yabo.cms.service.ArticleService;
import com.yabo.cms.service.AssociationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Article)表服务实现类
 *
 * @author makejava
 * @since 2024-01-07 19:37:19
 */
@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private AssociationMapper associationMapper;

    @Override
    public Object getArticleList(int pageNum, int pageSize) {
        // get current page data
        Page<Article> page = new Page<>(pageNum, pageSize);
        page(page);
        List<Article> articleList = page.getRecords();
        // get total count from page object
        long total = page.getTotal();

        // Create ArticlePageDto object and set articleList and total

        // Return the ArticlePageDto object
        return ArticlePageDto.builder()
            .articleList(articleList)
            .total((int) total)
            .build();
    }

    @Override
    public boolean deleteById(Integer idInt) {
        // delete article by id and association by article id
        int count = articleMapper.deleteById(idInt);
        int count1 = associationMapper.deleteByArticleId(idInt);
        return count > 0 && count1 > 0;
    }

}

