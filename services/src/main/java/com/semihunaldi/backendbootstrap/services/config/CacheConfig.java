package com.semihunaldi.backendbootstrap.services.config;

import com.semihunaldi.backendbootstrap.services.redis.CacheService;
import com.semihunaldi.backendbootstrap.services.redis.LocalCacheService;
import com.semihunaldi.backendbootstrap.services.redis.RedisCacheService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Created by semihunaldi on 22.11.2018
 */

@Configuration
public class CacheConfig {

	@Bean(name = "local")
	@Primary
	public CacheService getCacheService() {
		return new LocalCacheService();
	}

	@Bean(name = "redis")
	public CacheService getDistributedCacheService() {
		return new RedisCacheService();
	}
}
