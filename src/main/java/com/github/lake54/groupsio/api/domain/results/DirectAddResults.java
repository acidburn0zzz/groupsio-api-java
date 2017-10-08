package com.github.lake54.groupsio.api.domain.results;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.lake54.groupsio.api.domain.Model;
import com.github.lake54.groupsio.api.domain.Error;
import com.github.lake54.groupsio.api.domain.Subscription;
import com.google.common.base.Preconditions;
import org.immutables.value.Value;

import java.util.List;

import static org.immutables.value.Value.Style.ImplementationVisibility.PACKAGE;

@Value.Immutable(copy = false)
@Value.Style(visibility = PACKAGE)
@JsonSerialize(as = ImmutableDirectAddResults.class)
@JsonDeserialize(as = ImmutableDirectAddResults.class)
public abstract class DirectAddResults extends Model {

    public abstract int totalEmails();

    public abstract List<Error> errors();

    public abstract List<Subscription> addedMembers();

    @Value.Default
    public String object() {
        return "bulk_remove_results";
    }

    @Value.Check
    void check() {
        Preconditions.checkState(object().equals("bulk_remove_results"));
    }

    public static Builder builder() {
        return ImmutableDirectAddResults.builder();
    }

    public interface Builder {

        Builder totalEmails(int totalEmails);

        Builder addErrors(Error error);

        Builder addErrors(Error... errors);

        Builder addAllErrors(Iterable<? extends Error> errors);

        Builder errors(Iterable<? extends Error> errors);

        Builder addAddedMembers(Subscription member);

        Builder addAddedMembers(Subscription... member);

        Builder addAllAddedMembers(Iterable<? extends Subscription> member);

        Builder addedMembers(Iterable<? extends Subscription> member);

        DirectAddResults build();
    }
}