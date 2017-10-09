package com.github.lake54.groupsio.api.resource;

import com.fasterxml.jackson.databind.JavaType;
import com.github.lake54.groupsio.api.GroupsIOApiClient;
import com.github.lake54.groupsio.api.GroupsIOApiRequest;
import com.github.lake54.groupsio.api.domain.Subscription;
import com.github.lake54.groupsio.api.domain.User;
import com.github.lake54.groupsio.api.exception.GroupsIOApiException;
import com.github.lake54.groupsio.api.jackson.TypeUtils;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.List;

/**
 * Resource class based around all operations related to the
 * currently logged in user.
 */
public class UserResource extends BaseResource {

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
    public UserResource(@Nonnull GroupsIOApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Deletes a user's subscription by id.
     *
     * @param sub_id
     *      the subscription identifier.
     * @throws GroupsIOApiException
     *      on any errors dealing with data.
     * @throws IOException
     *      on any errors calling the API.
     */
    public void deleteSubscription(int sub_id) throws GroupsIOApiException, IOException {
        GroupsIOApiRequest request = GroupsIOApiRequest
            .builder("GET", "/deletesub")
                .putParam("sub_id", "" + sub_id)
            .build();

        this.apiClient.call(request, Object.class);
    }

    /**
     * Gets a user's {@link Subscription} for the specified group ID.
     *
     * @return
     *      the user's {@link Subscription} for the specified group ID.
     * @throws GroupsIOApiException
     *      on any errors dealing with data.
     * @throws IOException
     *      on any errors calling the API.
     */
    public Subscription getSubscription(int groupId) throws GroupsIOApiException, IOException {
        GroupsIOApiRequest request = GroupsIOApiRequest
            .builder("GET", "/getsub")
                .putParam("group_id", "" + groupId)
            .build();

        return this.apiClient.call(request, Subscription.class);
    }

    /**
     * Gets a list of {@link Subscription}s that the current user is subscribed
     * to.
     *
     * @return
     *      {@link List}<{@link Subscription}> representing the subscriptions.
     * @throws GroupsIOApiException
     *      on any errors dealing with data.
     * @throws IOException
     *      on any errors calling the API.
     */
    public List<Subscription> getSubscriptions() throws GroupsIOApiException, IOException {
        GroupsIOApiRequest request = GroupsIOApiRequest
            .builder("GET", "/getsubs")
                .putParam("limit", MAX_RESULTS)
            .build();

        return this.apiClient.paginate(request, SUBSCRIPTION_PAGE_TYPE);
    }

    /**
     * Get the user information associated with the currently-logged in user.
     *
     * @return
     *      a {@link User} instance representing the current user
     * @throws GroupsIOApiException
     *      on any errors dealing with data.
     * @throws IOException
     *      on any errors calling the API.
     */
    public User getUser() throws GroupsIOApiException, IOException {
        GroupsIOApiRequest request = GroupsIOApiRequest
            .builder("GET", "/getuser").build();

        return this.apiClient.call(request, User.class);
    }

    /**
     * Joins a group as the currently logged in user.
     *
     * @param groupId
     *      the identifier of the group to join.
     */
    public void joinGroup(int groupId) {
        throw new UnsupportedOperationException("Not available in API");
    }

    /**
     * Updates a user subscription using a {@link Subscription} object.
     *
     * @param subscription
     *      the {@link Subscription} to use when applying updates.
     * @return
     *      the full {@link Subscription} after a successful update.
     */
    public Subscription updateSubscription(Subscription subscription) {
        throw new UnsupportedOperationException("Not available in API");
    }

    /**
     * Updates a user given a {@link User} object with only the updated
     * fields set.
     *
     * Example:
     *
     * <pre>
     *      User userToUpdate = User
     *          .builder()
     *              .perPagePref(user_per_page_pref50)
     *          .build();
     *      User updatedUser = client.user().updateUser(userToUpdate);
     * </pre>
     *
     * @param user
     *      a user model to use to apply updates.
     * @return
     *      the full {@link User} after a successful update.
     * @throws GroupsIOApiException
     *      on any errors dealing with data.
     * @throws IOException
     *      on any errors calling the API.
     */
    public User updateUser(User user) throws IOException, GroupsIOApiException {
        return this.update("/updateuser", User.class, user);
    }
}
