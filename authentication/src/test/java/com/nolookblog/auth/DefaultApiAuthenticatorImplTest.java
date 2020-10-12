package com.nolookblog.auth;

/**
 * @author Mrrrrr10
 * @github https://github.com/Mrrrrr10
 * @blog https://nolookblog.com/
 * @description
 */

public class DefaultApiAuthenticatorImplTest {

	public static void main(String[] args) {
		String url = "https://www.baidu.com?" +
				"token=98897141f532d3c866649843b26d5ced&" +
				"timestamp=1602487604149&" +
				"appId=202010041039151000";

		DefaultApiAuthenticatorImpl defaultApiAuthenticator = new DefaultApiAuthenticatorImpl();
		defaultApiAuthenticator.auth(url);
	}

}
