package com.yabo.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yabo.cms.entity.Article;


/**
 * (Article)表服务接口
 *
 * @author makejava
 * @since 2024-01-07 19:37:19
 */
public interface ArticleService extends IService<Article> {

    Object getArticleList(int pageNum, int pageSize);

    boolean deleteById(Integer idInt);
}

