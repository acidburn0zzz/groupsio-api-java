package com.github.lake54.groupsio.api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.lake54.groupsio.api.domain.enums.attachment.MaxAttachmentSize;
import com.github.lake54.groupsio.api.domain.enums.email.EmailDelivery;
import com.github.lake54.groupsio.api.domain.enums.group.GroupAccess;
import com.github.lake54.groupsio.api.domain.enums.group.GroupAttachments;
import com.github.lake54.groupsio.api.domain.enums.group.GroupMaxPhotoSize;
import com.github.lake54.groupsio.api.domain.enums.group.GroupPrivacy;
import com.github.lake54.groupsio.api.domain.enums.group.GroupReplyTo;
import com.github.lake54.groupsio.api.domain.enums.group.GroupSubgroupAccess;
import com.github.lake54.groupsio.api.domain.enums.group.GroupViewMembers;
import com.github.lake54.groupsio.api.domain.enums.message.MessageSelection;
import com.google.common.base.Preconditions;
import org.immutables.value.Value;

import java.util.Optional;

import static org.immutables.value.Value.Style.ImplementationVisibility.PACKAGE;

@Value.Immutable(copy = false)
@Value.Style(visibility = PACKAGE)
@JsonSerialize(as = ImmutableGroup.class)
@JsonDeserialize(as = ImmutableGroup.class)
public abstract class Group extends Model {

    @JsonProperty("id")
    public abstract int id();

    @JsonProperty("parent_group_id")
    public abstract Optional<Integer> parentGroupId();

    @JsonProperty("created")
    public abstract String created();

    @JsonProperty("updated")
    public abstract String updated();

    @JsonProperty("title")
    public abstract String title();

    @JsonProperty("name")
    public abstract String name();

    @JsonProperty("alias")
    public abstract String alias();

    @JsonProperty("desc")
    public abstract String desc();

    @JsonProperty("subject_tag")
    public abstract String subjectTag();

    @JsonProperty("footer")
    public abstract String footer();

    @JsonProperty("website")
    public abstract String website();

    @JsonProperty("announce")
    public abstract boolean announce();

    @JsonProperty("moderated")
    public abstract boolean moderated();

    @JsonProperty("new_users_moderated")
    public abstract boolean newUsersModerated();

    @JsonProperty("unmoderate_users_after")
    public abstract int unmoderateUsersAfter();

    @JsonProperty("restricted")
    public abstract boolean restricted();

    @JsonProperty("allow_non_subs_to_post")
    public abstract boolean allowNonSubsToPost();

    @JsonProperty("force_html_emails")
    public abstract boolean forceHtmlEmails();

    @JsonProperty("normalize_html_emails")
    public abstract boolean normalizeHtmlEmails();

    @JsonProperty("reply_to")
    public abstract GroupReplyTo replyTo();

    @JsonProperty("remove_other_reply_options")
    public abstract boolean removeOtherReplyOptions();

    @JsonProperty("privacy")
    public abstract GroupPrivacy privacy();

    @JsonProperty("members_visible")
    public abstract GroupViewMembers membersVisible();

    @JsonProperty("subgroup_access")
    public abstract GroupSubgroupAccess subgroupAccess();

    @JsonProperty("calendar_access")
    public abstract GroupAccess calendarAccess();

    @JsonProperty("files_access")
    public abstract GroupAccess filesAccess();

    @JsonProperty("database_access")
    public abstract GroupAccess databaseAccess();

    @JsonProperty("wiki_access")
    public abstract GroupAccess wikiAccess();

    @JsonProperty("photos_access")
    public abstract GroupAccess photosAccess();

    @JsonProperty("member_directory_access")
    public abstract GroupAccess memberDirectoryAccess();

    // TODO: appears to be a bug with invalid enum of "polls_access_subscribers"
    // @JsonProperty("polls_access")
    // public abstract GroupAccess pollsAccess();

    @JsonProperty("chat_access")
    public abstract GroupAccess chatAccess();

    @JsonProperty("handle_attachments")
    public abstract GroupAttachments handleAttachments();

    @JsonProperty("plain_text_only")
    public abstract boolean plainTextOnly();

    @JsonProperty("max_photo_size_email")
    public abstract GroupMaxPhotoSize maxPhotoSizeEmail();

    @JsonProperty("max_photo_size_photos")
    public abstract GroupMaxPhotoSize maxPhotoSizePhotos();

    @JsonProperty("max_photo_size_databases")
    public abstract GroupMaxPhotoSize maxPhotoSizeDatabases();

    @JsonProperty("max_photo_size_wiki_images")
    public abstract GroupMaxPhotoSize maxPhotoSizeWikiImages();

    @JsonProperty("hash_tags_required")
    public abstract boolean hashTagsRequired();

    @JsonProperty("restrict_create_hash_tags")
    public abstract boolean restrictCreateHashTags();

