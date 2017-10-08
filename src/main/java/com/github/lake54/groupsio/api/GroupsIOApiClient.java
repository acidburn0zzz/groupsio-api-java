package com.github.lake54.groupsio.api;

import org.apache.http.HttpHost;
import org.apache.http.util.Asserts;

import com.github.lake54.groupsio.api.resource.ArchiveResource;
import com.github.lake54.groupsio.api.resource.GroupResource;
import com.github.lake54.groupsio.api.resource.MemberResource;
import com.github.lake54.groupsio.api.resource.UserResource;

/**
 * Main interface with Groups.io.
 * Actions will be carried out in the context of the user you login with. If you
 * receive an GroupsIOApiExceptionType.INADEQUATE_PERMISSIONS error, you need to
 * use a user who mods/owns a group. If you are integrating to manage one or
 * more groups rather than creating a generic application for use by any user,
 * consider creating a 'processing' user with mod/owner permissions just for use
 * with the API.
 * 
 * <pre>
 * GroupsIOApiClient client = new GroupsIOApiClient(apiKey, email);
 * List<Subscription> subscriptions = client.user().getSubscriptions();
 * subscriptions.get(0).getGroupId();
 * </pre>
 */
public class GroupsIOApiClient {

    private static final String DEFAULT_HOSTNAME = "api.groups.io";
    private static final String DEFAULT_VERSION = "v1";
    private static final String DEFAULT_DOMAIN = "groups.io";

    private final String apiKey;
    private final String apiRoot;
    private final String email;
    private final String domain;
    private final Integer twoFactor;

    private String apiToken;
    
    /**
     * Common client initialisation. Provide your API key and email.
     * 
     * @param apiKey
     *            - TODO: Update with details once published.
     * @param email
     *            - the email of the user to log in as
     */
    public GroupsIOApiClient(String apiKey, String email) {
        this(DEFAULT_HOSTNAME, DEFAULT_VERSION, apiKey, email, DEFAULT_DOMAIN, null);
    }
    
    /**
     * More in-depth constructor to override the defaults.
     * 
     * @param hostname
     *            - the base hostname (e.g. api.groups.io) to use
     * @param version
     *            - the API version (e.g. v1) to use
     * @param apiKey
     *            - TODO: Update with details once published.
     * @param email
     *            - the email of the user to log in as
     * @param domain
     *            - the domain name to connect with
     * @param twoFactor
     *            - the appropriate two-factor code to use
     */
    public GroupsIOApiClient(String hostname, String version, String apiKey, String email, String domain, Integer twoFactor) {
        Asserts.notBlank(apiKey, "apiKey");
        Asserts.notBlank(email, "email");
        this.apiKey = apiKey;
        this.email = email;
        this.domain = domain;
        this.twoFactor = twoFactor;
        this.apiRoot = "https://" + hostname + "/" + version;
    }

    /**
     * Actions involving message archives.
     *
     * @return {@link ArchiveResource}
     */
    public ArchiveResource archive()
    {
        return new ArchiveResource(this);
    }

    /**
     * Actions involving a specific group.
     * 
     * @return {@link GroupResource}
     */
    public GroupResource group()
    {
        return new GroupResource(this);
    }

    /**
     * Actions involving the members of a group.
     *
     * @return {@link MemberResource}
     */
    public MemberResource member() {
        return new MemberResource(this);
    }

    /**
     * Actions involving the currently authenticated user.
     *
     * @return {@link UserResource}
     */
    public UserResource user() {
        return new UserResource(this);
    }

    /**
     * @param apiToken
     *            set the apiToken
     */
    public void setApiToken(final String apiToken)
    {
        this.apiToken = apiToken;
    }

    /**
     * @return the apiKey
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * @return the apiToken
     */
    public String getApiToken() {
        return apiToken;
    }

    /**
     * @return the apiRoot
     */
    public String getApiRoot() {
        return apiRoot;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * @return the twoFactor
     */
    public Integer getTwoFactor() {
        return twoFactor;
    }
}
