package com.yicj.annotation.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PersonInfoAnnotation {
    // 名字
    String name();
    // 年龄
    int age() default 19;
    // 性别
    String gender() default "男";
    // 开发语言
    String[] language();
}
