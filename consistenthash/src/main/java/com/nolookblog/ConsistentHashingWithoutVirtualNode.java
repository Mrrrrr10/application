package com.nolookblog;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author Mrrrrr10
 * @github https://github.com/Mrrrrr10
 * @blog https://nolookblog.com/
 * @description	不带虚拟节点的一致性Hash算法
 */

public class ConsistentHashingWithoutVirtualNode {

	/**
	 * 服务器节点列表
	 */
	private static String[] servers = {"192.168.0.0:111", "192.168.0.1:111", "192.168.0.2:111",
			"192.168.0.3:111", "192.168.0.4:111"};

	/**
	 * key表示服务器的hash值，value表示服务器的名称
	 */
	private static SortedMap<Integer, String> sortedMap = new TreeMap<>();

	/**
	 * 程序初始化，将所有的服务器节点放入sortedMap中
	 */
	static {
		for (int i = 0; i < servers.length; i++) {
			int hash = getHash(servers[i]);
			System.out.println("服务器节点：[" + servers[i] + "]加入集合中, 其Hash值为" + hash);
			sortedMap.put(hash, servers[i]);
		}

		System.out.println();
	}

	/**
	 * 使用FNV1_32_HASH算法计算服务器的Hash值，这里不使用重写hashCode的方法，最终效果没区别
	 *
	 * @param str
	 * @return
	 */
	private static int getHash(String str) {
		final int p = 16777619;
		int hash = (int) 2166136261L;
		for (int i = 0; i < str.length(); i++)
			hash = (hash ^ str.charAt(i)) * p;
		hash += hash << 13;
		hash ^= hash >> 7;
		hash += hash << 3;
		hash ^= hash >> 17;
		hash += hash << 5;

		// 如果算出来的值为负数则取其绝对值
		if (hash < 0) {
			hash = Math.abs(hash);
		}

		return hash;
	}

	/**
	 * 根据缓存数据key，获取路由后到的服务器节点
	 *
	 * @param key
	 * @return
	 */
	private static String getServer(String key) {
		// 1.先计算缓存数据key的Hash值
		int hash = getHash(key);

		// 2.获取大于该Hash值的所有Map
		SortedMap<Integer, String> subMap = sortedMap.tailMap(hash);

		// 3.获取离该key顺时针过去第一个最近的那个服务器节点Hash值，并返回
		Integer i = subMap.firstKey();

		return subMap.get(i);
	}

	public static void main(String[] args) {
		String[] keys = {"127.0.0.1:1111", "221.226.0.1:2222", "10.211.0.1:3333"};
		for (int i = 0; i < keys.length; i++)
			System.out.println("缓存数据key：[" + keys[i] + "]的Hash值为" +
					getHash(keys[i]) + ", 被路由到服务器节点[" + getServer(keys[i]) + "]");
	}
}