    @JsonProperty("bounce_attachments")
    public abstract boolean bounceAttachments();

    @JsonProperty("allow_photos_in_files")
    public abstract boolean allowPhotosInFiles();

    @JsonProperty("email_delivery_default")
    public abstract EmailDelivery emailDeliveryDefault();

    @JsonProperty("message_selection_default")
    public abstract MessageSelection messageSelectionDefault();

    @JsonProperty("auto_follow_replies_default")
    public abstract boolean autoFollowRepliesDefault();

    @JsonProperty("max_attachment_size_default")
    public abstract MaxAttachmentSize maxAttachmentSizeDefault();

    @JsonProperty("disable_edits")
    public abstract boolean disableEdits();

    @JsonProperty("disable_no_email")
    public abstract boolean disableNoEmail();

    @JsonProperty("auto_close_threads")
    public abstract boolean autoCloseThreads();

    @JsonProperty("close_threads_after")
    public abstract int closeThreadsAfter();

    @JsonProperty("auto_moderate_threads")
    public abstract boolean autoModerateThreads();

    @JsonProperty("moderate_threads_after")
    public abstract int moderateThreadsAfter();

    @Value.Default
    @JsonProperty("object")
    public String object() {
        return "group";
    }

    @Value.Check
    void check() {
        Preconditions.checkState(object().equals("group"));
    }

    public static Builder builder() {
        return ImmutableGroup.builder();
    }

    public interface Builder {

        Builder id(int id);

        Builder parentGroupId(int parentGroupId);

        Builder created(String created);

        Builder updated(String updated);

        Builder title(String title);

        Builder name(String name);

        Builder alias(String alias);

        Builder desc(String desc);

        Builder subjectTag(String subjectTag);

        Builder footer(String footer);

        Builder website(String website);

        Builder announce(boolean announce);

        Builder moderated(boolean moderated);

        Builder newUsersModerated(boolean newUsersModerated);

        Builder unmoderateUsersAfter(int unmoderateUsersAfter);

        Builder restricted(boolean restricted);

        Builder allowNonSubsToPost(boolean allowNonSubsToPost);

        Builder forceHtmlEmails(boolean forceHtmlEmails);

        Builder normalizeHtmlEmails(boolean normalizeHtmlEmails);

        Builder replyTo(GroupReplyTo replyTo);

        Builder removeOtherReplyOptions(boolean removeOtherReplyOptions);

        Builder privacy(GroupPrivacy privacy);

        Builder membersVisible(GroupViewMembers membersVisible);

        Builder subgroupAccess(GroupSubgroupAccess subgroupAccess);

        Builder calendarAccess(GroupAccess calendarAccess);

        Builder filesAccess(GroupAccess filesAccess);

        Builder databaseAccess(GroupAccess databaseAccess);

        Builder wikiAccess(GroupAccess wikiAccess);

        Builder photosAccess(GroupAccess photosAccess);

        Builder memberDirectoryAccess(GroupAccess memberDirectoryAccess);

        // TODO: appears to be a bug with invalid enum of "polls_access_subscribers"
        // Builder pollsAccess(GroupAccess pollsAccess);

        Builder chatAccess(GroupAccess chatAccess);

        Builder handleAttachments(GroupAttachments handleAttachments);

        Builder plainTextOnly(boolean plainTextOnly);

        Builder maxPhotoSizeEmail(GroupMaxPhotoSize maxPhotoSizeEmail);

        Builder maxPhotoSizePhotos(GroupMaxPhotoSize maxPhotoSizePhotos);

        Builder maxPhotoSizeDatabases(GroupMaxPhotoSize maxPhotoSizeDatabases);

        Builder maxPhotoSizeWikiImages(GroupMaxPhotoSize maxPhotoSizeWikiImages);

        Builder hashTagsRequired(boolean hashTagsRequired);

        Builder restrictCreateHashTags(boolean restrictCreateHashTags);

        Builder bounceAttachments(boolean bounceAttachments);

        Builder allowPhotosInFiles(boolean allowPhotosInFiles);

        Builder emailDeliveryDefault(EmailDelivery emailDeliveryDefault);

        Builder messageSelectionDefault(MessageSelection messageSelectionDefault);

        Builder autoFollowRepliesDefault(boolean autoFollowRepliesDefault);

        Builder maxAttachmentSizeDefault(MaxAttachmentSize maxAttachmentSizeDefault);

        Builder disableEdits(boolean disableEdits);

        Builder disableNoEmail(boolean disableNoEmail);

        Builder autoCloseThreads(boolean autoCloseThreads);

        Builder closeThreadsAfter(int closeThreadsAfter);

        Builder autoModerateThreads(boolean autoModerateThreads);

        Builder moderateThreadsAfter(int moderateThreadsAfter);

        Group build();
    }
}