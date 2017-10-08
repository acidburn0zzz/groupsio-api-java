package com.github.lake54.groupsio.api.resource;

import com.github.lake54.groupsio.api.GroupsIOApiClient;
import com.github.lake54.groupsio.api.domain.Topic;
import com.github.lake54.groupsio.api.exception.GroupsIOApiException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class ArchiveResource extends BaseResource {

    public ArchiveResource(GroupsIOApiClient apiClient) {
        super(apiClient);
    }
    
    /**
     * Gets a list of {@link Topic}s from the specified group.
     * 
     * @return {@link List}<{@link Topic}> representing the topics in the group
     * @throws URISyntaxException
     * @throws IOException
     * @throws GroupsIOApiException
     */
    public List<Topic> getTopics(final Integer groupId) throws URISyntaxException, IOException, GroupsIOApiException {
        /*- Commented out until implemented in the API.
        final URIBuilder uri = new URIBuilder().setPath(baseUrl + "gettopics");
        uri.setParameter("group_id", groupId.toString());
        uri.setParameter("limit", MAX_RESULTS);
        final HttpRequestBase request = new HttpGet();
        request.setURI(uri.build());
        
        Page page = callApi(request, Page.class);
        
        final List<Topic> topics = Arrays.asList(OM.convertValue(page.getData(), Topic[].class));
        
        while (page.getHasMore())
        {
            uri.setParameter("page_token", page.getNextPageToken().toString());
            request.setURI(uri.build());
            page = callApi(request, Page.class);
            topics.addAll(Arrays.asList(OM.convertValue(page.getData(), Topic[].class)));
        }
        
        return topics;
        */
        throw new UnsupportedOperationException("Not available in API");
    }
}
