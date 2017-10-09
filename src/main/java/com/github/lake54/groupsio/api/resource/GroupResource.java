package com.github.lake54.groupsio.api.resource;

import com.fasterxml.jackson.databind.JavaType;
import com.github.lake54.groupsio.api.GroupsIOApiClient;
import com.github.lake54.groupsio.api.GroupsIOApiRequest;
import com.github.lake54.groupsio.api.domain.Group;
import com.github.lake54.groupsio.api.domain.Permissions;
import com.github.lake54.groupsio.api.domain.enums.group.GroupPrivacy;
import com.github.lake54.groupsio.api.exception.GroupsIOApiException;
import com.github.lake54.groupsio.api.jackson.TypeUtils;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.List;

import static com.github.lake54.groupsio.api.domain.Error.Type.INADEQUATE_PERMISSIONS;

/**
 * Resource class based around all operations related to groups.
 */
public class GroupResource extends BaseResource {

    /**
     * A static reference to use when de-serializing pages of groups.
     */
    private static final JavaType GROUP_PAGE_TYPE = TypeUtils
        .createPaginationType(Group.class);

    /**
     * Creates a new resource using a client instance.
     *
     * @param apiClient
     *      the {@link GroupsIOApiClient} used for requests.
     */
    public GroupResource(@Nonnull GroupsIOApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Creates a subgroup for the specified group identifier.
     *
     * @param groupId
     *      the identifier of the parent group.
     * @param name
     *      the name of the group to create.
     * @param description
     *      the description of the group to create.
     * @param privacy
     *      the privacy of the group to create.
     * @return
     *      the full {@link Group} instance after creation.
     * @throws GroupsIOApiException
     *      on any errors dealing with data.
     * @throws IOException
     *      on any errors calling the API.
     */
    public Group createSubgroup(int groupId,
                                @Nonnull String name,
                                @Nonnull String description,
                                @Nonnull GroupPrivacy privacy) throws GroupsIOApiException, IOException {
        GroupsIOApiRequest request = GroupsIOApiRequest
            .builder("GET", "/createsubgroup")
                .putParam("group_id", "" + groupId)
                .putParam("sub_group_name", name)
                .putParam("desc", description)
                .putParam("privacy", privacy.name())
            .build();

        return this.apiClient.call(request, Group.class);
    }

    /**
     * Deletes a group via an identifier.
     *
     * @param groupId
     *      the group identifier to remove.
     * @throws GroupsIOApiException
     *      on any errors dealing with data.
     * @throws IOException
     *      on any errors calling the API.
     */
    public void deleteGroup(int groupId) throws GroupsIOApiException, IOException {
        if (!getPermissions(groupId).deleteGroup()) {
            throw new GroupsIOApiException(INADEQUATE_PERMISSIONS);
        }

        GroupsIOApiRequest request = GroupsIOApiRequest
            .builder("GET", "/deletegroup")
                .putParam("group_id", "" + groupId)
                .putParam("understand", "I understand")
            .build();

        this.apiClient.call(request, Object.class);
    }

    /**
     * Gets a {@link Group} for the specified group.
     *
     * @return
     *      the {@link Group} for the specified group identifier.
     * @throws GroupsIOApiException
     *      on any errors dealing with data.
     * @throws IOException
     *      on any errors calling the API.
     */
    public Group getGroup(int groupId) throws GroupsIOApiException, IOException {
        if (!getPermissions(groupId).manageGroupSettings()) {
            throw new GroupsIOApiException(INADEQUATE_PERMISSIONS);
        }

        GroupsIOApiRequest request = GroupsIOApiRequest
            .builder("GET", "/getgroup")
                .putParam("group_id", "" + groupId)
            .build();

        return this.apiClient.call(request, Group.class);
    }

    /**
     * Gets a user's {@link Permissions} for the specified group.
     *
     * @return
     *      the user's {@link Permissions} for the specified group identifier.
     * @throws GroupsIOApiException
     *      on any errors dealing with data.
     * @throws IOException
     *      on any errors calling the API.
     */
    public Permissions getPermissions(int groupId) throws GroupsIOApiException, IOException {
        GroupsIOApiRequest request = GroupsIOApiRequest
            .builder("GET", "/getperms")
                .putParam("group_id", "" + groupId)
            .build();

        return this.apiClient.call(request, Permissions.class);
    }

    /**
     * Gets a list of groups for a given group identifier.
     *
     * @param groupId
     *      the group identifier to fetch subgroups for.
     * @return
     *      a {@link List}<{@link Group}> belonging to a parent group.
     * @throws GroupsIOApiException
     *      on any errors dealing with data.
     * @throws IOException
     *      on any errors calling the API.
     */
    public List<Group> getSubgroups(int groupId) throws GroupsIOApiException, IOException {
        GroupsIOApiRequest request = GroupsIOApiRequest
            .builder("GET", "/getsubgroups")
                .putParam("group_id", "" + groupId)
                .putParam("limit", MAX_RESULTS)
            .build();

        return this.apiClient.paginate(request, GROUP_PAGE_TYPE);
    }

    /**
     * Updates a group given a {@link Group} object with only the updated
     * fields set.
     *
     * Example:
     *
     * <pre>
     *      Group groupToUpdate = Group
     *          .builder()
     *              .website("https://github.com/lake54/groupsio-api-java")
     *          .build();
     *      Group updatedGroup = client.group().updateGroup(groupToUpdate);
     * </pre>
     *
     * @param group
     *      a group model to use to apply updates.
     * @return
     *      the full {@link Group} after a successful update.
     * @throws GroupsIOApiException
     *      on any errors dealing with data.
     * @throws IOException
     *      on any errors calling the API.
     */
    public Group updateGroup(Group group) throws IOException, GroupsIOApiException {
        if (!getPermissions(group.id()).manageGroupSettings()) {
            throw new GroupsIOApiException(INADEQUATE_PERMISSIONS);
        }
        return this.update("/updategroup", Group.class, group);
    }
}
