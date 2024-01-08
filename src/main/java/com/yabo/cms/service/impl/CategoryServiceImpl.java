package com.yabo.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yabo.cms.entity.Category;
import com.yabo.cms.mapper.CategoryMapper;
import com.yabo.cms.service.CategoryService;
import org.springframework.stereotype.Service;

/**
 * (Category)表服务实现类
 *
 * @author makejava
 * @since 2024-01-07 19:37:19
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}

