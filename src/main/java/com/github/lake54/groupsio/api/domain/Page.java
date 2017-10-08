package com.github.lake54.groupsio.api.domain;

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

    public abstract int totalCount();

    public abstract int startItem();

    public abstract int endItem();

    public abstract boolean hasMore();

    public abstract int nextPageToken();

    public abstract List<T> data();

    @Value.Default
    public String object() {
        return "page";
    }

    @Value.Check
    void check() {
        Preconditions.checkState(object().equals("page"));
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

