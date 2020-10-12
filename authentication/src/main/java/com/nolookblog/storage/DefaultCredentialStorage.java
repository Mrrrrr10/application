package com.nolookblog.storage;

/**
 * @author Mrrrrr10
 * @github https://github.com/Mrrrrr10
 * @blog https://nolookblog.com/
 * @description
 */

public class DefaultCredentialStorage implements CredentialStorage {

	/**
	 * 根据appId获取密码
	 *
	 * @param appId
	 * @return
	 */
	@Override
	public String getPasswordByAppId(String appId) {
		return "123456";
	}
}
