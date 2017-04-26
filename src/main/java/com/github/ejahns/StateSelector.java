package com.github.ejahns;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface StateSelector {

	String key() default "";

	Purpose purpose() default Purpose.METHOD;

	Class<? extends WebElementFunction> function() default WebElementTextFunction.class;

	enum Purpose {
		METHOD,
		PRESENCE,
	}
}
