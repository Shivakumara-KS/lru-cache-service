package com.example.lru_cache_service.service;

import com.example.lru_cache_service.dto.LruRequest;
import com.example.lru_cache_service.dto.LruResponse;
import com.example.lru_cache_service.entity.LruCache;
import com.example.lru_cache_service.exception.KeyNotFoundInCacheException;
import com.example.lru_cache_service.repository.LruCacheRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class CacheService {

    @Value("${LRU_CACHE_CAPACITY:5}")
    private Integer CACHE_CAPACITY = 5;

    public Map<String, String> lruCache = new ConcurrentHashMap<>(CACHE_CAPACITY);

    private final LruCacheRepository lruCacheRepository;

    public LruResponse getCacheByKey(String key) {
        if(!lruCache.containsKey(key)){
            throw new KeyNotFoundInCacheException("Key Not Found in Cache");
        }
        return new LruResponse(key, lruCache.get(key));
    }

    @Transactional
    public String createOrUpdateCache(LruRequest request) {
        lruCache.put(request.key(), request.value());

        Optional<LruCache> cache = lruCacheRepository.findByKey(request.key());
        if(cache.isPresent()){
           LruCache lruCache = cache.get();
           lruCache.setValue(request.value());
           lruCache.setTimestamp(new Timestamp(System.currentTimeMillis()));
           lruCacheRepository.save(lruCache);

        }else{
            LruCache newcache = LruCache.builder()
                    .key(request.key())
                    .value(request.value())
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
            lruCacheRepository.save(newcache);
        }
        return "Cache created successfully";
    }


    public String deleteCache(String key) {
        if(!lruCache.containsKey(key)){
            throw new KeyNotFoundInCacheException("Key Not Found in Cache");
        }
        lruCache.remove(key);
        lruCacheRepository.deleteByKey(key);

        return "Cache deleted successfully";
    }

    public String clearCache() {
        lruCache.clear();
        lruCacheRepository.deleteAll();
        return "Cache cleared successfully";
    }

}
