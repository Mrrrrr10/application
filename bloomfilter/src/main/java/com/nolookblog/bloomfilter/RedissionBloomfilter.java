package com.nolookblog.bloomfilter;

import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @author Mrrrrr10
 * @github https://github.com/Mrrrrr10
 * @blog https://nolookblog.com/
 * @description	基于redission的布隆过滤器
 */

public class RedissionBloomfilter {

	public static void main(String[] args) {
		Config config = new Config();
		config.useSingleServer().setAddress("redis://localhost:6379");

		RedissonClient redissonClient = Redisson.create(config);
		RBloomFilter<String> bloomFilter = redissonClient.getBloomFilter("redission_bloomfilter");

		// 布隆过滤器计算的正确率为99%，初始化布隆过滤器容量为1000000L
		bloomFilter.tryInit(1000000L, 0.01);

//		// 插入数据
//		for (int i = 0; i < 1000000; i++) {
//			bloomFilter.add(i + "");
//		}

		// 判断数据是否存在
		int count = 0;
		for (int i = 1000000; i < 2000000; i++) {
			if (bloomFilter.contains(i + "")) {
				count++;
				System.out.println(i + "误判了");
			}
		}

		System.out.println("总共的误判数:" + count);
	}

}
