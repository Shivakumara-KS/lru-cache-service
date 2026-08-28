package com.example.lru_cache_service.dto;

import jakarta.validation.constraints.NotBlank;

public record LruRequest(
        @NotBlank
        String key,

        @NotBlank
        String value
) {
}
