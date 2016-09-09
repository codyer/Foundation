package com.chinaredstar.foundation.common.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by cody.yi on 2016/7/29.
 * 通用库
 */
public class CommonUtil {

    /**
     * 获取组合类类型
     */
    public static ParameterizedType getType(final Class raw, final Type... args) {
        return new ParameterizedType() {
            public Type getRawType() {
                return raw;
            }

            public Type[] getActualTypeArguments() {
                return args;
            }

            public Type getOwnerType() {
                return null;
            }
        };
    }
}
