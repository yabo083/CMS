package com.yabo.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yabo.cms.entity.CompanyInfo;
import com.yabo.cms.mapper.CompanyInfoMapper;
import com.yabo.cms.service.CompanyInfoService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * (CompanyInfo)表服务实现类
 *
 * @author makejava
 * @since 2024-01-07 19:37:20
 */
@Service("companyInfoService")
public class CompanyInfoServiceImpl extends ServiceImpl<CompanyInfoMapper, CompanyInfo> implements CompanyInfoService {

    @Resource
    private CompanyInfoMapper companyInfoMapper;

    @Override
    public Object insert(CompanyInfo companyInfo) {
        QueryWrapper<CompanyInfo> queryWrapper = new QueryWrapper<>();
        int count = Math.toIntExact(companyInfoMapper.selectCount(queryWrapper));
        if (count > 0) {
            throw new RuntimeException("公司信息已经存在，不能再插入");
        }
        companyInfoMapper.insert(companyInfo);
        return "公司信息插入成功";
    }

}

