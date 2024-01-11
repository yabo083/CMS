package com.yabo.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yabo.cms.entity.Association;
import com.yabo.cms.entity.dto.AssociationAddDto;


/**
 * (Association)表服务接口
 *
 * @author makejava
 * @since 2024-01-07 19:37:19
 */
public interface AssociationService extends IService<Association> {

//    boolean update(AssociationDto associationDto);

    Object associationList();

    Object allAvailableCategory();

    Object allAvailableArticle(String categoryId);

    boolean save(AssociationAddDto associationAddDto);
}

