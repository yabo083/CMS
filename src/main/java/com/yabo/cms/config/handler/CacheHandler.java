package com.yabo.cms.config.handler;

import jakarta.annotation.Resource;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CacheHandler {

    @Resource
    private CacheManager cacheManager;


    /**
     * 清除指定名称的缓存
     *
     * @return
     */
    public void diyCacheCleaner(String cacheName) {
        // first, print all cache(cache's structure is very deep and please ensure to delete clearly),and then delete all cache
//         System.out.println(cacheManager.getCacheNames());
//         cacheManager.getCacheNames().forEach(cacheNames -> cacheManager.getCache(cacheNames).clear());
        // second, delete the specified cache

        if (Objects.nonNull(cacheManager.getCache(cacheName))) {
            cacheManager.getCache(cacheName).clear();
        }
    }

}
