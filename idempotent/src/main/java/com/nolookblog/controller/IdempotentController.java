package com.nolookblog.controller;

import com.nolookblog.annotation.Idempotent;
import com.nolookblog.common.entity.CommonResponse;
import com.nolookblog.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mrrrrr10
 * @github https://github.com/Mrrrrr10
 * @blog https://nolookblog.com/
 * @description
 */

@RequestMapping("/idempotent")
@RestController
public class IdempotentController {

	@Autowired
	private TokenService tokenService;

	/**
	 * 获取token
	 *
	 * @return
	 */
	@GetMapping("/token")
	public CommonResponse<String> token() {
		String token = tokenService.generateToken();
		return CommonResponse.success(token);
	}

	/**
	 * 用户注册
	 *
	 * @return
	 */
	@Idempotent
	@GetMapping("/test")
	public CommonResponse test() {
		return CommonResponse.success();
	}

}
