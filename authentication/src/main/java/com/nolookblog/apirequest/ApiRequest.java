package com.nolookblog.apirequest;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Mrrrrr10
 * @github https://github.com/Mrrrrr10
 * @blog https://nolookblog.com/
 * @description
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApiRequest {

	private String baseUrl;
	private String token;
	private String appId;
	private long timestamp;

	/**
	 * 生成ApiRequest
	 *
	 * @param url
	 * @return
	 */
	public static ApiRequest buildFromUrl(String url) {
		JSONObject params = parse(url);

		return new ApiRequest(
				params.getString("baseUrl"),
				params.getString("token"),
				params.getString("appId"),
				params.getLong("timestamp")

		);
	}

	/**
	 * 解析url
	 *
	 * @param url
	 * @return
	 */
	private static JSONObject parse(String url) {
		JSONObject jsonObject = new JSONObject();

		String[] urlParamsArr = StringUtils.split(url, "?");

		if (urlParamsArr.length == 0) {
			throw new RuntimeException("解析出错");
		}

		String baseUrl = urlParamsArr[0];
		jsonObject.put("baseUrl", baseUrl);

		String params = urlParamsArr[1];
		String[] paramsArr = StringUtils.split(params, "&");

		for (String param : paramsArr) {
			if (param.startsWith("token")) {
				String[] tokenArr = param.split("=");
				if (tokenArr.length == 0) {
					throw new RuntimeException("解析出错");
				}

				String token = tokenArr[1];
				jsonObject.put("token", token);
			}

			if (param.startsWith("timestamp")) {
				String[] timestampArr = param.split("=");
				if (timestampArr.length == 0) {
					throw new RuntimeException("解析出错");
				}

				String timestamp = timestampArr[1];
				jsonObject.put("timestamp", timestamp);
			}

			if (param.startsWith("appId")) {
				String[] appIdArr = param.split("=");
				if (appIdArr.length == 0) {
					throw new RuntimeException("解析出错");
				}

				String appId = appIdArr[1];
				jsonObject.put("appId", appId);
			}
		}

		return jsonObject;
	}

	public static void main(String[] args) {
		String url = "https://www.baidu.com?token=cf3ec0cf42cf64a304464ada680292e0&timestamp=1602473688024&appId=202010041039151000";
		System.out.println(ApiRequest.buildFromUrl(url));
	}

}
