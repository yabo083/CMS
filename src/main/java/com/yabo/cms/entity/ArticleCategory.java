package com.yabo.cms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (ArticleCategory)表实体类
 *
 * @author makejava
 * @since 2024-01-07 19:37:19
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("article_category")
public class ArticleCategory {


    private Integer articleId;

    private Integer categoryId;

}

