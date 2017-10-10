package com.github.lake54.groupsio.api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.Preconditions;
import org.immutables.value.Value;

import static org.immutables.value.Value.Style.ImplementationVisibility.PACKAGE;

@Value.Immutable(copy = false)
@Value.Style(visibility = PACKAGE)
@JsonSerialize(as = ImmutablePermissions.class)
@JsonDeserialize(as = ImmutablePermissions.class)
public abstract class Permissions extends Model {

    @JsonProperty("manage_subgroups")
    public abstract boolean manageSubgroups();

    @JsonProperty("delete_group")
    public abstract boolean deleteGroup();

    @JsonProperty("view_archives")
    public abstract boolean viewArchives();

    @JsonProperty("download_members")
    public abstract boolean downloadMembers();

    @JsonProperty("view_activity")
    public abstract boolean viewActivity();

    @JsonProperty("manage_hashtags")
    public abstract boolean manageHashtags();

    @JsonProperty("manage_integrations")
    public abstract boolean manageIntegrations();

    @JsonProperty("manage_group_settings")
    public abstract boolean manageGroupSettings();

    @JsonProperty("make_moderator")
    public abstract boolean makeModerator();

    @JsonProperty("manage_member_subscription_options")
    public abstract boolean manageMemberSubscriptionOptions();

    @JsonProperty("manage_pending_members")
    public abstract boolean managePendingMembers();

    @JsonProperty("remove_members")
    public abstract boolean removeMembers();

    @JsonProperty("ban_members")
    public abstract boolean banMembers();

    @JsonProperty("manage_group_billing")
    public abstract boolean manageGroupBilling();

    @JsonProperty("edit_archives")
    public abstract boolean editArchives();

    @JsonProperty("manage_pending_messages")
    public abstract boolean managePendingMessages();

    @JsonProperty("invite_members")
    public abstract boolean inviteMembers();

    @JsonProperty("view_databases")
    public abstract boolean viewDatabases();

    @JsonProperty("can_post")
    public abstract boolean canPost();

    @JsonProperty("manage_polls")
    public abstract boolean managePolls();

    @JsonProperty("view_photos")
    public abstract boolean viewPhotos();

    @JsonProperty("manage_photos")
    public abstract boolean managePhotos();

    @JsonProperty("manage_members")
    public abstract boolean manageMembers();

    @JsonProperty("view_calendar")
    public abstract boolean viewCalendar();

    @JsonProperty("manage_calendar")
    public abstract boolean manageCalendar();

    @JsonProperty("view_chats")
    public abstract boolean viewChats();

    @JsonProperty("manage_chats")
    public abstract boolean manageChats();

    @JsonProperty("view_member_directory")
    public abstract boolean viewMemberDirectory();

    @JsonProperty("view_files")
    public abstract boolean viewFiles();

    @JsonProperty("manage_files")
    public abstract boolean manageFiles();

    @JsonProperty("view_members")
    public abstract boolean viewMembers();

    @JsonProperty("view_wiki")
    public abstract boolean viewWiki();

    @JsonProperty("manage_wiki")
    public abstract boolean manageWiki();

    @JsonProperty("manage_subscription")
    public abstract boolean manageSubscription();

    @Value.Default
    @JsonProperty("object")
    public String object() {
        return "permissions";
    }

    @Value.Check
    void check() {
        Preconditions.checkState(object().equals("permissions"));
    }

    public static Builder builder() {
        return ImmutablePermissions.builder();
    }

    public interface Builder {

        Builder manageSubgroups(boolean manageSubgroups);

        Builder deleteGroup(boolean deleteGroup);

        Builder viewArchives(boolean viewArchives);

        Builder downloadMembers(boolean downloadMembers);

        Builder viewActivity(boolean viewActivity);

        Builder manageHashtags(boolean manageHashtags);

        Builder manageIntegrations(boolean manageIntegrations);

        Builder manageGroupSettings(boolean manageGroupSettings);

        Builder makeModerator(boolean makeModerator);

        Builder manageMemberSubscriptionOptions(boolean manageMemberSubscriptionOptions);

        Builder managePendingMembers(boolean managePendingMembers);

        Builder removeMembers(boolean removeMembers);

        Builder banMembers(boolean banMembers);

        Builder manageGroupBilling(boolean manageGroupBilling);

        Builder editArchives(boolean editArchives);

        Builder managePendingMessages(boolean managePendingMessages);

        Builder inviteMembers(boolean inviteMembers);

        Builder viewDatabases(boolean viewDatabases);

        Builder canPost(boolean canPost);

        Builder managePolls(boolean managePolls);

        Builder viewPhotos(boolean viewPhotos);

        Builder managePhotos(boolean managePhotos);

        Builder manageMembers(boolean manageMembers);

        Builder viewCalendar(boolean viewCalendar);

        Builder manageCalendar(boolean manageCalendar);

        Builder viewChats(boolean viewChats);

        Builder manageChats(boolean manageChats);

        Builder viewMemberDirectory(boolean viewMemberDirectory);

        Builder viewFiles(boolean viewFiles);

        Builder manageFiles(boolean manageFiles);

        Builder viewMembers(boolean viewMembers);

        Builder viewWiki(boolean viewWiki);

        Builder manageWiki(boolean manageWiki);

        Builder manageSubscription(boolean manageSubscription);

        Permissions build();
    }
}