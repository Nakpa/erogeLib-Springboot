package com.library.eroge.erogelib.config.exceptionCatchUtils.annotation;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface ResponseResult {
}
