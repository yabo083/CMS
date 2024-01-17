package com.yabo.cms.controller;

import com.yabo.cms.config.handler.CacheHandler;
import com.yabo.cms.entity.Category;
import com.yabo.cms.entity.response.R;
import com.yabo.cms.service.CategoryService;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @Resource
    private CacheHandler cacheHandler;

    @GetMapping("/list")
    public Object list() {
        return categoryService.list();
    }

    @Transactional
    @PostMapping("/add")
    public Object add(@RequestBody Category category) {
//        return categoryService.save(category) ? R.success("添加成功！") : R.error(500, "添加失败！");
        boolean result = categoryService.save(category);
        if (result) {
            // 清除缓存
            cacheHandler.diyCacheCleaner("associationList");
            return R.success("删除成功！");
        } else {
            return R.error(500, "删除失败！");
        }
    }

    @Transactional
    @PutMapping("/update")
    public Object update(@RequestBody Category category) {
//        return categoryService.updateById(category) ? R.success("修改成功！") : R.error(500, "修改失败！");
        boolean result = categoryService.updateById(category);
        if (result) {
            // 清除缓存
            cacheHandler.diyCacheCleaner("associationList");
            return R.success("删除成功！");
        } else {
            return R.error(500, "删除失败！");
        }
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    public Object delete(@PathVariable String id) {
        Integer idInt = Integer.valueOf(id);
//        return categoryService.removeById(idInt) ? R.success("删除成功！") : R.error(500, "删除失败！");
        boolean result = categoryService.deleteById(idInt);
        if (result) {
            // 清除缓存
            cacheHandler.diyCacheCleaner("associationList");
            return R.success("删除成功！");
        } else {
            return R.error(500, "删除失败！");
        }
    }

}
