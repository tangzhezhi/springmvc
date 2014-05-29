package com.baidu.yun.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CollectionRestrict {

    public int minLength() default Integer.MIN_VALUE;

    public int maxLength() default Integer.MAX_VALUE;

}
