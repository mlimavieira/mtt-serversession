package com.mttnow.session;

import java.util.HashMap;
import java.util.Map;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;

@EnableCaching
public class CustomCacheConfig implements CachingConfigurer {

	private static final Long ONE_SECOND = 60 * 1000L;
	
	@Value("#{custom.spring.cache.address}")
	private String cacheAddress;

	private RedissonClient redisson() {
		Config config = new Config();
		config.useSingleServer().setAddress(cacheAddress);
		return Redisson.create(config);
	}

	@Override
	public CacheManager cacheManager() {
		Map<String, CacheConfig> config = new HashMap<String, CacheConfig>();

		config.put("beerResult", new CacheConfig(30 * ONE_SECOND, 60 * ONE_SECOND));

		RedissonSpringCacheManager redissonSpringCacheManager = new RedissonSpringCacheManager(redisson(), config);

		return redissonSpringCacheManager;
	}

	@Override
	public CacheResolver cacheResolver() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KeyGenerator keyGenerator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CacheErrorHandler errorHandler() {
		// TODO Auto-generated method stub
		return null;
	}

}
