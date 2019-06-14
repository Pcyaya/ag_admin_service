package com.runyee.agdhome.annotation;


import com.runyee.agdhome.enums.Skip;

import java.lang.annotation.*;

/**
 * url 分类注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface UrlSkip {
	String value() default "";
	Skip skip() default Skip.NO;
}