package com.github.lake54.groupsio.api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.Preconditions;
import org.immutables.value.Value;

import static org.immutables.value.Value.Style.ImplementationVisibility.PACKAGE;

@Value.Immutable(copy = false)
@Value.Style(visibility = PACKAGE)
@JsonSerialize(as = ImmutableSubscriptionPlus.class)
@JsonDeserialize(as = ImmutableSubscriptionPlus.class)
public abstract class SubscriptionPlus extends SubscriptionBase {

    @JsonProperty("num_subs")
    public abstract int numSubs();

    @JsonProperty("pending_messages")
    public abstract int pendingMessages();

    @JsonProperty("pending_subs")
    public abstract int pendingSubs();

    @JsonProperty("most_recent_message")
    public abstract String mostRecentMessage();

    @Value.Default
    public String object() {
        return "subscription_plus";
    }

    @Value.Check
    void check() {
        Preconditions.checkState(object().equals("subscription_plus"));
    }

    public static Builder builder() {
        return ImmutableSubscriptionPlus.builder();
    }

    public interface Builder extends Subscription.Builder {

        Builder numSubs(int numSubs);

        Builder pendingMessages(int pendingMessages);

        Builder pendingSubs(int pendingSubs);

        Builder mostRecentMessage(String mostRecentMessage);

        SubscriptionPlus build();
    }
}