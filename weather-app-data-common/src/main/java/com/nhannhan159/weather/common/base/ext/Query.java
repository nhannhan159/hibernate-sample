package com.nhannhan159.weather.common.base.ext;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author tien.tan
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Query {
    Type type() default Type.EQUAL;
    String name() default "";

    enum Type {
        LIKE,
        LIKE_IGNORE_CASE,
        IN,
        EQUAL,
        EQUAL_IGNORE_CASE,
        GREATER,
        GREATER_EQUAL,
        LESS,
        LESS_EQUAL,
        NOT_EQUAL,
        NOT_LIKE,
        NOT_IN,
        NULL
    }
}
