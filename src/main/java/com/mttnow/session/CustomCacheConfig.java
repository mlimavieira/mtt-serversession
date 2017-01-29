package com.mttnow.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@EnableCaching
public class CustomCacheConfig extends CachingConfigurerSupport {

	private static final Logger LOG = LoggerFactory.getLogger(CustomCacheConfig.class);
	private static final Long ONE_SECOND = 60 * 1000L;

	@Value("${custom.spring.cache.hostName}")
	private String cacheHostName;

	@Value("${custom.spring.cache.port}")
	private int cachePort;

	@Bean
	public JedisConnectionFactory redisConnectionFactory() {
		JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();

		redisConnectionFactory.setHostName(cacheHostName);
		redisConnectionFactory.setPort(cachePort);
		return redisConnectionFactory;
	}

	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf) {
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
		redisTemplate.setConnectionFactory(cf);
		return redisTemplate;
	}

	@Bean
	public CacheManager cacheManager(RedisTemplate redisTemplate) {
		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
		cacheManager.setDefaultExpiration(60 * ONE_SECOND);
		return cacheManager;
	}

	//
	//
	// private RedissonClient redisson() {
	// LOG.info("\n\n\n*********************");
	// LOG.info("CacheAddress: " + cacheAddress);
	// LOG.info("*********************\n\n\n\n");
	// Config config = new Config();
	// config.useSingleServer().setAddress(cacheAddress);
	// return Redisson.create(config);
	// }
	//
	// @Bean
	// @Override
	// public CacheManager cacheManager() {
	// Map<String, CacheConfig> config = new HashMap<String, CacheConfig>();
	//
	// config.put("beerResult", new CacheConfig(30 * ONE_SECOND, 60 *
	// ONE_SECOND));
	//
	// RedissonSpringCacheManager redissonSpringCacheManager = new
	// RedissonSpringCacheManager(redisson(), config);
	//
	// return redissonSpringCacheManager;
	// }


}
