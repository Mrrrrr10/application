package com.nolookblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author Mrrrrr10
 * @description redis配置类
 */
@Configuration
public class RedisConfig {

	/**
	 * redis默认配置类: RedisAutoConfiguration类
	 *
	 * @param redisConnectionFactory
	 * @return
	 * @tips: 注解@ConditionalOnMissingBean(name = "redisTemplate")
	 * 当没有配置RedisTemplate的时候, springboot将帮我们默认配置RedisTemplate
	 */
	@Bean
	public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate template = new RedisTemplate();

		template.setConnectionFactory(redisConnectionFactory);
		// key 的序列化采用 StringRedisSerializer
		template.setKeySerializer(new StringRedisSerializer());
		template.setHashKeySerializer(new StringRedisSerializer());

		// value 的序列化采用 FastJsonRedisSerializer
//		FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer(Object.class);
//		template.setValueSerializer(fastJsonRedisSerializer);
//		template.setHashValueSerializer(fastJsonRedisSerializer);

		/*

		// value 的序列化采用 Jackson2JsonRedisSerializer
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer =
				new Jackson2JsonRedisSerializer(Object.class);

		ObjectMapper objectMapper = new ObjectMapper();

		// 定制化
		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addSerializer(DateTime.class, new JodaDateTimeJsonSerializer());
		simpleModule.addDeserializer(DateTime.class, new JodaDateTimeJsonDeserializer());

		// 告诉redisTemplate应该怎样去解析对应的参数
		objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

		// 注册Module
		objectMapper.registerModule(simpleModule);

		jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

		template.setValueSerializer(jackson2JsonRedisSerializer);

		*/
		template.setDefaultSerializer(new StringRedisSerializer());
		template.afterPropertiesSet();

		return template;
	}
}
