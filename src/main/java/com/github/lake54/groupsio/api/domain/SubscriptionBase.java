package com.github.lake54.groupsio.api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.lake54.groupsio.api.domain.enums.attachment.MaxAttachmentSize;
import com.github.lake54.groupsio.api.domain.enums.email.EmailDelivery;
import com.github.lake54.groupsio.api.domain.enums.message.MessageSelection;
import com.github.lake54.groupsio.api.domain.enums.profile.ProfilePrivacy;
import com.github.lake54.groupsio.api.domain.enums.subscription.SubscriptionModStatus;
import com.github.lake54.groupsio.api.domain.enums.subscription.SubscriptionNotify;
import com.github.lake54.groupsio.api.domain.enums.subscription.SubscriptionOwnerMessageNotify;
import com.github.lake54.groupsio.api.domain.enums.subscription.SubscriptionPostStatus;
import com.github.lake54.groupsio.api.domain.enums.subscription.SubscriptionStatus;
import com.github.lake54.groupsio.api.domain.enums.user.UserStatus;

import java.util.Optional;

public abstract class SubscriptionBase extends Model {

    @JsonProperty("id")
    public abstract int id();

    @JsonProperty("created")
    public abstract String created();

    @JsonProperty("updated")
    public abstract String updated();

    @JsonProperty("user_id")
    public abstract int userId();

    @JsonProperty("group_id")
    public abstract int groupId();

    @JsonProperty("status")
    public abstract SubscriptionStatus status();

    @JsonProperty("post_status")
    public abstract SubscriptionPostStatus postStatus();

    @JsonProperty("email_delivery")
    public abstract Optional<EmailDelivery> emailDelivery();

    @JsonProperty("message_selection")
    public abstract Optional<MessageSelection> messageSelection();

    @JsonProperty("auto_follow_replies")
    public abstract Optional<Boolean> autoFollowReplies();

    @JsonProperty("max_attachment_size")
    public abstract Optional<MaxAttachmentSize> maxAttachmentSize();

    @JsonProperty("approved_posts")
    public abstract int approvedPosts();

    @JsonProperty("mod_status")
    public abstract SubscriptionModStatus modStatus();

    @JsonProperty("pending_msg_notify")
    public abstract Optional<SubscriptionNotify> pendingMsgNotify();

    @JsonProperty("pending_sub_notify")
    public abstract Optional<SubscriptionNotify> pendingSubNotify();

    @JsonProperty("sub_notify")
    public abstract Optional<SubscriptionNotify> subNotify();

    @JsonProperty("storage_notify")
    public abstract Optional<SubscriptionNotify> storageNotify();

    @JsonProperty("sub_group_notify")
    public abstract Optional<SubscriptionNotify> subGroupNotify();

    @JsonProperty("message_report_notify")
    public abstract Optional<SubscriptionNotify> messageReportNotify();

    // TODO: seemingly some API bug returning ""
    // @JsonProperty("mod_permissions")
    // public abstract SubscriptionPermission modPermissions();

    @JsonProperty("owner_msg_notify")
    public abstract Optional<SubscriptionOwnerMessageNotify> ownerMsgNotify();

    @JsonProperty("email")
    public abstract String email();

    @JsonProperty("user_status")
    public abstract UserStatus userStatus();

    @JsonProperty("user_name")
    public abstract String userName();

    @JsonProperty("timezone")
    public abstract String timezone();

    @JsonProperty("full_name")
    public abstract String fullName();

    @JsonProperty("about_me")
    public abstract String aboutMe();

    @JsonProperty("about_format")
    public abstract String aboutFormat();

    @JsonProperty("location")
    public abstract String location();

    @JsonProperty("website")
    public abstract String website();

    @JsonProperty("profile_photo_id")
    public abstract String profilePhotoId();

    @JsonProperty("profile_privacy")
    public abstract ProfilePrivacy profilePrivacy();

    @JsonProperty("dont_munge_message_id")
    public abstract boolean dontMungeMessageId();

    @JsonProperty("moderator_notes")
    public abstract String moderatorNotes();

    @JsonProperty("moderator_notes_updated")
    public abstract String moderatorNotesUpdated();

    @JsonProperty("use_signature")
    public abstract boolean useSignature();

    @JsonProperty("use_signature_email")
    public abstract boolean useSignatureEmail();

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

        // TODO: seemingly some API bug returning ""
        // Builder modPermissions(SubscriptionPermission modPermissions);

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

        SubscriptionBase build();
    }
}