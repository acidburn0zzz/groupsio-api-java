package com.github.lake54.groupsio.api.jackson;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.util.function.Function;

public class TypeUtils {

    private static final TypeFactory TYPE_FACTORY = TypeFactory.defaultInstance();

    public static JavaType generateType(Function<TypeFactory, JavaType> generator) {
        return generator.apply(TYPE_FACTORY);
    }
}
