package com.wjx.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.google.common.collect.Table;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @Author WangJX
 * @Date 2022/4/20 18:39
 * @Description
 */
public class GuavaTest {
    public static void main(String[] args) throws ExecutionException {
        multiMapOrSetTest();

    }

    // 要记录员工每个月工作的天数
    public static void twoMapTest() {
        // 普通map实现

        Map<String, Map<String, Integer>> map = new HashMap<>();
        //存放元素
        Map<String, Integer> workMap = new HashMap<>();
        workMap.put("Jan", 20);
        workMap.put("Feb", 28);
        map.put("wjx", workMap);

        //取出元素
        Integer dayCount = map.get("wjx").get("Jan");

        System.out.println(dayCount);

    }

    public static void tableTest() {
        Table<String,String,Integer> table= HashBasedTable.create();
        //存放元素
        table.put("Hydra", "Jan", 20);
        table.put("Hydra", "Feb", 28);

        table.put("Trunks", "Jan", 28);
        table.put("Trunks", "Feb", 16);

        //取出元素
        Integer dayCount2 = table.get("Hydra", "Feb");
        System.out.println(dayCount2);
    }


    // Multimap 和 MultiSet
    public static void multiMapOrSetTest() {
        // Multimap: key-value  key可以重复，value也可重复
        // Map<K, Collection<V>> map
        Multimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("key1", "11");
        multimap.put("key2", "12");
        multimap.put("key1", "11");
        multimap.put("key2", "13");
        System.out.println(multimap.get("key1"));
        System.out.println(multimap.get("key2"));


        //MultiSet: 无序+可重复   count()方法获取单词的次数  增强了可读性+操作简单
        Multiset<String> set = HashMultiset.create();
        set.add("csc");
        set.add("lwl");
        set.add("csc");
        System.out.println(set.size());
        System.out.println(set.count("csc"));
    }


    public static void loadingCacheTest() throws ExecutionException {
        LoadingCache<Integer, Long> cacheMap = CacheBuilder.newBuilder().initialCapacity(10)
                .concurrencyLevel(10)
                .expireAfterAccess(Duration.ofSeconds(10))
                .weakValues()
                .recordStats()
                .removalListener(new RemovalListener<Integer, Long>() {
                    @Override
                    public void onRemoval(RemovalNotification<Integer, Long> notification) {
                        System.out.println(notification.getValue());
                    }
                })
                .build(new CacheLoader<Integer, Long>() {
                    @Override
                    public Long load(Integer key) throws Exception {
                        System.out.println(key);
                        return System.currentTimeMillis();
                    }
                });
        System.out.println(cacheMap.get(1));
        cacheMap.put(2, 5454L);
        cacheMap.put(3, 344L);
        cacheMap.put(4, 404L);
        System.out.println(cacheMap.get(2));


    }
}
