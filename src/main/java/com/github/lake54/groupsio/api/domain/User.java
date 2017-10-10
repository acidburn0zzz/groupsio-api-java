package com.github.lake54.groupsio.api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.lake54.groupsio.api.domain.enums.misc.AboutFormat;
import com.github.lake54.groupsio.api.domain.enums.misc.SortDir;
import com.github.lake54.groupsio.api.domain.enums.misc.SortType;
import com.github.lake54.groupsio.api.domain.enums.misc.TimePref;
import com.github.lake54.groupsio.api.domain.enums.misc.ViewType;
import com.github.lake54.groupsio.api.domain.enums.profile.ProfilePrivacy;
import com.github.lake54.groupsio.api.domain.enums.user.UserPerPagePref;
import com.github.lake54.groupsio.api.domain.enums.user.UserPostPref;
import com.github.lake54.groupsio.api.domain.enums.user.UserStatus;
import com.google.common.base.Preconditions;
import org.immutables.value.Value;

import static org.immutables.value.Value.Style.ImplementationVisibility.PACKAGE;

@Value.Immutable(copy = false)
@Value.Style(visibility = PACKAGE)
@JsonSerialize(as = ImmutableUser.class)
@JsonDeserialize(as = ImmutableUser.class)
public abstract class User extends Model {

    @JsonProperty("id")
    public abstract int id();

    @JsonProperty("created")
    public abstract String created();

    @JsonProperty("updated")
    public abstract String updated();

    @JsonProperty("email")
    public abstract String email();

    @JsonProperty("full_name")
    public abstract String fullName();

    @JsonProperty("user_name")
    public abstract String userName();

    @JsonProperty("timezone")
    public abstract String timezone();

    @JsonProperty("status")
    public abstract UserStatus status();

    @JsonProperty("profile_photo_id")
    public abstract String profilePhotoId();

    @JsonProperty("has_profile_photo")
    public abstract boolean hasProfilePhoto();

    @JsonProperty("post_pref")
    public abstract UserPostPref postPref();

    @JsonProperty("per_page_pref")
    public abstract UserPerPagePref perPagePref();

    @JsonProperty("allow_facebook_login")
    public abstract boolean allowFacebookLogin();

    @JsonProperty("allow_google_login")
    public abstract boolean allowGoogleLogin();

    @JsonProperty("two_factor_enabled")
    public abstract boolean twoFactorEnabled();

    @JsonProperty("recovery_codes")
    public abstract String recoveryCodes();

    @JsonProperty("dont_munge_message_id")
    public abstract boolean dontMungeMessageId();

    @JsonProperty("about_me")
    public abstract String aboutMe();

    @JsonProperty("about_format")
    public abstract AboutFormat aboutFormat();

    @JsonProperty("location")
    public abstract String location();

    @JsonProperty("website")
    public abstract String website();

    @JsonProperty("time_pref")
    public abstract TimePref timePref();

    @JsonProperty("profile_privacy")
    public abstract ProfilePrivacy profilePrivacy();

    @JsonProperty("default_message_view")
    public abstract ViewType defaultMessageView();

    @JsonProperty("topics_sort_dir")
    public abstract SortDir topicsSortDir();

    @JsonProperty("topic_sort_dir")
    public abstract SortDir topicSortDir();

    @JsonProperty("messages_sort_dir")
    public abstract SortDir messagesSortDir();

    @JsonProperty("expanded_messages_sort_dir")
    public abstract SortDir expandedMessagesSortDir();

    @JsonProperty("search_sort")
    public abstract SortType searchSort();

    @JsonProperty("search_sort_dir")
    public abstract SortDir searchSortDir();

    @Value.Default
    @JsonProperty("object")
    public String object() {
        return "user";
    }

    @Value.Check
    void check() {
        Preconditions.checkState(object().equals("user"));
    }

    public static Builder builder() {
        return ImmutableUser.builder();
    }

    public interface Builder {

        Builder id(int id);

        Builder created(String created);

        Builder updated(String updated);

        Builder email(String email);

        Builder fullName(String fullName);

        Builder userName(String userName);

        Builder timezone(String timezone);

        Builder status(UserStatus status);

        Builder profilePhotoId(String profilePhotoId);

        Builder hasProfilePhoto(boolean hasProfilePhoto);

        Builder postPref(UserPostPref postPref);

        Builder perPagePref(UserPerPagePref perPagePref);

        Builder allowFacebookLogin(boolean allowFacebookLogin);

        Builder allowGoogleLogin(boolean allowGoogleLogin);

        Builder twoFactorEnabled(boolean twoFactorEnabled);

        Builder recoveryCodes(String recoveryCodes);

        Builder dontMungeMessageId(boolean dontMungeMessageId);

        Builder aboutMe(String aboutMe);

        Builder aboutFormat(AboutFormat aboutFormat);

        Builder location(String location);

        Builder website(String website);

        Builder timePref(TimePref timePref);

        Builder profilePrivacy(ProfilePrivacy profilePrivacy);

        Builder defaultMessageView(ViewType defaultMesssageView);

        Builder topicsSortDir(SortDir topicsSortDir);

        Builder topicSortDir(SortDir topicSortDir);

        Builder messagesSortDir(SortDir messagesSortDir);

        Builder expandedMessagesSortDir(SortDir expandedMessagesSortDir);

        Builder searchSort(SortType searchSort);

        Builder searchSortDir(SortDir searchSortDir);

        User build();
    }
}