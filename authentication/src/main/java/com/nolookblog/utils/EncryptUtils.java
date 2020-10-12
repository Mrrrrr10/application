package com.nolookblog.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Mrrrrr10
 * @github https://github.com/Mrrrrr10
 * @blog https://nolookblog.com/
 * @description
 */

public class EncryptUtils {

	/**
	 * 参数升序排序连接(空字符串不参与)
	 *
	 * @param params
	 * @return
	 */
	public static String sortNotNullParamsByAsc(JSONObject params) {
		List<String> keys = new ArrayList<>(params.keySet());
		Collections.sort(keys);
		StringBuilder sb = new StringBuilder();
		for (String key : keys) {
			if (!StringUtils.isEmpty(params.getString(key))) {
				if (sb.length() > 0) {
					sb.append("&");
				}
				sb.append(key).append("=").append(params.getString(key));
			}
		}

		return sb.toString();
	}
}
