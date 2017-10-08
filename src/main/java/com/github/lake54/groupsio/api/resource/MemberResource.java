package com.github.lake54.groupsio.api.resource;

import com.fasterxml.jackson.databind.JavaType;
import com.github.lake54.groupsio.api.GroupsIOApiClient;
import com.github.lake54.groupsio.api.domain.results.DirectAddResults;
import com.github.lake54.groupsio.api.domain.Error;
import com.github.lake54.groupsio.api.domain.Page;
import com.github.lake54.groupsio.api.domain.Subscription;
import com.github.lake54.groupsio.api.exception.GroupsIOApiException;
import com.github.lake54.groupsio.api.jackson.TypeUtils;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static com.github.lake54.groupsio.api.domain.Error.Type.INADEQUATE_PERMISSIONS;

public class MemberResource extends BaseResource
{
    private static final JavaType SUBSCRIPTION_PAGE_TYPE = TypeUtils.generateType(factory -> {
        return factory.constructParametricType(Page.class, Subscription.class);
    });

    public MemberResource(final GroupsIOApiClient apiClient, final String baseUrl)
    {
        super(apiClient, baseUrl);
    }
    
    /**
     * Gets a user's {@link Subscription} for the specified group and member IDs
     * 
     * @return the user's {@link Subscription} for the specified group ID
     * @throws URISyntaxException
     * @throws IOException
     * @throws GroupsIOApiException
     */
    public Subscription getMemberInGroup(final Integer groupId, final Integer memberId)
            throws URISyntaxException, IOException, GroupsIOApiException
    {
        if (apiClient.group().getPermissions(groupId).viewMembers())
        {
            final URIBuilder uri = new URIBuilder().setPath(baseUrl + "getmember");
            uri.setParameter("group_id", groupId.toString());
            uri.setParameter("sub_id", memberId.toString());
            final HttpRequestBase request = new HttpGet();
            request.setURI(uri.build());
            
            return callApi(request, Subscription.class);
        }
        else
        {
            final Error error = Error.create(INADEQUATE_PERMISSIONS);
            throw new GroupsIOApiException(error);
        }
    }
    
    /**
     * Gets a list of members (represented by {@link Subscription}) subscribed
     * to a particular group.
     * 
     * @param groupId
     *            - which group to get members from
     * @return {@link List}<{@link Subscription}> representing the subscribed
     *         members
     * @throws URISyntaxException
     * @throws IOException
     * @throws GroupsIOApiException
     */
    public List<Subscription> getMembersInGroup(final Integer groupId) throws URISyntaxException, IOException, GroupsIOApiException
    {
        if (apiClient.group().getPermissions(groupId).viewMembers())
        {
            final URIBuilder uri = new URIBuilder().setPath(baseUrl + "getmembers");
            uri.setParameter("group_id", groupId.toString());
            uri.setParameter("limit", MAX_RESULTS);
            final HttpRequestBase request = new HttpGet();
            request.setURI(uri.build());

            Page<Subscription> page = callApi(request, SUBSCRIPTION_PAGE_TYPE);
            final List<Subscription> subscriptions = new ArrayList<>();
            subscriptions.addAll(page.data());
            
            while (page.hasMore())
            {
                uri.setParameter("page_token", "" + page.nextPageToken());
                request.setURI(uri.build());
                page = callApi(request, SUBSCRIPTION_PAGE_TYPE);
                subscriptions.addAll(page.data());
            }
            
            return subscriptions;
        }
        else
        {
            final Error error = Error.create(INADEQUATE_PERMISSIONS);
            throw new GroupsIOApiException(error);
        }
    }
    
    /**
     * Gets a list of members (represented by {@link Subscription}) subscribed
     * to a particular group.
     * 
     * @param groupId
     *            - which group to get members from
     * @param query
     *            - what to search for (will search over email or name)
     * @return {@link List}<{@link Subscription}> representing the subscribed
     *         members
     * @throws URISyntaxException
     * @throws IOException
     * @throws GroupsIOApiException
     */
    public List<Subscription> searchMembers(final Integer groupId, final String query)
            throws URISyntaxException, IOException, GroupsIOApiException
    {
        if (apiClient.group().getPermissions(groupId).viewMembers())
        {
            final URIBuilder uri = new URIBuilder().setPath(baseUrl + "searchmembers");
            uri.setParameter("group_id", groupId.toString());
            uri.setParameter("q", query);
            uri.setParameter("limit", MAX_RESULTS);
            final HttpRequestBase request = new HttpGet();
            request.setURI(uri.build());

            Page<Subscription> page = callApi(request, SUBSCRIPTION_PAGE_TYPE);
            final List<Subscription> subscriptions = new ArrayList<>();
            subscriptions.addAll(page.data());
            
            while (page.hasMore())
            {
                uri.setParameter("page_token", "" + page.nextPageToken());
                request.setURI(uri.build());
                page = callApi(request, SUBSCRIPTION_PAGE_TYPE);
                subscriptions.addAll(Arrays.asList(OM.convertValue(page.data(), Subscription[].class)));
            }
            
            return subscriptions;
        }
        else
        {
            final Error error = Error.create(INADEQUATE_PERMISSIONS);
            throw new GroupsIOApiException(error);
        }
    }
    
