package com.github.lake54.groupsio.api.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import javax.annotation.Nonnull;

import static org.immutables.value.Value.Style.ImplementationVisibility.PACKAGE;

@Value.Immutable(builder = false, copy = false)
@Value.Style(visibility = PACKAGE)
@JsonSerialize(as = ImmutableLogin.class)
@JsonDeserialize(as = ImmutableLogin.class)
public abstract class Login extends Model {

    @Value.Parameter
    public abstract String name();

    @Value.Parameter
    public abstract String token();

    public static Login create(@Nonnull String name, @Nonnull String color) {
        return ImmutableLogin.of(name, color);
    }
}