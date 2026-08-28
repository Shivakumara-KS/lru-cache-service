package com.example.lru_cache_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "lru_cache")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LruCache {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String key;

    private String value;

    private Timestamp timestamp;
}

