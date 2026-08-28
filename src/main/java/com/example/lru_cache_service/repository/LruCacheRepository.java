package com.example.lru_cache_service.repository;

import com.example.lru_cache_service.entity.LruCache;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LruCacheRepository extends JpaRepository<LruCache,Long> {

    void deleteByKey(@NotBlank String key);
    Optional<LruCache> findByKey(@NotBlank String key);

  /*  @Query("""
    select c from lruCache c
    order by c.timestamp desc limit :limit
"""
    )
    List<LruCache> findLatestCache(@Param("limit")  int limit) ;*/

}
