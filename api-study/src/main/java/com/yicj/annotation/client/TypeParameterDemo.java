package com.yicj.annotation.client;

import com.yicj.annotation.anno.TypeParameterAnnotation;

public class TypeParameterDemo<@TypeParameterAnnotation T> {
    public <@TypeParameterAnnotation U> T foo(T t) {
        return null;
    }
}

