package com.yabo.cms.controller;

import com.yabo.cms.config.handler.CacheHandler;
import com.yabo.cms.entity.Article;
import com.yabo.cms.entity.response.R;
import com.yabo.cms.service.ArticleService;
import jakarta.annotation.Resource;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @Resource
    private CacheHandler cacheHandler;


    /**
     * 获取完整内容的文章列表, 每页10行
     *
     * @return 现有的文章列表
     */
    @Cacheable(cacheNames = "articleList", key = "'articleList'.concat(#pageNum).concat('-').concat(#pageSize)")
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Object getArticleList(@PathVariable int pageNum,
        @PathVariable int pageSize) {
        // 实现分页查询，默认每页10行，且从第1页开始
        return articleService.getArticleList(pageNum, pageSize);
    }

    @Transactional
    @PostMapping("/add")
    public Object addArticle(@RequestBody Article article) {
        boolean result = articleService.save(article);
        if (result) {
            // 清除缓存
            cacheHandler.diyCacheCleaner("articleList");
            cacheHandler.diyCacheCleaner("associationList");
            return R.success("添加成功！");
        } else {
            return R.error(500, "添加失败！");
        }
    }

    @Transactional
    @PutMapping("/update")
    public Object updateArticle(@RequestBody Article article) {
        boolean result = articleService.updateById(article);
        if (result) {
            // 清除缓存
            cacheHandler.diyCacheCleaner("articleList");
            cacheHandler.diyCacheCleaner("associationList");
            return R.success("修改成功！");
        } else {
            return R.error(500, "修改失败！");
        }
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    public Object deleteArticle(@PathVariable String id) {
        Integer idInt = Integer.valueOf(id);
        boolean result = articleService.deleteById(idInt);
        if (result) {
            // 清除缓存
            cacheHandler.diyCacheCleaner("articleList");
            cacheHandler.diyCacheCleaner("associationList");
            return R.success("删除成功！");
        } else {
            return R.error(500, "删除失败！");
        }
    }


}
