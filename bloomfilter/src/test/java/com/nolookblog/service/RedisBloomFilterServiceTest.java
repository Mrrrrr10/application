package com.nolookblog.service;

import com.google.common.base.Charsets;
import com.google.common.hash.Funnel;
import com.nolookblog.Application;
import com.nolookblog.bloomfilter.RedisBloomFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Mrrrrr10
 * @github https://github.com/Mrrrrr10
 * @blog https://nolookblog.com/
 * @description
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RedisBloomFilterServiceTest {


	@Autowired
	private RedisBloomFilterService redisBloomFilterService;

	@Test
	public void test() {
		RedisBloomFilter<String> bloomFilter = RedisBloomFilter.create((Funnel<String>) (from, into)
				-> into
				.putString(from, Charsets.UTF_8)
				.putString(from, Charsets.UTF_8), 1000000, 0.01);

//		// 插入数据
//		for (int i = 0; i < 1000000; i++) {
//			redisBloomFilterService.add(bloomFilter, "redis_bloomfilter", i + "");
//		}

		// 判断数据是否存在
		int count = 0;
		for (int i = 1000000; i < 2000000; i++) {
			if (redisBloomFilterService.contains(bloomFilter, "redis_bloomfilter", i + "")) {
				count++;
				System.out.println(i + "误判了");
			}
		}

		System.out.println("总共的误判数:" + count);

	}

}
