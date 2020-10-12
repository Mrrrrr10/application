package com.nolookblog.auth;

import com.nolookblog.apirequest.ApiRequest;

/**
 * @author Mrrrrr10
 * @github https://github.com/Mrrrrr10
 * @blog https://nolookblog.com/
 * @description
 */

public interface Authenticator {

	/**
	 * 认证
	 *
	 * @param url
	 */
	void auth(String url);

	/**
	 * 认证
	 *
	 * @param apiRequest
	 */
	void auth(ApiRequest apiRequest);
}
