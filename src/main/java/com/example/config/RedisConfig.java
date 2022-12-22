package com.example.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration
@EnableRedisRepositories
public class RedisConfig {

	@Value("${spring.redis.host}")
	private String host;

	@Value("${spring.redis.port}")
	private String port;

	@Bean(name = "redisConnectionFactory")
	public JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration jedisConnectionFactory = new RedisStandaloneConfiguration();
		jedisConnectionFactory.setHostName(host);
		jedisConnectionFactory.setPort(Integer.parseInt(port));
		return new JedisConnectionFactory(jedisConnectionFactory);
	}

//	@Bean
//	public RedisTemplate<String, Object> redisTemplate() {
//		RedisTemplate<String, Object> template = new RedisTemplate<>();
//		template.setConnectionFactory(jedisConnectionFactory());
//		template.setDefaultSerializer(new StringRedisSerializer());
//		template.setKeySerializer(new GenericToStringSerializer<>(Object.class));
//		template.setHashValueSerializer(new GenericToStringSerializer<>(Object.class));
//		template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
//		return template;
//	}

	@Bean(name = "redisTemplate")
	public RedisTemplate<String, Object> redisTemplate(
			@Qualifier(value = "redisConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		template.setDefaultSerializer(new GenericToStringSerializer<>(Object.class));
		template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
		template.afterPropertiesSet();
		return template;
	}
}
