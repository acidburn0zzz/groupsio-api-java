package com.github.lake54.groupsio.api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.Preconditions;
import org.immutables.value.Value;

import static org.immutables.value.Value.Style.ImplementationVisibility.PACKAGE;

@Value.Immutable(copy = false)
@Value.Style(visibility = PACKAGE)
@JsonSerialize(as = ImmutableSubscription.class)
@JsonDeserialize(as = ImmutableSubscription.class)
public abstract class Subscription extends SubscriptionBase {

    @Value.Default
    @JsonProperty("object")
    public String object() {
        return "subscription";
    }

    @Value.Check
    void check() {
        Preconditions.checkState(object().equals("subscription"));
    }

    public static Builder builder() {
        return ImmutableSubscription.builder();
    }

    public interface Builder extends SubscriptionBase.Builder {

    }
}