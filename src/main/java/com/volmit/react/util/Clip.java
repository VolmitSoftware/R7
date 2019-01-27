package com.volmit.react.util;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(FIELD)
public @interface Clip
{
	public double min() default Double.MIN_VALUE;

	public double max() default Double.MAX_VALUE;
}
