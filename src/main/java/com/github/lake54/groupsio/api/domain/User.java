package com.github.lake54.groupsio.api.domain;

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

    public abstract int id();

    public abstract String created();

    public abstract String updated();

    public abstract String email();

    public abstract String fullName();

    public abstract String userName();

    public abstract String timezone();

    public abstract UserStatus status();

    public abstract String profilePhotoId();

    public abstract boolean hasProfilePhoto();

    public abstract UserPostPref postPref();

    public abstract UserPerPagePref perPagePref();

    public abstract boolean allowFacebookLogin();

    public abstract boolean allowGoogleLogin();

    public abstract boolean twoFactorEnabled();

    public abstract String recoveryCodes();

    public abstract boolean dontMungeMessageId();

    public abstract String aboutMe();

    public abstract AboutFormat aboutFormat();

    public abstract String location();

    public abstract String website();

    public abstract TimePref timePref();

    public abstract ProfilePrivacy profilePrivacy();

    public abstract ViewType defaultMessageView();

    public abstract SortDir topicsSortDir();

    public abstract SortDir topicSortDir();

    public abstract SortDir messagesSortDir();

    public abstract SortDir expandedMessagesSortDir();

    public abstract SortType searchSort();

    public abstract SortDir searchSortDir();

    @Value.Default
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