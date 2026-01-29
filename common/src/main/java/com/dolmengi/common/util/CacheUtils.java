package com.dolmengi.common.util;

import com.dolmengi.common.constants.CacheType;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class CacheUtils {

    private CacheUtils() {}

    private static final Map<CacheType, Cache<Object, Object>> cacheMap = new ConcurrentHashMap<>();

    private static Cache<Object, Object> getOrInitCache(CacheType type, Duration ttl) {
        return cacheMap.computeIfAbsent(type, key -> {
            Duration expire = ttl == null ? type.getTtl() : ttl;

            return Caffeine.newBuilder()
                    .expireAfterWrite(expire)
                    //.maximumSize(10_000) // 기본 최대 크기 설정
                    .recordStats()
                    .build();
        });
    }

    /**
     * 캐시 저장
     */
    public static <K, V> void put(CacheType type, K key, V value) {
        put(type, key, value, null);
    }

    /**
     * 캐시 저장
     */
    public static <K, V> void put(CacheType type, K key, V value, Duration ttl) {
        Cache<Object, Object> cache = getOrInitCache(type, ttl);
        cache.put(key, value);
    }

    /**
     * 캐시 조회
     */
    @SuppressWarnings("unchecked")
    public static <K, V> V get(CacheType type, K key) {
        Cache<Object, Object> cache = cacheMap.get(type);
        if (cache == null) return null;

        return (V) cache.getIfPresent(key);
    }

    /**
     * 캐시 전체 조회
     */
    public static Map<Object, Object> getAll(CacheType type) {
        Cache<Object, Object> cache = cacheMap.get(type);
        if (cache == null) return Map.of();

        return Map.copyOf(cache.asMap());
    }

    /**
     * 캐시 삭제
     */
    public static <K> void evict(CacheType type, K key) {
        Cache<Object, Object> cache = cacheMap.get(type);
        if (cache != null) cache.invalidate(key);
    }

    /**
     * 캐시 초기화
     */
    public static void clear(CacheType type) {
        Cache<Object, Object> cache = cacheMap.get(type);
        if (cache != null) cache.invalidateAll();
    }

    /**
     * 캐시 전체 초기화
     */
    public static void clearAll() {
        cacheMap.values().forEach(Cache::invalidateAll);
    }

    /**
     * 캐시 사이즈
     */
    public static long size(CacheType type) {
        Cache<Object, Object> cache = cacheMap.get(type);

        return cache != null ? cache.estimatedSize() : 0;
    }

    /**
     * 캐시 통계 조회
     */
    public static String getStats(CacheType type) {
        Cache<Object, Object> cache = cacheMap.get(type);

        return cache != null ? cache.stats().toString() : null;
    }

}
