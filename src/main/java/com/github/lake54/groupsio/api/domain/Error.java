package com.github.lake54.groupsio.api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.lake54.groupsio.api.domain.enums.error.ErrorType;
import org.immutables.value.Value;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

import static org.immutables.value.Value.Style.ImplementationVisibility.PACKAGE;

@Value.Immutable(builder = false, copy = false)
@Value.Style(visibility = PACKAGE)
@JsonSerialize(as = ImmutableError.class)
@JsonDeserialize(as = ImmutableError.class)
public abstract class Error extends Model {

    @Value.Derived
    @JsonProperty("owner")
    public String owner() {
        return "error";
    }

    @Value.Parameter
    @JsonProperty("type")
    public abstract ErrorType type();

    @Value.Parameter
    @JsonProperty("extra")
    public abstract Optional<String> extra();

    public static Error create(ErrorType type) {
        return create(type, null);
    }

    public static Error create(@Nonnull ErrorType type, @Nullable String extra) {
        return ImmutableError.of(type, Optional.ofNullable(extra));
    }
}