    /**
     * Update a subscription to a group given a blank {@link Subscription}
     * object with only the updated
     * fields set.
     * Example:
     * 
     * <pre>
     * final Subscription subToUpdate = new Subscription();
     * subToUpdate.setAutoFollowReplies(true);
     * final Subscription updateSub = client.member().updateMember(subToUpdate);
     * </pre>
     * 
     * @param subscription
     *            - with only the updated fields set
     * @return the full {@link Subscription} after a successful update
     * @throws URISyntaxException
     * @throws IOException
     * @throws GroupsIOApiException
     */
    public Subscription updateMember(final Subscription subscription) throws URISyntaxException, IOException, GroupsIOApiException
    {
        if (apiClient.group().getPermissions(subscription.groupId()).manageMemberSubscriptionOptions())
        {
            final URIBuilder uri = new URIBuilder().setPath(baseUrl + "updatemember");
            final HttpPost request = new HttpPost();
            final Map<String, Object> map = OM.convertValue(subscription, Map.class);
            final List<BasicNameValuePair> postParameters = new ArrayList<>();
            for (final Entry<String, Object> entry : map.entrySet())
            {
                postParameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }
            request.setEntity(new UrlEncodedFormEntity(postParameters));
            
            request.setURI(uri.build());
            
            return callApi(request, Subscription.class);
        }
        else
        {
            final Error error = Error.create(INADEQUATE_PERMISSIONS);
            throw new GroupsIOApiException(error);
        }
    }
    
    /**
     * Ban a member if they aren't already banned
     * 
     * @param groupId
     *            of the group they belong to
     * @param subscriptionId
     *            of the subscription they have
     * @return the user's {@link Subscription}
     * @throws URISyntaxException
     * @throws IOException
     * @throws GroupsIOApiException
     */
    public Subscription banMember(final Integer groupId, final Integer subscriptionId)
            throws URISyntaxException, IOException, GroupsIOApiException
    {
        if (apiClient.group().getPermissions(groupId).banMembers()
                && getMemberInGroup(groupId, subscriptionId).status().canBan())
        {
            final URIBuilder uri = new URIBuilder().setPath(baseUrl + "banmember");
            uri.setParameter("group_id", groupId.toString());
            uri.setParameter("sub_id", subscriptionId.toString());
            final HttpRequestBase request = new HttpGet();
            request.setURI(uri.build());
            
            return callApi(request, Subscription.class);
        }
        else
        {
            final Error error = Error.create(INADEQUATE_PERMISSIONS);
            throw new GroupsIOApiException(error);
        }
    }
    
    /**
     * Approve a member to a group
     * 
     * @param groupId
     *            of the group they should belong to
     * @param subscriptionId
     *            of the subscription they have
     * @return the user's {@link Subscription}
     * @throws URISyntaxException
     * @throws IOException
     * @throws GroupsIOApiException
     */
    public Subscription approveMember(final Integer groupId, final Integer subscriptionId)
            throws URISyntaxException, IOException, GroupsIOApiException
    {
        if (apiClient.group().getPermissions(groupId).managePendingMembers())
        {
            final URIBuilder uri = new URIBuilder().setPath(baseUrl + "approvemember");
            uri.setParameter("group_id", groupId.toString());
            uri.setParameter("sub_id", subscriptionId.toString());
            final HttpRequestBase request = new HttpGet();
            request.setURI(uri.build());
            
            return callApi(request, Subscription.class);
        }
        else
        {
            final Error error = Error.create(INADEQUATE_PERMISSIONS);
            throw new GroupsIOApiException(error);
        }
    }

    /**
     * Remove members directly from a group
     *
     * @param groupId
     *      of the group they should be removed from
     * @param emails
     *      a list of email address to remove.
     * @throws URISyntaxException
     * @throws IOException
     * @throws GroupsIOApiException
     */
    public void bulkRemoveMembers(final Integer groupId, List<String> emails)
        throws URISyntaxException, IOException, GroupsIOApiException
    {

        if (apiClient.group().getPermissions(groupId).inviteMembers())
        {
            final URIBuilder uri = new URIBuilder().setPath(baseUrl + "bulkremovemembers");
            uri.setParameter("group_id", groupId.toString());
            uri.setParameter("emails", String.join("\n", emails));
            final HttpRequestBase request = new HttpGet();
            request.setURI(uri.build());

            callApi(request, DirectAddResults.class);
        }
        else
        {
            final Error error = Error.create(INADEQUATE_PERMISSIONS);
            throw new GroupsIOApiException(error);
        }
    }

