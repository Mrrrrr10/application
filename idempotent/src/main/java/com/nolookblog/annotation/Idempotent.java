package com.nolookblog.annotation;

import java.lang.annotation.*;

/**
 * @author Mrrrrr10
 * @github https://github.com/Mrrrrr10
 * @blog https://nolookblog.com/
 * @description 幂等注解
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Idempotent {

	/**
	 * 是否需要接口幂等校验
	 *
	 * @return
	 */
	boolean isIdempotent() default true;

}
