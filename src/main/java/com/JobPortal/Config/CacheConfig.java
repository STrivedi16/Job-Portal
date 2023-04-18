package com.JobPortal.Config;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

//import com.example.Users.Redis.ResponceEntityDeserializer;


@Configuration
@EnableRedisRepositories
@EnableCaching
public class CacheConfig {

	private Logger LOG = LoggerFactory.getLogger(CacheConfig.class);

	@Value("${spring.redis.port}")
	private int port;
	
	@Value("${spring.redis.host}")
	private String host;
	
	public CacheConfig() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Bean
	public LettuceConnectionFactory connectionFactory() {
		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
		
		LOG.info("Caching is invoked... ");
		configuration.setHostName(host);
		configuration.setPort(port);
		return new LettuceConnectionFactory(configuration);

	}

//	@Bean
//	public RedisTemplate<String, Object> redisTemplate() {
//
//		RedisTemplate<String, Object> template = new RedisTemplate<>();
//		RedisSerializer<String> stringSerializer = new StringRedisSerializer();
//		JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();
//		LettuceConnectionFactory lcf = connectionFactory();
//		lcf.afterPropertiesSet();
//		template.setConnectionFactory(lcf);
//		template.setKeySerializer(stringSerializer);
//		template.setHashKeySerializer(stringSerializer);
//		template.setValueSerializer(jdkSerializationRedisSerializer);
//		template.setHashValueSerializer(jdkSerializationRedisSerializer);
//		template.setEnableTransactionSupport(true);
//		template.afterPropertiesSet();
//		System.err.println("in Redis Template");
//		return template;
//	}
//
//	@Bean
//	public RedisCacheConfiguration cacheConfiguration() {
//		System.err.println("In the Redis Component");
//		return RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(60)).disableCachingNullValues()
//				.serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
//	}
//
//	@Bean
//	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
//
//		System.err.println("In Redis Builder");
//		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
//		builder.deserializerByType(ResponseEntity.class, new ResponceEntityDeserializer());
//		return builder;
//	}
	
	@Bean
	public RedisCacheConfiguration cacheConfiguration() {

		RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(Duration.ofSeconds(600)).disableCachingNullValues();
		return cacheConfig;

	}

	@Bean
	public RedisCacheManager cacheManager() {

		RedisCacheManager rcm = RedisCacheManager.builder(connectionFactory()).cacheDefaults(cacheConfiguration())
				.transactionAware().build();
		return rcm;

	}

	public RedisTemplate<String, Object> redisTemplate() {

		RedisTemplate<String, Object> template = new RedisTemplate<>();
		RedisSerializer<String> stringSerializer = new StringRedisSerializer();
		JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();
		LettuceConnectionFactory lcf = connectionFactory();
		lcf.afterPropertiesSet();
		template.setConnectionFactory(lcf);
		template.setKeySerializer(stringSerializer);
		template.setHashKeySerializer(stringSerializer);
		template.setValueSerializer(jdkSerializationRedisSerializer);
		template.setHashValueSerializer(jdkSerializationRedisSerializer);
		template.setEnableTransactionSupport(true);
		template.afterPropertiesSet();
		return template;

	}

	
	

}
