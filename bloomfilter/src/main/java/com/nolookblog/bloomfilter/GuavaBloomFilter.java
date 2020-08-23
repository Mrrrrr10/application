package com.nolookblog.bloomfilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * @author Mrrrrr10
 * @github https://github.com/Mrrrrr10
 * @blog https://nolookblog.com/
 * @description	基于guava的布隆过滤器实现
 */

public class GuavaBloomFilter {

	/**
	 * 预计要插入多少数据
	 */
	private static int size = 1000000;

	/**
	 * 期望的误判率
	 */
	private static double fpp = 0.01;

	private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size, fpp);

	public static void main(String[] args) {
		// 插入数据
		for (int i = 0; i < 1000000; i++) {
			bloomFilter.put(i);
		}

		// 判断数据是否存在
		int count = 0;
		for (int i = 1000000; i < 2000000; i++) {
			if (bloomFilter.mightContain(i)) {
				count++;
				System.out.println(i + "误判了");
			}
		}

		System.out.println("总共的误判数:" + count);
	}

}
