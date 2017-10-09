package com.github.lake54.groupsio.api.resource;

import com.fasterxml.jackson.databind.JavaType;
import com.github.lake54.groupsio.api.GroupsIOApiClient;
import com.github.lake54.groupsio.api.GroupsIOApiRequest;
import com.github.lake54.groupsio.api.domain.Subscription;
import com.github.lake54.groupsio.api.domain.results.BulkRemoveResults;
import com.github.lake54.groupsio.api.domain.results.DirectAddResults;
import com.github.lake54.groupsio.api.domain.results.InviteResults;
import com.github.lake54.groupsio.api.exception.GroupsIOApiException;
import com.github.lake54.groupsio.api.jackson.TypeUtils;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.List;

import static com.github.lake54.groupsio.api.domain.Error.Type.INADEQUATE_PERMISSIONS;

/**
 * Resource class based around all operations related to group members.
 */
public class MemberResource extends BaseResource {

    /**
     * A static reference to use when de-serializing pages of subscriptions.
     */
    private static final JavaType SUBSCRIPTION_PAGE_TYPE = TypeUtils
        .createPaginationType(Subscription.class);

    /**
     * Creates a new resource using a client instance.
     *
     * @param apiClient
     *      the {@link GroupsIOApiClient} used for requests.
     */
    public MemberResource(@Nonnull GroupsIOApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Approves a member in a group.
     *
     * @param groupId
     *      the identifier of the group to approve in.
     * @param subscriptionId
     *      the identifier of the subscription to approve.
     * @return
     *      the member's {@link Subscription} instance.
     * @throws GroupsIOApiException
     *      on any errors dealing with data.
     * @throws IOException
     *      on any errors calling the API.
     */
    public Subscription approveMember(int groupId, int subscriptionId) throws GroupsIOApiException, IOException {
        if (!this.apiClient.group().getPermissions(groupId).managePendingMembers()) {
            throw new GroupsIOApiException(INADEQUATE_PERMISSIONS);
        }

        GroupsIOApiRequest request = GroupsIOApiRequest
            .builder("GET", "/approvemember")
                .putParam("group_id", "" + groupId)
                .putParam("sub_id", "" + subscriptionId)
            .build();

        return this.apiClient.call(request, Subscription.class);
    }

    /**
     * Bans a member from a group.
     *
     * @param groupId
     *      the group identifier to ban from.
     * @param subscriptionId
     *      the identifier of the subscription to ban.
     * @return
     *      the member's {@link Subscription} instance.
     * @throws GroupsIOApiException
     *      on any errors dealing with data.
     * @throws IOException
     *      on any errors calling the API.
     */
    public Subscription banMember(int groupId, int subscriptionId) throws GroupsIOApiException, IOException {
        if (!this.apiClient.group().getPermissions(groupId).banMembers()
            || !getMemberInGroup(groupId, subscriptionId).status().canBan()) {
            throw new GroupsIOApiException(INADEQUATE_PERMISSIONS);
        }

        GroupsIOApiRequest request = GroupsIOApiRequest
            .builder("GET", "/banmember")
                .putParam("group_id", "" + groupId)
                .putParam("sub_id", "" + subscriptionId)
            .build();

        return this.apiClient.call(request, Subscription.class);
    }

    /**
     * Removes a batch of users from a group.
     *
     * @param groupId
     *      the group identifier to remove from.
     * @param emails
     *      a list of user emails to remove.
     * @return
     *      a {@link BulkRemoveResults} object.
     * @throws GroupsIOApiException
     *      on any errors dealing with data.
     * @throws IOException
     *      on any errors calling the API.
     */
    public BulkRemoveResults bulkRemoveMembers(int groupId, List<String> emails) throws GroupsIOApiException, IOException {
        if (this.apiClient.group().getPermissions(groupId).inviteMembers()) {
            throw new GroupsIOApiException(INADEQUATE_PERMISSIONS);
        }

        GroupsIOApiRequest request = GroupsIOApiRequest
            .builder("GET", "/bulkremovemembers")
                .putParam("group_id", "" + groupId)
                .putParam("emails", String.join("\n", emails))
            .build();

        return this.apiClient.call(request, BulkRemoveResults.class);
    }

    /**
     * Directly adds a batch of users to a group.
     *
     * @param groupId
     *      the group identifier to add to.
     * @param emails
     *      a list of user emails to add.
     * @return
     *      a {@link DirectAddResults} object.
     * @throws GroupsIOApiException
     *      on any errors dealing with data.
     * @throws IOException
     *      on any errors calling the API.
     */
    public DirectAddResults directAddMember(int groupId, List<String> emails) throws GroupsIOApiException, IOException {
        if (!this.apiClient.group().getPermissions(groupId).inviteMembers()) {
            throw new GroupsIOApiException(INADEQUATE_PERMISSIONS);
        }

        GroupsIOApiRequest request = GroupsIOApiRequest
            .builder("GET", "/directadd")
                .putParam("group_id", "" + groupId)
                .putParam("emails", String.join("\n", emails))
            .build();

        return this.apiClient.call(request, DirectAddResults.class);
    }

    /**
     * Gets a member's {@link Subscription} for the specified group identifiers.
     *
     * @param groupId
     *      the group identifier to lookup.
     * @param subscriptionId
     *      the member identifier to lookup.
     * @return
     *      the member's {@link Subscription} instance.
     * @throws GroupsIOApiException
     *      on any errors dealing with data.
     * @throws IOException
     *      on any errors calling the API.
     */
    public Subscription getMemberInGroup(int groupId, int subscriptionId) throws GroupsIOApiException, IOException {
        if (!this.apiClient.group().getPermissions(groupId).viewMembers()) {
            throw new GroupsIOApiException(INADEQUATE_PERMISSIONS);
        }

        GroupsIOApiRequest request = GroupsIOApiRequest
            .builder("GET", "/getmember")
                .putParam("group_id", "" + groupId)
                .putParam("sub_id", "" + subscriptionId)
            .build();

        return this.apiClient.call(request, Subscription.class);
    }

    /**
     * Gets a list of members subscribed to a particular group.
     *
     * @param groupId
     *      the group identifier to lookup.
     * @return
     *      a {@link List}<{@link Subscription}> representing the subscribed members.
     * @throws GroupsIOApiException
     *      on any errors dealing with data.
     * @throws IOException
     *      on any errors calling the API.
     */
    public List<Subscription> getMembersInGroup(int groupId) throws GroupsIOApiException, IOException {
        if (!this.apiClient.group().getPermissions(groupId).viewMembers()) {
            throw new GroupsIOApiException(INADEQUATE_PERMISSIONS);
        }

        GroupsIOApiRequest request = GroupsIOApiRequest
            .builder("GET", "/getmembers")
                .putParam("group_id", "" + groupId)
                .putParam("limit", MAX_RESULTS)
            .build();

        return this.apiClient.paginate(request, SUBSCRIPTION_PAGE_TYPE);
    }

    /**
     * Invites adds a batch of users to a group.
     *
     * @param groupId
     *      the group identifier to invite to.
     * @param emails
     *      a list of user emails to invite.
     * @return
     *      a {@link InviteResults} object.
     * @throws GroupsIOApiException
     *      on any errors dealing with data.
     * @throws IOException
     *      on any errors calling the API.
     */
    public InviteResults inviteMember(int groupId, List<String> emails) throws GroupsIOApiException, IOException {
        if (!this.apiClient.group().getPermissions(groupId).inviteMembers()) {
            throw new GroupsIOApiException(INADEQUATE_PERMISSIONS);
        }

        GroupsIOApiRequest request = GroupsIOApiRequest
            .builder("GET", "/invite")
                .putParam("group_id", "" + groupId)
                .putParam("emails", String.join("\n", emails))
            .build();

        return this.apiClient.call(request, InviteResults.class);
    }

    /**
     * Removes a member from a group.
     *
     * @param groupId
     *      the group identifier to remove from.
     * @param subscriptionId
     *      the member identifier to remove.
     * @return
     *      the member's {@link Subscription} instance.
     * @throws GroupsIOApiException
     *      on any errors dealing with data.
     * @throws IOException
     *      on any errors calling the API.
     */
    public Subscription removeMember(int groupId, int subscriptionId) throws GroupsIOApiException, IOException {
        if (!this.apiClient.group().getPermissions(groupId).removeMembers()) {
            throw new GroupsIOApiException(INADEQUATE_PERMISSIONS);
        }

        GroupsIOApiRequest request = GroupsIOApiRequest
            .builder("GET", "/removemember")
                .putParam("group_id", "" + groupId)
                .putParam("sub_id", "" + subscriptionId)
            .build();

        return this.apiClient.call(request, Subscription.class);
    }

    /**
     * Searches for a list of members subscribed to a particular group.
     *
     * @param groupId
     *      the group identifier to search in.
     * @param query
     *      what to search for (will search over email or name).
     * @return
     *      a {@link List}<{@link Subscription}> representing the subscribed members.
     * @throws GroupsIOApiException
     *      on any errors dealing with data.
     * @throws IOException
     *      on any errors calling the API.
     */
    public List<Subscription> searchMembers(int groupId, String query) throws GroupsIOApiException, IOException {
        if (!this.apiClient.group().getPermissions(groupId).viewMembers()) {
            throw new GroupsIOApiException(INADEQUATE_PERMISSIONS);
        }

        GroupsIOApiRequest request = GroupsIOApiRequest
            .builder("GET", "/searchmembers")
                .putParam("group_id", "" + groupId)
                .putParam("limit", MAX_RESULTS)
                .putParam("q", query)
            .build();

        return this.apiClient.paginate(request, SUBSCRIPTION_PAGE_TYPE);
    }

    /**
     * Send a bounce probe to a specific member.
     *
     * @param groupId
     *      the group identifier to work under.
     * @param subscriptionId
     *      the identifier of the subscription.
     * @return
     *      the member's {@link Subscription} instance.
     * @throws GroupsIOApiException
     *      on any errors dealing with data.
     * @throws IOException
     *      on any errors calling the API.
     */
    public Subscription sendBounceProbe(int groupId, int subscriptionId) throws GroupsIOApiException, IOException {
        if (!this.apiClient.group().getPermissions(groupId).manageMemberSubscriptionOptions()
                || !getMemberInGroup(groupId, subscriptionId).userStatus().canSendBounceProbe()) {
            throw new GroupsIOApiException(INADEQUATE_PERMISSIONS);
        }

        GroupsIOApiRequest request = GroupsIOApiRequest
            .builder("GET", "/sendbounceprobe")
                .putParam("group_id", "" + groupId)
                .putParam("sub_id", "" + subscriptionId)
            .build();

        return this.apiClient.call(request, Subscription.class);
    }

    /**
     * Send a confirmation email to a user if they are not yet confirmed.
     *
     * @param groupId
     *      the group identifier to work under.
     * @param subscriptionId
     *      the identifier of the subscription.
     * @return
     *      the member's {@link Subscription} instance.
     * @throws GroupsIOApiException
     *      on any errors dealing with data.
     * @throws IOException
     *      on any errors calling the API.
     */
    public Subscription sendConfirmationEmail(int groupId, int subscriptionId) throws GroupsIOApiException, IOException {
        if (!this.apiClient.group().getPermissions(groupId).manageMemberSubscriptionOptions()
                || !getMemberInGroup(groupId, subscriptionId).userStatus().canSendConfirmationEmail()) {
            throw new GroupsIOApiException(INADEQUATE_PERMISSIONS);
        }

        GroupsIOApiRequest request = GroupsIOApiRequest
            .builder("GET", "/sendbounceprobe")
                .putParam("group_id", "" + groupId)
                .putParam("sub_id", "" + subscriptionId)
            .build();

        return this.apiClient.call(request, Subscription.class);
    }

    /**
     * Updates a member given a {@link Subscription} object with only the updated
     * fields set.
     *
     * Example:
     *
     * <pre>
     *      Subscription subToUpdate = Subscription
     *          .builder()
     *              .autoFollowReplies(true)
     *          .build();
     *      Subscription updatedSub = client.member().updateMember(updatedSub);
     * </pre>
     *
     * @param subscription
     *      a subscription model to use to apply updates.
     * @return
     *      the full {@link Subscription} after a successful update.
     * @throws GroupsIOApiException
     *      on any errors dealing with data.
     * @throws IOException
     *      on any errors calling the API.
     */
    public Subscription updateMember(Subscription subscription) throws GroupsIOApiException, IOException {
        if (!this.apiClient.group().getPermissions(subscription.groupId()).manageMemberSubscriptionOptions()) {
            throw new GroupsIOApiException(INADEQUATE_PERMISSIONS);
        }
        return this.update("/updatemember", Subscription.class, subscription);
    }
}
