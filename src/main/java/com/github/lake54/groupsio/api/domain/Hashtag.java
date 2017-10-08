package com.github.lake54.groupsio.api.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import javax.annotation.Nonnull;

import static org.immutables.value.Value.Style.ImplementationVisibility.PACKAGE;

@Value.Immutable(builder = false, copy = false)
@Value.Style(visibility = PACKAGE)
@JsonSerialize(as = ImmutableHashtag.class)
@JsonDeserialize(as = ImmutableHashtag.class)
public abstract class Hashtag extends Model {

    @Value.Parameter
    public abstract Integer id();

    @Value.Parameter
    public abstract String name();

    @Value.Parameter
    public abstract String color();

    public static Hashtag create(int id, @Nonnull String name, @Nonnull String color) {
        return ImmutableHashtag.of(id, name, color);
    }
}