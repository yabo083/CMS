package com.yabo.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yabo.cms.entity.Association;


/**
 * (Association)表数据库访问层
 *
 * @author makejava
 * @since 2024-01-07 19:37:19
 */
public interface AssociationMapper extends BaseMapper<Association> {


    int deleteByArticleId(Integer idInt);

    int deleteByCategoryId(Integer idInt);
}

