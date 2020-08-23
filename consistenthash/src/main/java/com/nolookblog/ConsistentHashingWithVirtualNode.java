package com.nolookblog;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author Mrrrrr10
 * @github https://github.com/Mrrrrr10
 * @blog https://nolookblog.com/
 * @description 带虚拟节点的一致性Hash算法
 */

public class ConsistentHashingWithVirtualNode {

	/**
	 * 服务器节点列表
	 */
	private static String[] servers = {"192.168.0.0:111", "192.168.0.1:111", "192.168.0.2:111",
			"192.168.0.3:111", "192.168.0.4:111"};

	/**
	 * 真实节点列表，考虑到服务器上线、下线的场景，即添加、删除的场景会比较频繁，这里使用LinkedList会更好
	 */
	private static List<String> realNodes = new LinkedList<>();

	/**
	 * 虚拟节点，key表示虚拟节点的hash值，value表示虚拟节点的名称
	 */
	private static SortedMap<Integer, String> virtualNodes = new TreeMap<>();

	/**
	 * 虚拟节点的数目，这里写死，为了演示需要，一个真实结点对应5个虚拟节点
	 */
	private static final int VIRTUAL_NODES = 5;

	static {
		// 1.先把原始的服务器节点逐个添加到真实节点列表中
		for (int i = 0; i < servers.length; i++)
			realNodes.add(servers[i]);

		// 2.再添加虚拟节点，遍历LinkedList使用foreach循环效率会比较高
		for (String str : realNodes) {
			for (int i = 0; i < VIRTUAL_NODES; i++) {
				String virtualNodeName = str + "&&VN" + String.valueOf(i);
				int hash = getHash(virtualNodeName);
				System.out.println("虚拟节点：[" + virtualNodeName + "]被添加, hash值为" + hash);
				virtualNodes.put(hash, virtualNodeName);
			}
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
		SortedMap<Integer, String> subMap = virtualNodes.tailMap(hash);

		// 3.获取离该key顺时针过去第一个最近的那个虚拟服务器节点Hash值
		Integer i = subMap.firstKey();

		// 4.获取对应的虚拟节点名称，这里字符串稍微截取一下
		String virtualNode = subMap.get(i);

		// 5.返回真实服务器节点
		return virtualNode.substring(0, virtualNode.indexOf("&&"));
	}

	public static void main(String[] args) {
		String[] keys = {"127.0.0.1:1111", "221.226.0.1:2222", "10.211.0.1:3333"};
		for (int i = 0; i < keys.length; i++)
			System.out.println("缓存数据key：[" + keys[i] + "]的Hash值为" +
					getHash(keys[i]) + ", 被路由到服务器节点[" + getServer(keys[i]) + "]");
	}
}