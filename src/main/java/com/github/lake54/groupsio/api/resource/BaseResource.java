package com.github.lake54.groupsio.api.resource;

import com.github.lake54.groupsio.api.GroupsIOApiClient;
import com.github.lake54.groupsio.api.GroupsIOApiRequest;
import com.github.lake54.groupsio.api.exception.GroupsIOApiException;
import com.github.lake54.groupsio.api.jackson.TypeUtils;
import com.google.common.base.Preconditions;
import okhttp3.FormBody;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.Map;

/**
 * Base resource class adding base implementations for all resources.
 */
abstract class BaseResource {

    /**
     * Maximum limit for all pagination requests.
     */
    static final String MAX_RESULTS = "100";

    /**
     * Internal client instances to use for API calls.
     */
    final GroupsIOApiClient apiClient;

    /**
     * Creates a new resource using a client instance.
     *
     * @param apiClient
     *      the {@link GroupsIOApiClient} used for requests.
     */
    BaseResource(@Nonnull GroupsIOApiClient apiClient) {
        this.apiClient = Preconditions.checkNotNull(apiClient);
    }

    /**
     * Updates an object type via the API.
     *
     * @param path
     *      the path to use for the update operation.
     * @param tClass
     *      the class type being updated.
     * @param object
     *      the object update to apply.
     * @return
     *      an updated object instance.
     * @throws GroupsIOApiException
     *      on any errors dealing with data.
     * @throws IOException
     *      on any errors calling the API.
     */
    <T> T update(String path, Class<T> tClass, T object) throws GroupsIOApiException, IOException {
        FormBody.Builder formBuilder = new FormBody.Builder();

        Map<String, Object> data = TypeUtils.convert(object, factory ->
            factory.constructMapLikeType(Map.class, String.class, Object.class));

        for (Map.Entry<String, Object> entry : data.entrySet()) {
            formBuilder.add(entry.getKey(), entry.getValue().toString());
        }

        GroupsIOApiRequest request = GroupsIOApiRequest
            .builder("GET", path)
                .body(formBuilder.build())
            .build();

        return this.apiClient.call(request, tClass);
    }
}
