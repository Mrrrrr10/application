package com.nolookblog.service;

import com.nolookblog.bloomfilter.RedisBloomFilter;

/**
 * @author Mrrrrr10
 * @github https://github.com/Mrrrrr10
 * @blog https://nolookblog.com/
 * @description
 */

public interface RedisBloomFilterService {

	/**
	 * 根据给定的布隆过滤器添加值
	 *
	 * @param redisBloomFilter
	 * @param key
	 * @param value
	 * @param <T>
	 */
	<T> void add(RedisBloomFilter<T> redisBloomFilter, String key, T value);

	/**
	 * 根据给定的布隆过滤器判断值是否存在
	 *
	 * @param redisBloomFilter
	 * @param key
	 * @param value
	 * @param <T>
	 * @return
	 */
	<T> boolean contains(RedisBloomFilter<T> redisBloomFilter, String key, T value);
}
