package com.github.lake54.groupsio.api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.Preconditions;
import org.immutables.value.Value;

import java.util.List;

import static org.immutables.value.Value.Style.ImplementationVisibility.PACKAGE;

@Value.Immutable(copy = false)
@Value.Style(visibility = PACKAGE)
@JsonSerialize(as = ImmutablePage.class)
@JsonDeserialize(as = ImmutablePage.class)
public abstract class Page<T> extends Model {

    @JsonProperty("total_count")
    public abstract int totalCount();

    @JsonProperty("start_item")
    public abstract int startItem();

    @JsonProperty("end_item")
    public abstract int endItem();

    @JsonProperty("has_more")
    public abstract boolean hasMore();

    @JsonProperty("next_page_token")
    public abstract int nextPageToken();

    @JsonProperty("data")
    public abstract List<T> data();

    @Value.Default
    @JsonProperty("object")
    public String object() {
        return "list";
    }

    @Value.Check
    void check() {
        Preconditions.checkState(object().equals("list"));
    }

    public static <T> Builder<T> builder() {
        return ImmutablePage.builder();
    }

    public interface Builder<T> {

        Builder totalCount(int totalCount);

        Builder startItem(int startItem);

        Builder endItem(int endItem);

        Builder hasMore(boolean hasMore);

        Builder nextPageToken(int nextPageToken);

        Builder addData(T data);

        Builder addData(T... data);

        Builder addAllData(Iterable<? extends T> data);

        Builder data(Iterable<? extends T> data);

        Page<T> build();
    }
}

