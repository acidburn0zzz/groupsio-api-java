package com.github.lake54.groupsio.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

import static org.immutables.value.Value.Style.ImplementationVisibility.PACKAGE;

@Value.Immutable(builder = false, copy = false)
@Value.Style(visibility = PACKAGE)
@JsonSerialize(as = ImmutableError.class)
@JsonDeserialize(as = ImmutableError.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Error {

    @Value.Derived
    public String owner() {
        return "error";
    }

    @Value.Parameter
    public abstract Type type();

    @Value.Parameter
    public abstract Optional<String> extra();

    public static Error create(Type type) {
        return create(type, null);
    }

    public static Error create(@Nonnull Type type, @Nullable String extra) {
        return ImmutableError.of(type, Optional.ofNullable(extra));
    }

    public enum Type {
        UNKNOWN, UNAUTHORIZED, BAD_REQUEST, AUTHENTICATION,
        EXPIRED, RATE_LIMIT, INADEQUATE_PERMISSIONS,
        INVALID_VALUE, SERVER;
    }
}
