package com.nolookblog.token;

import com.alibaba.fastjson.JSONObject;
import com.nolookblog.utils.EncryptUtils;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.Date;

/**
 * @author Mrrrrr10
 * @github https://github.com/Mrrrrr10
 * @blog https://nolookblog.com/
 * @description token校验模型
 */

@Getter
@ToString
public class AuthToken {

	private static final long DEFAULT_EXPIRED_TIME_INTERVAL = 60 * 1000L;
	private String token;
	private long createTime;
	private String baseUrl;
	private long expiredTimeInterval = DEFAULT_EXPIRED_TIME_INTERVAL;
	private static final String KEY = "AKIDz8krbsJ5yKBZQpn74WFkmLPx3gnPhESA";

	public AuthToken(String token, long createTime, String baseUrl, long expiredTimeInterval) {
		this.token = token;
		this.createTime = createTime;
		this.baseUrl = baseUrl;
		this.expiredTimeInterval = expiredTimeInterval;
	}

	public AuthToken(String token, long createTime, String baseUrl) {
		this.token = token;
		this.createTime = createTime;
		this.baseUrl = baseUrl;
	}

	/**
	 * 生成token
	 *
	 * @param baseUrl
	 * @param appId
	 * @param password
	 * @param timestamp
	 * @return
	 */
	public static AuthToken create(String baseUrl, String appId, String password, long timestamp) {
		JSONObject params = new JSONObject();
		params.put("baseUrl", baseUrl);
		params.put("appId", appId);
		params.put("password", password);
		params.put("timestamp", timestamp);

		String content = EncryptUtils.sortNotNullParamsByAsc(params);
		content += MessageFormat.format("&key={0}", KEY);

		String token = DigestUtils.md5Hex(content);

		return new AuthToken(token, timestamp, baseUrl);

	}

	/**
	 * 获取token
	 *
	 * @return
	 */
	public String getToken() {
		return this.token;
	}

	/**
	 * 判断当前请求是否超过有效时间窗口
	 *
	 * @return
	 */
	public boolean isExpired() {
		return this.createTime + this.expiredTimeInterval <= System.currentTimeMillis();
	}

	/**
	 * 校验token
	 *
	 * @param authToken
	 * @return
	 */
	public boolean match(AuthToken authToken) {
		return StringUtils.equals(this.token, authToken.getToken());
	}

	public static void main(String[] args) {
		System.out.println(new Date());
		AuthToken token = AuthToken.create("https://www.baidu.com",
				"202010041039151000", "123456", System.currentTimeMillis());
		System.out.println(token);
	}

}
