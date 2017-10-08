package com.github.lake54.groupsio.api.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.lake54.groupsio.api.domain.enums.attachment.MaxAttachmentSize;
import com.github.lake54.groupsio.api.domain.enums.email.EmailDelivery;
import com.github.lake54.groupsio.api.domain.enums.group.GroupAccess;
import com.github.lake54.groupsio.api.domain.enums.group.GroupAttachments;
import com.github.lake54.groupsio.api.domain.enums.group.GroupMaxPhotoSize;
import com.github.lake54.groupsio.api.domain.enums.group.GroupPrivacy;
import com.github.lake54.groupsio.api.domain.enums.group.GroupReplyTo;
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

    public abstract int id();

    public abstract Optional<Integer> parentGroupId();

    public abstract String created();

    public abstract String updated();

    public abstract String title();

    public abstract String name();

    public abstract String alias();

    public abstract String desc();

    public abstract String subjectTag();

    public abstract String footer();

    public abstract String website();

    public abstract boolean announce();

    public abstract boolean moderated();

    public abstract boolean newUsersModerated();

    public abstract int unmoderateUsersAfter();

    public abstract boolean restricted();

    public abstract boolean allowNonSubsToPost();

    public abstract boolean forceHtmlEmails();

    public abstract boolean normalizeHtmlEmails();

    public abstract GroupReplyTo replyTo();

    public abstract boolean removeOtherReplyOptions();

    public abstract GroupPrivacy privacy();

    public abstract GroupViewMembers membersVisible();

    public abstract GroupAccess subgroupAccess();

    public abstract GroupAccess calendarAccess();

    public abstract GroupAccess filesAccess();

    public abstract GroupAccess databaseAccess();

    public abstract GroupAccess wikiAccess();

    public abstract GroupAccess photosAccess();

    public abstract GroupAccess memberDirectoryAccess();

    public abstract GroupAccess pollsAccess();

    public abstract GroupAccess chatAccess();

    public abstract GroupAttachments handleAttachments();

    public abstract boolean plainTextOnly();

    public abstract GroupMaxPhotoSize maxPhotoSizeEmail();

    public abstract GroupMaxPhotoSize maxPhotoSizePhotos();

    public abstract GroupMaxPhotoSize maxPhotoSizeDatabases();

    public abstract GroupMaxPhotoSize maxPhotoSizeWikiImages();

    public abstract boolean hashTagsRequired();

    public abstract boolean restrictCreateHashTags();

    public abstract boolean bounceAttachments();

    public abstract boolean allowPhotosInFiles();

    public abstract EmailDelivery emailDeliveryDefault();

    public abstract MessageSelection messageSelectionDefault();

    public abstract boolean autoFollowRepliesDefault();

    public abstract MaxAttachmentSize maxAttachmentSizeDefault();

    public abstract boolean disableEdits();

    public abstract boolean disableNoEmail();

    public abstract boolean autoCloseThreads();

    public abstract int closeThreadsAfter();

    public abstract boolean autoModerateThreads();

    public abstract int moderateThreadsAfter();

    @Value.Default
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

        Builder subgroupAccess(GroupAccess subgroupAccess);

        Builder calendarAccess(GroupAccess calendarAccess);

        Builder filesAccess(GroupAccess filesAccess);

        Builder databaseAccess(GroupAccess databaseAccess);

        Builder wikiAccess(GroupAccess wikiAccess);

        Builder photosAccess(GroupAccess photosAccess);

        Builder memberDirectoryAccess(GroupAccess memberDirectoryAccess);

        Builder pollsAccess(GroupAccess pollsAccess);

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