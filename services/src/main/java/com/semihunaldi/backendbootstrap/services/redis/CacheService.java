package com.semihunaldi.backendbootstrap.services.redis;

import org.springframework.data.redis.core.HashOperations;

/**
 * Created by semihunaldi on 22.11.2018
 */
public interface CacheService extends HashOperations<String,Object,Object> {

}
