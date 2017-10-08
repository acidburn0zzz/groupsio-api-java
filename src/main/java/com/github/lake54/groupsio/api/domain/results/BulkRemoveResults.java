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
@JsonSerialize(as = ImmutableBulkRemoveResults.class)
@JsonDeserialize(as = ImmutableBulkRemoveResults.class)
public abstract class BulkRemoveResults extends Model {

    public abstract int removed();

    public abstract int totalEmails();

    public abstract List<Error> errors();

    @Value.Default
    public String object() {
        return "direct_add_results";
    }

    @Value.Check
    void check() {
        Preconditions.checkState(object().equals("direct_add_results"));
    }

    public static Builder builder() {
        return ImmutableBulkRemoveResults.builder();
    }

    public interface Builder {

        Builder removed(int removed);

        Builder totalEmails(int totalEmails);

        Builder addErrors(Error error);

        Builder addErrors(Error... errors);

        Builder addAllErrors(Iterable<? extends Error> errors);

        Builder errors(Iterable<? extends Error> errors);

        BulkRemoveResults build();
    }
}
