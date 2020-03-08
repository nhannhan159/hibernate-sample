package com.nhannhan159.base.application;

import com.google.common.base.Strings;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * @author tien.tan
 */
public final class Assert {

    private Assert() {}

    public static void isTrue(boolean expression, String msg) {
        isTrue(expression, ErrorCode.PARAMETER_ERROR, msg);
    }

    public static void isTrue(boolean expression, ErrorCode error, String msg) {
        if (!expression) {
            throw new AppException(error, msg);
        }
    }

    public static void isNull(Object object, String msg) {
        isNull(object, ErrorCode.PARAMETER_ERROR, msg);
    }

    public static void isNull(Object object, ErrorCode error, String msg) {
        if (Objects.nonNull(object)) {
            throw new AppException(error, msg);
        }
    }

    public static void notNull(Object object, String msg) {
        notNull(object, ErrorCode.PARAMETER_ERROR, msg);
    }

    public static void notNull(Object object, ErrorCode error, String msg) {
        if (Objects.isNull(object)) {
            throw new AppException(error, msg);
        }
    }

    public static void notBlank(String text, String msg) {
        notBlank(text, ErrorCode.PARAMETER_ERROR, msg);
    }

    public static void notBlank(String text, ErrorCode error, String msg) {
        if (Strings.isNullOrEmpty(text)) {
            throw new AppException(error, msg);
        }
    }

    public static void notEmpty(Object[] array, String msg) {
        notEmpty(array, ErrorCode.PARAMETER_ERROR, msg);
    }

    public static void notEmpty(Object[] array, ErrorCode error, String msg) {
        if (Objects.isNull(array) || array.length == 0) {
            throw new AppException(error, msg);
        }
    }

    public static void notEmpty(Collection<?> collection, String msg) {
        notEmpty(collection, ErrorCode.PARAMETER_ERROR, msg);
    }

    public static void notEmpty(Collection<?> collection, ErrorCode error, String msg) {
        if (Objects.isNull(collection) || collection.isEmpty()) {
            throw new AppException(error, msg);
        }
    }

    public static void notEmpty(Map<?, ?> map, String msg) {
        notEmpty(map, ErrorCode.PARAMETER_ERROR, msg);
    }

    public static void notEmpty(Map<?, ?> map, ErrorCode error, String msg) {
        if (Objects.isNull(map) || map.isEmpty()) {
            throw new AppException(error, msg);
        }
    }
}
