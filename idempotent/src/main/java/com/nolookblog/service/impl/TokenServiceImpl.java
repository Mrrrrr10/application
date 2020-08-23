package com.nolookblog.service.impl;

import com.nolookblog.common.constant.RedisConstants;
import com.nolookblog.common.em.ResponseEnum;
import com.nolookblog.common.exception.BusinessException;
import com.nolookblog.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Mrrrrr10
 * @github https://github.com/Mrrrrr10
 * @blog https://nolookblog.com/
 * @description
 */

@Service
public class TokenServiceImpl implements TokenService {

	@Autowired
	private RedisTemplate redisTemplate;

	private static final String REQUEST_TOKEN_NAME = "RequestToken";

	/**
	 * 生产token
	 *
	 * @return
	 */
	@Override
	public String generateToken() {
		String token = UUID.randomUUID().toString().replace("-", "");
		redisTemplate.opsForValue().set(RedisConstants.TOKEN_PREFIX + token,
				token, RedisConstants.TOKEN_EXPIRATION, TimeUnit.SECONDS);

		return token;
	}

	/**
	 * 校验token
	 *
	 * @param request
	 * @return
	 */
	@Override
	public boolean verifyToken(HttpServletRequest request) {
		String token = request.getHeader(REQUEST_TOKEN_NAME);
		String tokenKey = RedisConstants.TOKEN_PREFIX + token;

		if (StringUtils.isBlank(token)) {
			throw new BusinessException(ResponseEnum.E_100116);
		}

		if (!redisTemplate.hasKey(tokenKey)) {
			throw new BusinessException(ResponseEnum.E_100117);
		}

		String cacheToken = (String) redisTemplate.opsForValue().get(tokenKey);
		if (!StringUtils.equals(cacheToken, token)) {
			throw new BusinessException(ResponseEnum.E_100118);
		}

		Boolean isDelete = redisTemplate.delete(tokenKey);
		if (!isDelete) {
			throw new BusinessException(ResponseEnum.E_100119);
		}

		return true;
	}
}
