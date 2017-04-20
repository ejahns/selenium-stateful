package com.github.ejahns;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface State {

	String key() default "";

	Purpose[] purpose() default {Purpose.DEFAULT};

	Class<? extends WebElementFunction> function() default WebElementTextFunction.class;

	enum Purpose {
		DEFAULT,
		PRESENCE,
		CUSTOM
	}
}
