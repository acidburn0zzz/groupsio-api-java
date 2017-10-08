package com.github.lake54.groupsio.api.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import javax.annotation.Nonnull;

import static org.immutables.value.Value.Style.ImplementationVisibility.PACKAGE;

@Value.Immutable(builder = false, copy = false)
@Value.Style(visibility = PACKAGE)
@JsonSerialize(as = ImmutablePoster.class)
@JsonDeserialize(as = ImmutablePoster.class)
public abstract class Poster extends Model {

    @Value.Parameter
    public abstract String name();

    @Value.Parameter
    public abstract String userId();

    public static Poster create(@Nonnull String name, @Nonnull String userId) {
        return ImmutablePoster.of(name, userId);
    }
}