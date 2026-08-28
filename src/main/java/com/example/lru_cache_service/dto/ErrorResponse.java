package com.example.lru_cache_service.dto;

public record ErrorResponse (
        Integer status,
        String message
){
}
