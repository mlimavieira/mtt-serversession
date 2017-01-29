package com.mttnow.session;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class SpringBootDemoApplication extends CachingConfigurerSupport {

	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication.class, args);
	}

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Beer").apiInfo(apiInfo()).select()
				.paths(PathSelectors.regex("/.*")).build();
	}

	ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Spring REST Application").description("Spring REST Application")
				.contact("Marcio Vieira").license("Apache License Version 2.0").version("2.0").build();
	}

	/*
	 * JEDIS CONNECTION
	 * 
	 * @Bean RedisTemplate<Object, Object> redisTemplate() {
	 * RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object,
	 * Object>(); redisTemplate.setConnectionFactory(redisson());
	 * redisTemplate.set return redisTemplate; }
	 * 
	 * @Bean JedisConnectionFactory jedisConnectionFactory() {
	 * JedisConnectionFactory factory = new JedisConnectionFactory();
	 * factory.setHostName("localhost"); factory.setPort(6379);
	 * factory.setUsePool(true); return factory; }
	 * 
	 * @Bean CacheManager cacheManager() {
	 * 
	 * RedissonSpringCacheManager redisCacheManager = new
	 * RedissonSpringCacheManager(redisTemplate());
	 * redisCacheManager.setUsePrefix(true);
	 * redisCacheManager.setDefaultExpiration(60);
	 * 
	 * Map<String, Long> expires = new HashMap<>(); expires.put("beerResult",
	 * 30L); redisCacheManager.setExpires(expires); return redisCacheManager; }
	 */

}
