package com.semihunaldi.backendbootstrap.services.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by semihunaldi on 22.11.2018
 */

@Component
@Profile("redis-cache")
public class RedisCacheService implements CacheService {

	@Autowired
	private RedisTemplate redisTemplate;

	private HashOperations hashOperations;

	@PostConstruct
	public void init() {
		hashOperations = redisTemplate.opsForHash();
	}

	@Override
	public Long delete(String s, Object... objects) {
		return hashOperations.delete(s,objects);
	}

	@Override
	public Boolean hasKey(String s, Object o) {
		return hashOperations.hasKey(s,o);
	}

	@Override
	public Object get(String s, Object o) {
		return hashOperations.get(s,o);
	}

	@Override
	public List<Object> multiGet(String s, Collection<Object> collection) {
		return hashOperations.multiGet(s,collection);
	}

	@Override
	public Long increment(String s, Object o, long l) {
		return hashOperations.increment(s,o,l);
}

	@Override
	public Double increment(String s, Object o, double v) {
		return hashOperations.increment(s,o,v);
	}

	@Override
	public Set<Object> keys(String s) {
		return hashOperations.keys(s);
	}

	@Override
	public Long lengthOfValue(String s, Object o) {
		return hashOperations.lengthOfValue(s,o);
	}

	@Override
	public Long size(String s) {
		return hashOperations.size(s);
	}

	@Override
	public void putAll(String s, Map<?, ?> map) {
		hashOperations.putAll(s,map);
	}

	@Override
	public void put(String s, Object o, Object o2) {
		hashOperations.put(s,o,o2);
	}

	@Override
	public Boolean putIfAbsent(String s, Object o, Object o2) {
		return hashOperations.putIfAbsent(s,o,o2);
	}

	@Override
	public List<Object> values(String s) {
		return hashOperations.values(s);
	}

	@Override
	public Map<Object, Object> entries(String s) {
		return hashOperations.entries(s);
	}

	@Override
	public Cursor<Map.Entry<Object, Object>> scan(String s, ScanOptions scanOptions) {
		return hashOperations.scan(s,scanOptions);
	}

	@Override
	public RedisOperations<String, ?> getOperations() {
		return hashOperations.getOperations();
	}
}
