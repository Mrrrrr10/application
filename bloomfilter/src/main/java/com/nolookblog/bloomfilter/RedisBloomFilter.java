package com.nolookblog.bloomfilter;

import com.google.common.base.Preconditions;
import com.google.common.hash.Funnel;
import com.google.common.hash.Hashing;

/**
 * @author Mrrrrr10
 * @github https://github.com/Mrrrrr10
 * @blog https://nolookblog.com/
 * @description 基于 redis + guava 的布隆过滤器实现
 */

public class RedisBloomFilter<T> {

	public int numHashFunctions;

	public int bitSize;

	public Funnel<T> funnel;

	/**
	 * RedisBloomFilter构造器
	 *
	 * @param funnel
	 * @param expectedInsertions 预计要插入多少数据
	 * @param fpp                期望的误判率
	 */
	public RedisBloomFilter(Funnel<T> funnel, long expectedInsertions, double fpp) {
		this.funnel = funnel;
		bitSize = optimalNumOfBits(expectedInsertions, fpp);
		numHashFunctions = optimalNumOfHashFunctions(expectedInsertions, bitSize);
	}

	public static <T> RedisBloomFilter<T> create(Funnel<? super T> funnel, int expectedInsertions, double fpp) {
		return create(funnel, (long) expectedInsertions, fpp);
	}

	private static <T> RedisBloomFilter<T> create(Funnel<? super T> funnel, long expectedInsertions, double fpp) {
		Preconditions.checkNotNull(funnel);
		Preconditions.checkArgument(expectedInsertions >= 0L, "Expected insertions (%s) must be >= 0", new Object[]{expectedInsertions});
		Preconditions.checkArgument(fpp > 0.0D, "False positive probability (%s) must be > 0.0", new Object[]{fpp});
		Preconditions.checkArgument(fpp < 1.0D, "False positive probability (%s) must be < 1.0", new Object[]{fpp});
		if (expectedInsertions == 0L) {
			expectedInsertions = 1L;
		}

		try {
			return new RedisBloomFilter(funnel, expectedInsertions, fpp);
		} catch (IllegalArgumentException var10) {
			long numBits = optimalNumOfBits(expectedInsertions, fpp);
			throw new IllegalArgumentException("Could not create BloomFilter of " + numBits + " bits", var10);
		}
	}

	public int[] murmurHashOffset(T value) {
		int[] offset = new int[numHashFunctions];

		long hash64 = Hashing.murmur3_128().hashObject(value, funnel).asLong();
		int hash1 = (int) hash64;
		int hash2 = (int) (hash64 >>> 32);
		for (int i = 1; i <= numHashFunctions; i++) {
			int nextHash = hash1 + i * hash2;
			if (nextHash < 0) {
				nextHash = ~nextHash;
			}
			offset[i - 1] = nextHash % bitSize;
		}

		return offset;
	}

	/**
	 * 计算bit数组的长度
	 *
	 * @param n
	 * @param p
	 * @return
	 */
	private static int optimalNumOfBits(long n, double p) {
		if (p == 0) {
			p = Double.MIN_VALUE;
		}
		return (int) (-n * Math.log(p) / (Math.log(2) * Math.log(2)));
	}

	/**
	 * 计算hash方法执行次数
	 *
	 * @param n
	 * @param m
	 * @return
	 */
	private static int optimalNumOfHashFunctions(long n, long m) {
		return Math.max(1, (int) Math.round((double) m / n * Math.log(2)));
	}
}
