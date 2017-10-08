package com.github.lake54.groupsio.api.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.lake54.groupsio.api.domain.enums.attachment.MaxAttachmentSize;
import com.github.lake54.groupsio.api.domain.enums.email.EmailDelivery;
import com.github.lake54.groupsio.api.domain.enums.message.MessageSelection;
import com.github.lake54.groupsio.api.domain.enums.profile.ProfilePrivacy;
import com.github.lake54.groupsio.api.domain.enums.subscription.SubscriptionModStatus;
import com.github.lake54.groupsio.api.domain.enums.subscription.SubscriptionNotify;
import com.github.lake54.groupsio.api.domain.enums.subscription.SubscriptionOwnerMessageNotify;
import com.github.lake54.groupsio.api.domain.enums.subscription.SubscriptionPermission;
import com.github.lake54.groupsio.api.domain.enums.subscription.SubscriptionPostStatus;
import com.github.lake54.groupsio.api.domain.enums.subscription.SubscriptionStatus;
import com.github.lake54.groupsio.api.domain.enums.user.UserStatus;
import com.google.common.base.Preconditions;
import org.immutables.value.Value;

import java.util.Optional;

import static org.immutables.value.Value.Style.ImplementationVisibility.PACKAGE;

@Value.Immutable(copy = false)
@Value.Style(visibility = PACKAGE)
@JsonSerialize(as = ImmutableSubscription.class)
@JsonDeserialize(as = ImmutableSubscription.class)
public abstract class Subscription extends Model {

    public abstract int id();

    public abstract String created();

    public abstract String updated();

    public abstract int userId();

    public abstract int groupId();

    public abstract SubscriptionStatus status();

    public abstract SubscriptionPostStatus postStatus();

    public abstract Optional<EmailDelivery> emailDelivery();

    public abstract Optional<MessageSelection> messageSelection();

    public abstract Optional<Boolean> autoFollowReplies();

    public abstract Optional<MaxAttachmentSize> maxAttachmentSize();

    public abstract int approvedPosts();

    public abstract SubscriptionModStatus modStatus();

    public abstract Optional<SubscriptionNotify> pendingMsgNotify();

    public abstract Optional<SubscriptionNotify> pendingSubNotify();

    public abstract Optional<SubscriptionNotify> subNotify();

    public abstract Optional<SubscriptionNotify> storageNotify();

    public abstract Optional<SubscriptionNotify> subGroupNotify();

    public abstract Optional<SubscriptionNotify> messageReportNotify();

    public abstract SubscriptionPermission modPermissions();

    public abstract Optional<SubscriptionOwnerMessageNotify> ownerMsgNotify();

    public abstract String email();

    public abstract UserStatus userStatus();

    public abstract String userName();

    public abstract String timezone();

    public abstract String fullName();

    public abstract String aboutMe();

    public abstract String aboutFormat();

    public abstract String location();

    public abstract String website();

    public abstract String profilePhotoId();

    public abstract ProfilePrivacy profilePrivacy();

    public abstract boolean dontMungeMessageId();

    public abstract String moderatorNotes();

    public abstract String moderatorNotesUpdated();

    public abstract boolean useSignature();

    public abstract boolean useSignatureEmail();

    @Value.Default
    public String object() {
        return "subscription";
    }

    @Value.Check
    void check() {
        Preconditions.checkState(object().equals("subscription"));
    }

    public static Builder builder() {
        return ImmutableSubscription.builder();
    }

    public interface Builder {

        Builder id(int id);

        Builder created(String created);

        Builder updated(String updated);

        Builder userId(int userId);

        Builder groupId(int groupId);

        Builder status(SubscriptionStatus status);

        Builder postStatus(SubscriptionPostStatus postStatus);

        Builder emailDelivery(EmailDelivery emailDelivery);

        Builder messageSelection(MessageSelection messageSelection);

        Builder autoFollowReplies(boolean autoFollowReplies);

        Builder maxAttachmentSize(MaxAttachmentSize maxAttachmentSize);

        Builder approvedPosts(int approvedPosts);

        Builder modStatus(SubscriptionModStatus modStatus);

        Builder pendingMsgNotify(SubscriptionNotify pendingMsgNotify);

        Builder pendingSubNotify(SubscriptionNotify pendingSubNotify);

        Builder subNotify(SubscriptionNotify subNotify);

        Builder storageNotify(SubscriptionNotify storageNotify);

        Builder subGroupNotify(SubscriptionNotify subGroupNotify);

        Builder messageReportNotify(SubscriptionNotify messageReportNotify);

        Builder modPermissions(SubscriptionPermission modPermissions);

        Builder ownerMsgNotify(SubscriptionOwnerMessageNotify ownerMsgNotify);

        Builder email(String email);

        Builder userStatus(UserStatus userStatus);

        Builder userName(String userName);

        Builder timezone(String timezone);

        Builder fullName(String fullName);

        Builder aboutMe(String aboutMe);

        Builder aboutFormat(String aboutFormat);

        Builder location(String location);

        Builder website(String website);

        Builder profilePhotoId(String profilePhotoId);

        Builder profilePrivacy(ProfilePrivacy profilePrivacy);

        Builder dontMungeMessageId(boolean dontMungeMessageId);

        Builder moderatorNotes(String moderatorNotes);

        Builder moderatorNotesUpdated(String moderatorNotesUpdated);

        Builder useSignature(boolean useSignature);

        Builder useSignatureEmail(boolean useSignatureEmail);

        Subscription build();
    }
}