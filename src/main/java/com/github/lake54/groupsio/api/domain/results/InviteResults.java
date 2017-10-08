package com.github.lake54.groupsio.api.domain.results;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.lake54.groupsio.api.domain.Model;
import com.github.lake54.groupsio.api.domain.Error;
import com.google.common.base.Preconditions;
import org.immutables.value.Value;

import java.util.List;

import static org.immutables.value.Value.Style.ImplementationVisibility.PACKAGE;

@Value.Immutable(copy = false)
@Value.Style(visibility = PACKAGE)
@JsonSerialize(as = ImmutableInviteResults.class)
@JsonDeserialize(as = ImmutableInviteResults.class)
public abstract class InviteResults extends Model {

    public abstract int totalEmails();

    public abstract List<Error> errors();

    public abstract List<String> invited();

    @Value.Default
    public String object() {
        return "invite_results";
    }

    @Value.Check
    void check() {
        Preconditions.checkState(object().equals("invite_results"));
    }

    public static Builder builder() {
        return ImmutableInviteResults.builder();
    }

    public interface Builder {

        Builder totalEmails(int totalEmails);

        Builder addErrors(Error error);

        Builder addErrors(Error... errors);

        Builder addAllErrors(Iterable<? extends Error> errors);

        Builder errors(Iterable<? extends Error> errors);

        Builder addInvited(String invited);

        Builder addInvited(String... invited);

        Builder addAllInvited(Iterable<String> invited);

        Builder invited(Iterable<String> invited);

        InviteResults build();
    }
}