package com.example.lru_cache_service.exception;

public class KeyNotFoundInCacheException extends RuntimeException {
    public KeyNotFoundInCacheException(String message) {
        super(message);
    }
}
