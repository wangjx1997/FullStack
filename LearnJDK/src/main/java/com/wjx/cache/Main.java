package com.wjx.cache;

import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @Author WangJX
 * @Date 2023/7/11 15:07
 * @Description
 */
public class Main {
    LoadingCache<String, String> loadingCache = Caffeine.newBuilder()
            //创建缓存或者最近一次更新缓存后经过指定时间间隔，刷新缓存；refreshAfterWrite仅支持LoadingCache
            .refreshAfterWrite(10, TimeUnit.SECONDS)
            .expireAfterWrite(10, TimeUnit.SECONDS)
            .expireAfterAccess(10, TimeUnit.SECONDS)
            .maximumSize(10)
            //根据key查询数据库里面的值，这里是个lamba表达式
            .build(key -> new Date().toString());

    static Cache<Object, Object> cache = Caffeine.newBuilder()
            //初始数量
            .initialCapacity(10)
            //最大条数
            .maximumSize(10)
            //expireAfterWrite和expireAfterAccess同时存在时，以expireAfterWrite为准
            //最后一次写操作后经过指定时间过期
            .expireAfterWrite(1, TimeUnit.SECONDS)
            //最后一次读或写操作后经过指定时间过期
            .expireAfterAccess(1, TimeUnit.SECONDS)
            //监听缓存被移除
            .removalListener((key, val, removalCause) -> { })
            //记录命中
            .recordStats()
            .build();


    public static void main(String[] args) {


        cache.put("1","张三");
        //张三
        System.out.println(cache.getIfPresent("1"));
        //存储的是默认值
        System.out.println(cache.get("2",o -> "默认值"));



        AsyncLoadingCache<String, String> asyncLoadingCache = Caffeine.newBuilder()
                //创建缓存或者最近一次更新缓存后经过指定时间间隔刷新缓存；仅支持LoadingCache
                .refreshAfterWrite(1, TimeUnit.SECONDS)
                .expireAfterWrite(1, TimeUnit.SECONDS)
                .expireAfterAccess(1, TimeUnit.SECONDS)
                .maximumSize(10)
                //根据key查询数据库里面的值
                .buildAsync(key -> {
                    Thread.sleep(1000);
                    return new Date().toString();
                });

//异步缓存返回的是CompletableFuture
        CompletableFuture<String> future = asyncLoadingCache.get("1");

        future.thenAccept(System.out::println);
    }
}
