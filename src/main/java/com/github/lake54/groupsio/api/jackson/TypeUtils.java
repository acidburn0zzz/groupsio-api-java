package com.github.lake54.groupsio.api.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.github.lake54.groupsio.api.domain.Page;

import java.util.function.Function;

/**
 * Utility class for interacting with Jackson types.
 */
public class TypeUtils {

    /**
     * A reference to the default {@link TypeFactory} instance.
     */
    private static final TypeFactory TYPE_FACTORY = TypeFactory.defaultInstance();

    /**
     * A static mapper instance to use for all JSON interaction.
     */
    private static final ObjectMapper MAPPER = new ObjectMapper()
        .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    /**
     * Converts an object to the type defined by the provided generator.
     *
     * @param object
     *      the object to convert to the new type.
     * @param generator
     *      the generator to create a new type reference.
     * @return
     *      an instance of the defined type.
     */
    public static <T> T convert(Object object, Function<TypeFactory, JavaType> generator) {
        return MAPPER.convertValue(object, generateType(generator));
    }

    /**
     * Creates a type reference to use for pagination.
     *
     * @param tClass
     *      the class to use for pagination.
     * @return
     *      a type representing a page of the provided class.
     */
    public static JavaType createPaginationType(Class<?> tClass) {
        return generateType(factory -> factory.constructParametricType(Page.class, tClass));
    }

    /**
     * Creates a type reference using the provided generator.
     *
     * @param generator
     *      the generator used to create a new type reference.
     * @return
     *      a type defined by the generating function.
     */
    public static JavaType generateType(Function<TypeFactory, JavaType> generator) {
        return generator.apply(TYPE_FACTORY);
    }
}
