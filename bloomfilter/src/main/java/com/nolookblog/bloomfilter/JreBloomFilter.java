package com.nolookblog.bloomfilter;

import io.rebloom.client.Client;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author Mrrrrr10
 * @github https://github.com/Mrrrrr10
 * @blog https://nolookblog.com/
 * @description	基于jrebloom的布隆过滤器实现
 */

public class JreBloomFilter {
	public static void main(String[] args) {
		JedisPoolConfig conf = new JedisPoolConfig();
		conf.setMaxTotal(100);
		conf.setTestOnBorrow(false);
		conf.setTestOnReturn(false);
		conf.setTestOnCreate(false);
		conf.setTestWhileIdle(false);
		conf.setMinEvictableIdleTimeMillis(60000L);
		conf.setTimeBetweenEvictionRunsMillis(30000L);
		conf.setNumTestsPerEvictionRun(-1);
		conf.setFairness(true);
		JedisPool jedisPool = new JedisPool(conf, "localhost", 6379, 30000);
		Client client = new Client(jedisPool);

		boolean exists = client.exists("newFilter", "foo55");
		System.out.println(exists);

		client.add("newFilter", "123");
		client.add("newFilter", "456");
		client.add("newFilter", "789");
		client.close();
	}
}
