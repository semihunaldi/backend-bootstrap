package com.semihunaldi.backendbootstrap.services.redis;

import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by semihunaldi on 22.11.2018
 */

@Component
@Profile("local-cache")
public class LocalCacheService implements CacheService {

	//memory cache.
	private final Map<String, Object> map = new ConcurrentHashMap<>(256);

	@Override
	public Long delete(String s, Object... objects) {
		map.remove(s);
		return 0L;
	}

	@Override
	public Boolean hasKey(String s, Object o) {
		return map.containsKey(s);
	}

	@Override
	public Object get(String s, Object o) {
		return map.getOrDefault(s, null);
	}

	@Override
	public List<Object> multiGet(String s, Collection<Object> collection) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long increment(String s, Object o, long l) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Double increment(String s, Object o, double v) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<Object> keys(String s) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long lengthOfValue(String s, Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long size(String s) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void putAll(String s, Map<?, ?> map) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void put(String s, Object o, Object o2) {
		map.put(s, o2);
	}

	@Override
	public Boolean putIfAbsent(String s, Object o, Object o2) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Object> values(String s) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Map<Object, Object> entries(String s) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Cursor<Map.Entry<Object, Object>> scan(String s, ScanOptions scanOptions) {
		throw new UnsupportedOperationException();
	}

	@Override
	public RedisOperations<String, ?> getOperations() {
		throw new UnsupportedOperationException();
	}
}
