package com.nolookblog.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Mrrrrr10
 * @github https://github.com/Mrrrrr10
 * @blog https://nolookblog.com/
 * @description
 */

public interface TokenService {

	/**
	 * 生产token
	 *
	 * @return
	 */
	String generateToken();

	/**
	 * 校验token
	 *
	 * @param request
	 * @return
	 */
	boolean verifyToken(HttpServletRequest request);
}