    /**
     * Add members directly to a group
     *
     * @param groupId
     *      of the group they should be added to
     * @param emails
     *      a list of email address to add.
     * @throws URISyntaxException
     * @throws IOException
     * @throws GroupsIOApiException
     */
    public void directAddMember(final Integer groupId, List<String> emails)
            throws URISyntaxException, IOException, GroupsIOApiException
    {

        if (apiClient.group().getPermissions(groupId).inviteMembers())
        {
            final URIBuilder uri = new URIBuilder().setPath(baseUrl + "directadd");
            uri.setParameter("group_id", groupId.toString());
            uri.setParameter("emails", String.join("\n", emails));
            final HttpRequestBase request = new HttpGet();
            request.setURI(uri.build());

            callApi(request, DirectAddResults.class);
        }
        else
        {
            final Error error = Error.create(INADEQUATE_PERMISSIONS);
            throw new GroupsIOApiException(error);
        }
    }
    
    public void inviteMember()
    {
        throw new NotImplementedException("Not implemented in client");
    }
    
    /**
     * Remove a member from a group
     * 
     * @param groupId
     *            of the group they belong to
     * @param subscriptionId
     *            of the subscription they have
     * @return the user's {@link Subscription}
     * @throws URISyntaxException
     * @throws IOException
     * @throws GroupsIOApiException
     */
    public Subscription removeMember(final Integer groupId, final Integer subscriptionId)
            throws URISyntaxException, IOException, GroupsIOApiException
    {
        if (apiClient.group().getPermissions(groupId).removeMembers())
        {
            final URIBuilder uri = new URIBuilder().setPath(baseUrl + "removemember");
            uri.setParameter("group_id", groupId.toString());
            uri.setParameter("sub_id", subscriptionId.toString());
            final HttpRequestBase request = new HttpGet();
            request.setURI(uri.build());
            
            return callApi(request, Subscription.class);
        }
        else
        {
            final Error error = Error.create(INADEQUATE_PERMISSIONS);
            throw new GroupsIOApiException(error);
        }
    }
    
    /**
     * Send a bounce probe to a user if they are bouncing
     * 
     * @param groupId
     *            of the group they belong to
     * @param subscriptionId
     *            of the subscription they have
     * @return the user's {@link Subscription}
     * @throws URISyntaxException
     * @throws IOException
     * @throws GroupsIOApiException
     */
    public Subscription sendBounceProbe(final Integer groupId, final Integer subscriptionId)
            throws URISyntaxException, IOException, GroupsIOApiException
    {
        if (apiClient.group().getPermissions(groupId).manageMemberSubscriptionOptions()
                && getMemberInGroup(groupId, subscriptionId).userStatus().canSendBounceProbe())
        {
            final URIBuilder uri = new URIBuilder().setPath(baseUrl + "sendbounceprobe");
            uri.setParameter("group_id", groupId.toString());
            uri.setParameter("sub_id", subscriptionId.toString());
            final HttpRequestBase request = new HttpGet();
            request.setURI(uri.build());
            
            return callApi(request, Subscription.class);
        }
        else
        {
            final Error error = Error.create(INADEQUATE_PERMISSIONS);
            throw new GroupsIOApiException(error);
        }
    }
    
    /**
     * Send a confirmation email to a user if they are not yet confirmed
     * 
     * @param groupId
     *            of the group they belong to
     * @param subscriptionId
     *            of the subscription they have
     * @return the user's {@link Subscription}
     * @throws URISyntaxException
     * @throws IOException
     * @throws GroupsIOApiException
     */
    public Subscription sendConfirmationEmail(final Integer groupId, final Integer subscriptionId)
            throws URISyntaxException, IOException, GroupsIOApiException
    {
        if (apiClient.group().getPermissions(groupId).manageMemberSubscriptionOptions()
                && getMemberInGroup(groupId, subscriptionId).userStatus().canSendConfirmationEmail())
        {
            final URIBuilder uri = new URIBuilder().setPath(baseUrl + "sendconfirmation");
            uri.setParameter("group_id", groupId.toString());
            uri.setParameter("sub_id", subscriptionId.toString());
            final HttpRequestBase request = new HttpGet();
            request.setURI(uri.build());
            
            return callApi(request, Subscription.class);
        }
        else
        {
            final Error error = Error.create(INADEQUATE_PERMISSIONS);
            throw new GroupsIOApiException(error);
        }
    }
}
