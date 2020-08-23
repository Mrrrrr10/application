package com.nolookblog.service.impl;

import com.google.common.base.Preconditions;
import com.nolookblog.bloomfilter.RedisBloomFilter;
import com.nolookblog.service.RedisBloomFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Mrrrrr10
 * @github https://github.com/Mrrrrr10
 * @blog https://nolookblog.com/
 * @description
 */

@Service
public class RedisBloomFilterServiceImpl implements RedisBloomFilterService {

	@Autowired
	private RedisTemplate redisTemplate;

	/**
	 * 根据给定的布隆过滤器添加值
	 *
	 * @param redisBloomFilter
	 * @param key
	 * @param value
	 * @param <T>
	 */
	@Override
	public <T> void add(RedisBloomFilter<T> redisBloomFilter, String key, T value) {
		Preconditions.checkArgument(redisBloomFilter != null, "redisBloomFilter不能为空");
		int[] offset = redisBloomFilter.murmurHashOffset(value);
		for (int i : offset) {
			redisTemplate.opsForValue().setBit(key, i, true);
		}
	}

	/**
	 * 根据给定的布隆过滤器判断值是否存在
	 *
	 * @param redisBloomFilter
	 * @param key
	 * @param value
	 * @param <T>
	 * @return
	 */
	@Override
	public <T> boolean contains(RedisBloomFilter<T> redisBloomFilter, String key, T value) {
		Preconditions.checkArgument(redisBloomFilter != null, "redisBloomFilter不能为空");
		int[] offset = redisBloomFilter.murmurHashOffset(value);
		for (int i : offset) {
			if (!redisTemplate.opsForValue().getBit(key, i)) {
				return false;
			}
		}

		return true;
	}
}
