package com.github.lake54.groupsio.api.domain;

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

    public abstract boolean manageSubgroups();

    public abstract boolean deleteGroup();

    public abstract boolean viewArchives();

    public abstract boolean downloadMembers();

    public abstract boolean viewActivity();

    public abstract boolean manageHashtags();

    public abstract boolean manageIntegrations();

    public abstract boolean manageGroupSettings();

    public abstract boolean makeModerator();

    public abstract boolean manageMemberSubscriptionOptions();

    public abstract boolean managePendingMembers();

    public abstract boolean removeMembers();

    public abstract boolean banMembers();

    public abstract boolean manageGroupBilling();

    public abstract boolean editArchives();

    public abstract boolean managePendingMessages();

    public abstract boolean inviteMembers();

    public abstract boolean viewDatabases();

    public abstract boolean canPost();

    public abstract boolean managePolls();

    public abstract boolean viewPhotos();

    public abstract boolean managePhotos();

    public abstract boolean manageMembers();

    public abstract boolean viewCalendar();

    public abstract boolean manageCalendar();

    public abstract boolean viewChats();

    public abstract boolean manageChats();

    public abstract boolean viewMemberDirectory();

    public abstract boolean viewFiles();

    public abstract boolean manageFiles();

    public abstract boolean viewMembers();

    public abstract boolean viewWiki();

    public abstract boolean manageWiki();

    public abstract boolean manageSubscription();

    @Value.Default
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