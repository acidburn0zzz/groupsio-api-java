package com.github.lake54.groupsio.api.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.List;

import static org.immutables.value.Value.Style.ImplementationVisibility.PACKAGE;

@Value.Immutable(copy = false)
@Value.Style(visibility = PACKAGE)
@JsonSerialize(as = ImmutableTopic.class)
@JsonDeserialize(as = ImmutableTopic.class)
public abstract class Topic extends Model {

    public abstract int id();

    public abstract String subject();

    public abstract String snippet();

    public abstract Poster poster();

    public abstract int numMsgs();

    public abstract String mostRecentMessage();

    public abstract boolean isSticky();

    public abstract boolean isModerated();

    public abstract boolean isClosed();

    public abstract List<Hashtag> hashtags();

    public static Builder builder() {
        return ImmutableTopic.builder();
    }

    public interface Builder {

        Builder id(int id);

        Builder subject(String subject);

        Builder snippet(String snippet);

        Builder poster(Poster poster);

        Builder numMsgs(int numMsgs);

        Builder mostRecentMessage(String mostRecentMessage);

        Builder isSticky(boolean isSticky);

        Builder isModerated(boolean isModerated);

        Builder isClosed(boolean isClosed);

        Builder addHashtags(Hashtag hashtag);

        Builder addHashtags(Hashtag... hashtags);

        Builder addAllHashtags(Iterable<? extends Hashtag> hashtags);

        Builder hashtags(Iterable<? extends Hashtag> hashtags);

        Topic build();
    }
}