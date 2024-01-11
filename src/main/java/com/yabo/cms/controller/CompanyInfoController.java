package com.yabo.cms.controller;

import com.yabo.cms.entity.CompanyInfo;
import com.yabo.cms.entity.response.R;
import com.yabo.cms.service.CompanyInfoService;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companyInfo")
public class CompanyInfoController {

    @Resource
    private CompanyInfoService companyInfoService;

    /**
     * 获取公司信息
     * @return 现有的公司信息
     */
    @GetMapping("/list")
    public Object getCompanyInfo() {
        return companyInfoService.list();
    }

    /**
     * 修改公司信息
     * @return ok
     */
    @Transactional
    @PutMapping("/update")
    public Object updateCompanyInfo(@RequestBody CompanyInfo companyInfo) {
        return companyInfoService.updateById(companyInfo) ? R.success("修改成功！") : R.error(500, "修改失败！");
    }

    /**
     * 删除公司信息
     * @return
     */
    @Transactional
    @DeleteMapping("/delete/{id}")
    public Object deleteCompanyInfo(@PathVariable String id) {
        Integer idInt = Integer.valueOf(id);
        return companyInfoService.removeById(idInt) ? R.success("删除成功！") : R.error(500, "删除失败！");
    }

    /**
     * 添加公司信息
     * @return ok
     */
    @Transactional
    @PostMapping("/add")
    public Object addCompanyInfo(@RequestBody CompanyInfo companyInfo) {
        return companyInfoService.insert(companyInfo);
    }


}
