package com.github.lake54.groupsio.api.resource;

import com.github.lake54.groupsio.api.GroupsIOApiClient;
import com.github.lake54.groupsio.api.domain.Topic;
import com.github.lake54.groupsio.api.exception.GroupsIOApiException;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.List;

/**
 * Resource class based around all operations related to archives.
 */
public class ArchiveResource extends BaseResource {

    /**
     * Creates a new resource using a client instance.
     *
     * @param apiClient
     *      the {@link GroupsIOApiClient} used for requests.
     */
    public ArchiveResource(@Nonnull GroupsIOApiClient apiClient) {
        super(apiClient);
    }

    /**
     * Retrieves a list of topics for a group.
     *
     * @param groupId
     *      the group identifier to retrieve for.
     * @return
     *      a list of {@link Topic} instances.
     * @throws GroupsIOApiException
     *      on any errors dealing with data.
     * @throws IOException
     *      on any errors calling the API.
     */
    public List<Topic> getTopics(int groupId) throws IOException, GroupsIOApiException {
        throw new UnsupportedOperationException("Not available in API");
    }
}
