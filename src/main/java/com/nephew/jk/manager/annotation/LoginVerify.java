package com.nephew.jk.manager.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * https://www.cnblogs.com/liangweiping/p/3837332.html
 * https://www.jianshu.com/p/a7bedc771204
 */
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.PACKAGE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginVerify {

    String value() default "login";

    boolean isVerify() default true;

}
