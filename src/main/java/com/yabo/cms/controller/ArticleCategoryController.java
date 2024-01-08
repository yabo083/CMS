package com.yabo.cms.controller;

import com.yabo.cms.entity.ArticleCategory;
import com.yabo.cms.entity.dto.ArticleCategoryDto;
import com.yabo.cms.entity.response.R;
import com.yabo.cms.service.ArticleCategoryService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articleCategory")
public class ArticleCategoryController {

    @Resource
    private ArticleCategoryService articleCategoryService;

    @GetMapping("/list")
    public Object list() {
        return articleCategoryService.list();
    }

    @PostMapping("/add")
    public Object add(@RequestBody ArticleCategory articleCategory) {
        return articleCategoryService.save(articleCategory) ? R.success("添加成功！") : R.error(500, "添加失败！");
    }

    @PutMapping("/update")
    public Object update(@RequestBody ArticleCategoryDto articleCategoryDto) {
        return articleCategoryService.update(articleCategoryDto) ? R.success("修改成功！")
            : R.error(500, "修改失败！");
    }

    @DeleteMapping("/delete")
    public Object delete(@RequestBody ArticleCategory articleCategory) {
        return articleCategoryService.delete(articleCategory) ? R.success("删除成功！") : R.error(500, "删除失败！");
    }


}
