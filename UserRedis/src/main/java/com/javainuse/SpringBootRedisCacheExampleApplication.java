package com.javainuse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.javainuse.entity.User;

@SpringBootApplication
@EnableCaching(proxyTargetClass = true)
public class SpringBootRedisCacheExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRedisCacheExampleApplication.class, args);
	}
	@Bean
	JedisConnectionFactory jedisConnectionFactory(){
		return new JedisConnectionFactory();
	}

	@Bean
	RedisTemplate<String, User> redisTemplate(){
		RedisTemplate<String,User> redisTemplate = new RedisTemplate<String, User>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return redisTemplate;
	}
}
