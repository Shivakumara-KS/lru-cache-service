package com.example.lru_cache_service.service;


import com.example.lru_cache_service.dto.LruRequest;
import com.example.lru_cache_service.entity.LruCache;
import com.example.lru_cache_service.repository.LruCacheRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CacheServiceTest {

    @InjectMocks
    CacheService service;

    @Mock
    LruCacheRepository repository;

    LruCache cache;

    @BeforeEach
    void init(){
        cache = LruCache.builder()
                .key("name")
                .value("Shiva")
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();
    }

    @Test
    void test_CreateCache(){
        when(repository.findByKey(any())).thenReturn(Optional.empty());
        when(repository.save(any())).thenReturn(cache);
        LruRequest request = new LruRequest("name", "Shiva");
        String create = service.createOrUpdateCache(request);
        assertEquals("Cache created successfully", create);
    }

}

