package com.nolookblog.common.constant;

/**
 * @author Mrrrrr10
 * @description redis常量类
 */
public class RedisConstants {

	/**
	 * token前缀
	 */
	public static final String TOKEN_PREFIX = "idempotent:token:";

	/**
	 * 过期时间
	 */
	public static final Long TOKEN_EXPIRATION = 60L;

}
