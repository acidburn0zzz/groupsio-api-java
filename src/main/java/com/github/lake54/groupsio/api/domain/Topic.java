package com.github.lake54.groupsio.api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("id")
    public abstract int id();

    @JsonProperty("subject")
    public abstract String subject();

    @JsonProperty("snippet")
    public abstract String snippet();

    @JsonProperty("poster")
    public abstract Poster poster();

    @JsonProperty("num_msgs")
    public abstract int numMsgs();

    @JsonProperty("most_recent_message")
    public abstract String mostRecentMessage();

    @JsonProperty("is_sticky")
    public abstract boolean isSticky();

    @JsonProperty("is_moderated")
    public abstract boolean isModerated();

    @JsonProperty("is_closed")
    public abstract boolean isClosed();

    @JsonProperty("hashtags")
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