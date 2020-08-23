package com.nolookblog.handler;

import com.nolookblog.annotation.Idempotent;
import com.nolookblog.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Mrrrrr10
 * @github https://github.com/Mrrrrr10
 * @blog https://nolookblog.com/
 * @description
 */

@Aspect
@Component
@Slf4j
public class IdempotentHandler {

	@Autowired
	private TokenService tokenService;

	@Autowired
	private HttpServletRequest request;

	/**
	 * 切入点, 对注解进行拦截
	 */
	@Pointcut("@annotation(com.nolookblog.annotation.Idempotent)")
	public void idempotent() {

	}

	/**
	 * 环绕目标对象方法执行, 拦截到了做什么
	 *
	 * @param proceedingJoinPoint
	 * @param idempotent
	 * @return
	 * @throws Throwable
	 */
	@Around("@annotation(idempotent)")
	public Object around(ProceedingJoinPoint proceedingJoinPoint, Idempotent idempotent) throws Throwable {
		if (log.isDebugEnabled()) {
			log.info("IdempotentHandler[api接口幂等]开始执行幂等操作");
		}

		// 方法标记对象
		Signature signature = proceedingJoinPoint.getSignature();
		if (!(signature instanceof MethodSignature)) {
			throw new IllegalArgumentException("the Annotation @Idempotent must used on method!");
		}

		// 获取注解参数

		// 是否需要接口幂等
		boolean isIdempotent = idempotent.isIdempotent();
		if (isIdempotent) {
			tokenService.verifyToken(request);
		}

		return proceedingJoinPoint.proceed();
	}

}
