package com.yabo.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yabo.cms.entity.CompanyInfo;


/**
 * (CompanyInfo)表服务接口
 *
 * @author makejava
 * @since 2024-01-07 19:37:20
 */
public interface CompanyInfoService extends IService<CompanyInfo> {

    Object insert(CompanyInfo companyInfo);
}

