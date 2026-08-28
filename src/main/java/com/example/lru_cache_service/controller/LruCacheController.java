package com.example.lru_cache_service.controller;

import com.example.lru_cache_service.dto.LruRequest;
import com.example.lru_cache_service.dto.LruResponse;
import com.example.lru_cache_service.service.CacheService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cache")
@RequiredArgsConstructor
public class LruCacheController {

    private final CacheService cacheService;

    @GetMapping("/{key}")
    public ResponseEntity<LruResponse> getCache(@PathVariable String key)
    {
        return ResponseEntity.ok()
                .body(cacheService.getCacheByKey(key));
    }

    @PostMapping
    public ResponseEntity<String> saveCache(@RequestBody @Valid LruRequest request)
    {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cacheService.createOrUpdateCache(request));
    }

    @PutMapping
    public ResponseEntity<String> updateCache(@RequestBody @Valid LruRequest request)
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(cacheService.createOrUpdateCache(request));
    }
    @DeleteMapping("/{key}")
    public ResponseEntity<String> deleteCache(@PathVariable String key)
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(cacheService.deleteCache(key));
    }
    @GetMapping("/clear/all")
    public ResponseEntity<String> deleteAllCache()
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(cacheService.clearCache());
    }






}
