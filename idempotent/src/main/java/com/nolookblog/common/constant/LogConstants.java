package com.nolookblog.common.constant;


import com.nolookblog.common.util.LocalDateTimeUtils;

/**
 * @author Mrrrrr10
 * @description 日志打印 常量类, 命名规范: 【异常拦截/异常捕获】当前时间: {}, 模块名: {}, 请求路径:{}, 异常信息:{}
 */

public class LogConstants {

	//////////////////////////////////// 公用前缀 ////////////////////////////////////

	/**
	 * 异常拦截
	 */
	private static final String EXCEPTION_INTERCEPT = "\n【异常拦截】当前时间: %s, 模块名: %s, 请求路径: {}, 异常信息: {}";

	/**
	 * 异常捕获前缀
	 */
	private static final String EXCEPTION_CATCH = "\n【异常捕获】当前时间: %s, 模块名: %s, 请求路径: {}, 异常信息: {}";

	//////////////////////////////////// 安全模块(SECURITY) ////////////////////////////////////

	/**
	 * 安全模块名
	 */
	private static final String SECURITY_MODULE_NAME = "SECURITY";

	/**
	 * 安全模块异常拦截
	 */
	public static final String SECURITY_MODULE_EXCEPTION_INTERCEPT =
			String.format(EXCEPTION_INTERCEPT, now(), SECURITY_MODULE_NAME);


	//////////////////////////////////// 用户模块(USER) ////////////////////////////////////
	/**
	 * 用户模块名
	 */
	private static final String USER_MODULE_NAME = "USER";

	/**
	 * 用户模块异常捕获
	 */
	public static final String USER_MODULE_EXCEPTION_CATCH =
			String.format(EXCEPTION_CATCH, now(), USER_MODULE_NAME);

	/**
	 * 获取当前时间字符串
	 *
	 * @return
	 */
	private static String now() {
		return LocalDateTimeUtils.formatNow(LocalDateTimeUtils.PATTERN_1);
	}
}
