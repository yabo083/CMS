package com.yabo.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yabo.cms.entity.Category;
import com.yabo.cms.mapper.AssociationMapper;
import com.yabo.cms.mapper.CategoryMapper;
import com.yabo.cms.service.AssociationService;
import com.yabo.cms.service.CategoryService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * (Category)表服务实现类
 *
 * @author makejava
 * @since 2024-01-07 19:37:19
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private AssociationMapper associationMapper;


    @Override
    public boolean deleteById(Integer idInt) {
        // delete category by id and association by category id
        int count = categoryMapper.deleteById(idInt);
        int count1 = associationMapper.deleteByCategoryId(idInt);
        return count > 0 && count1 > 0;
    }
}

