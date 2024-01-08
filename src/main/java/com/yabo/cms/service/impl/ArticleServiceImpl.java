package com.yabo.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yabo.cms.entity.Article;
import com.yabo.cms.mapper.ArticleMapper;
import com.yabo.cms.service.ArticleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

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

    @Override
    public Object getArticleList(int pageNum, int pageSize) {
        System.out.println("pageNum = " + pageNum);
        System.out.println("pageSize = " + pageSize);
        Page<Article> page = new Page<>(pageNum, pageSize);
        page(page);
        return page.getRecords();

    }
}

