package com.yabo.cms.controller;

import com.yabo.cms.config.handler.CacheHandler;
import com.yabo.cms.entity.Association;
import com.yabo.cms.entity.dto.AssociationAddDto;
import com.yabo.cms.entity.response.R;
import com.yabo.cms.service.AssociationService;
import jakarta.annotation.Resource;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/association")
public class AssociationController {

    @Resource
    private AssociationService associationService;

    @Resource
    private CacheHandler cacheHandler;

    @Cacheable(cacheNames = "associationList", key = "'associationList'")
    @GetMapping("/list")
    public Object list() {
        return associationService.associationList();
    }

//    @Cacheable(cacheNames = "allAvailableCategory", key = "'allAvailableCategory'")
    @GetMapping("/allAvailableCategory")
    public Object allAvailableCategory() {
        return associationService.allAvailableCategory();
    }

    @GetMapping("/allAvailableArticle/{categoryId}")
    public Object allAvailableArticle(@PathVariable String categoryId) {
        return associationService.allAvailableArticle(categoryId);
    }

    @Cacheable(cacheNames = "allConnectedArticle", key = "'allConnectedArticle' + #categoryId")
    @GetMapping("/allConnectedArticle/{categoryId}")
    public Object allConnectedArticle(@PathVariable String categoryId) {
        return associationService.allConnectedArticle(categoryId);
    }


    @Transactional
    @PostMapping("/add")
    public Object add(@RequestBody AssociationAddDto associationAddDto) {
//        return associationService.save(associationAddDto) ? R.success("添加成功！") : R.error(500, "添加失败！");
        boolean result = associationService.save(associationAddDto);
        if (result) {
            // 清除缓存
            cacheHandler.diyCacheCleaner("associationList");
            cacheHandler.diyCacheCleaner("allConnectedArticle");
            cacheHandler.diyCacheCleaner("allAvailableCategory");
            return R.success("添加成功！");
        } else {
            return R.error(500, "添加失败！");
        }
    }

    @Transactional
    @PutMapping("/update")
    public Object update(@RequestBody Association association) {
//        return associationService.update(associationDto) ? R.success("修改成功！")
//            : R.error(500, "修改失败！");
        boolean result = associationService.updateById(association);
        if (result) {
            // 清除缓存
            cacheHandler.diyCacheCleaner("associationList");
            cacheHandler.diyCacheCleaner("allConnectedArticle");
            cacheHandler.diyCacheCleaner("allAvailableCategory");
            return R.success("修改成功！");
        } else {
            return R.error(500, "修改失败！");
        }
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    public Object delete(@PathVariable String id) {
        Integer idInt = Integer.valueOf(id);
//        return associationService.delete(association) ? R.success("删除成功！") : R.error(500, "删除失败！");
        boolean result = associationService.removeById(idInt);
        if (result) {
            // 清除缓存
            cacheHandler.diyCacheCleaner("associationList");
            cacheHandler.diyCacheCleaner("allConnectedArticle");
            cacheHandler.diyCacheCleaner("allAvailableCategory");
            return R.success("删除成功！");
        } else {
            return R.error(500, "删除失败！");
        }
    }


}
