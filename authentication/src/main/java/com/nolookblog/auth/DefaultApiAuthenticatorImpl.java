package com.nolookblog.auth;

import com.nolookblog.apirequest.ApiRequest;
import com.nolookblog.storage.CredentialStorage;
import com.nolookblog.storage.MySqlCredentialStorage;
import com.nolookblog.token.AuthToken;

/**
 * @author Mrrrrr10
 * @github https://github.com/Mrrrrr10
 * @blog https://nolookblog.com/
 * @description
 */

public class DefaultApiAuthenticatorImpl implements Authenticator {

	private CredentialStorage credentialStorage;

	public DefaultApiAuthenticatorImpl() {
		this.credentialStorage = new MySqlCredentialStorage();
	}

	public DefaultApiAuthenticatorImpl(CredentialStorage credentialStorage) {
		this.credentialStorage = credentialStorage;
	}

	/**
	 * 认证
	 *
	 * @param url
	 */
	@Override
	public void auth(String url) {
		ApiRequest apiRequest = ApiRequest.buildFromUrl(url);
		auth(apiRequest);
	}

	/**
	 * 认证
	 *
	 * @param apiRequest
	 */
	@Override
	public void auth(ApiRequest apiRequest) {
		String appId = apiRequest.getAppId();
		String token = apiRequest.getToken();
		long timestamp = apiRequest.getTimestamp();
		String baseUrl = apiRequest.getBaseUrl();

		AuthToken clientAuthToken = new AuthToken(token, timestamp, baseUrl);
		if (clientAuthToken.isExpired()) {
			throw new RuntimeException("Token is expired.");
		}

		String password = credentialStorage.getPasswordByAppId(appId);
		AuthToken serverAuthToken = AuthToken.create(baseUrl, appId, password, timestamp);

		if (!serverAuthToken.match(clientAuthToken)) {
			throw new RuntimeException("Token verification failed.");
		}

		System.out.println("Auth pass.");
	}
}



