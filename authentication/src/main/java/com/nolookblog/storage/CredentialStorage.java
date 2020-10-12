package com.nolookblog.storage;

/**
 * @author Mrrrrr10
 * @github https://github.com/Mrrrrr10
 * @blog https://nolookblog.com/
 * @description
 */

public interface CredentialStorage {

	/**
	 * 	根据appId获取密码
	 *
	 * @param appId
	 * @return
	 */
	String getPasswordByAppId(String appId);
}
