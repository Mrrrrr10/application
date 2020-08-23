package com.nolookblog.interceptor;

import com.nolookblog.common.constant.LogConstants;
import com.nolookblog.common.em.ResponseEnum;
import com.nolookblog.common.entity.CommonResponse;
import com.nolookblog.common.exception.BusinessException;
import com.nolookblog.common.util.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;


/**
 * @author Mrrrrr10
 * @description 全局异常拦截器: @RestControllerAdvice + @ExceptionHandler 配合使用实现全局异常处理
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * 捕获Exception异常
	 *
	 * @param request
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = Exception.class)
	public CommonResponse handlerException(HttpServletRequest request, Exception e) {
		ResponseEnum responseEnum;

		// 请求路径
		String uri = request.getRequestURI();

		if (e instanceof MethodArgumentNotValidException) {
			// 参数校验异常
			responseEnum = ResponseEnum.E_100101;
			MethodArgumentNotValidException methodArgumentNotValidException =
					(MethodArgumentNotValidException) e;
			responseEnum.setMessage((methodArgumentNotValidException.getBindingResult()
					.getFieldError().getDefaultMessage()));
		} else if (e instanceof BusinessException) {
			// 自定义异常
			BusinessException businessException = (BusinessException) e;
			responseEnum = businessException.getResponseEnum();
		} else if (e instanceof NoHandlerFoundException) {
			// 路径异常
			responseEnum = ResponseEnum.C_NOT_FOUND;
		} else if (e instanceof HttpRequestMethodNotSupportedException) {
			// 路径异常
			responseEnum = ResponseEnum.E_100104;
		} else {
			// 未知异常
			responseEnum = ResponseEnum.E_999999;
			log.error(LogConstants.SECURITY_MODULE_EXCEPTION_INTERCEPT, uri, LogUtils.formatExceptionMsg(e));

			return CommonResponse.failure(responseEnum);
		}

		String message = responseEnum.getMessage();
		log.error(LogConstants.SECURITY_MODULE_EXCEPTION_INTERCEPT, uri, message);

		return CommonResponse.failure(responseEnum);
	}

}
