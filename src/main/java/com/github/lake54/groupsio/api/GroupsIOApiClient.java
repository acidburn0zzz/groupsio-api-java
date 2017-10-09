package com.github.lake54.groupsio.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lake54.groupsio.api.domain.Error;
import com.github.lake54.groupsio.api.domain.Login;
import com.github.lake54.groupsio.api.domain.Page;
import com.github.lake54.groupsio.api.exception.GroupsIOApiException;
import com.github.lake54.groupsio.api.jackson.TypeUtils;
import com.github.lake54.groupsio.api.resource.ArchiveResource;
import com.github.lake54.groupsio.api.resource.GroupResource;
import com.github.lake54.groupsio.api.resource.MemberResource;
import com.github.lake54.groupsio.api.resource.UserResource;
import com.google.common.base.Preconditions;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
 * GroupsIOApiClient client = new GroupsIOApiClient(apiKey);
 * List<Subscription> subscriptions = client.user().getSubscriptions();
 * subscriptions.get(0).getGroupId();
 * </pre>
 */
public class GroupsIOApiClient {

    /**
     * The default hostname to use when calling the API.
     */
    private static final String DEFAULT_HOSTNAME = "api.groups.io";

    /**
     * The default version tag to use for calls to the API.
     */
    private static final String DEFAULT_VERSION = "v1";

    /**
     * A singleton client instance to use for all API calls.
     */
    private static final OkHttpClient CLIENT = new OkHttpClient();

    /**
     * A mapper instance to use for all JSON interactions.
     */
    private static final ObjectMapper MAPPER = new ObjectMapper()
        .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    /**
     * The API key use for authorization.
     */
    private final String apiKey;

    /**
     * The generated API root host/path combination.
     */
    private final HttpUrl apiRoot;

    /**
     * The API token retrieved after login.
     */
    private String apiToken;
    
    /**
     * Common client initialisation. Provide your API key and email.
     * 
     * @param apiKey
     *            - TODO: Update with details once published.
     */
    public GroupsIOApiClient(@Nonnull String apiKey) {
        this(DEFAULT_HOSTNAME, DEFAULT_VERSION, apiKey);
    }
    
    /**
     * More in-depth constructor to override the defaults.
     *
     * @param apiKey
     *            - TODO: Update with details once published.
     * @param hostname
     *            - the base hostname (e.g. api.groups.io) to use
     * @param version
     *            - the API version (e.g. v1) to use
     */
    public GroupsIOApiClient(@Nonnull String apiKey, @Nonnull String hostname, @Nonnull String version) {
        Preconditions.checkNotNull(version);
        Preconditions.checkNotNull(hostname);
        this.apiKey = Preconditions.checkNotNull(apiKey);
        this.apiRoot = HttpUrl.parse("https://" + hostname + "/" + version);
        Preconditions.checkNotNull(this.apiRoot);
    }

    /**
     * Actions involving message archives.
     *
     * @return {@link ArchiveResource}
     */
    @Nonnull
    public ArchiveResource archive()
    {
        return new ArchiveResource(this);
    }


    /**
     * Actions involving a specific group.
     * 
     * @return {@link GroupResource}
     */
    @Nonnull
    public GroupResource group()
    {
        return new GroupResource(this);
    }

    /**
     * Actions involving the members of a group.
     *
     * @return {@link MemberResource}
     */
    @Nonnull
    public MemberResource member() {
        return new MemberResource(this);
    }

    /**
     * Actions involving the currently authenticated user.
     *
     * @return {@link UserResource}
     */
    @Nonnull
    public UserResource user() {
        return new UserResource(this);
    }

    /**
     * Makes a request to the groups.io API, based on the provided
     * request instance.
     *
     * @param request
     *      the request meta info to use for calls to the API.
     * @param type
     *      the type of the expected response body.
     * @return
     *      a parsed response body as a T instance.
     * @throws GroupsIOApiException
     *      on any errors dealing with data.
     * @throws IOException
     *      on any errors calling the API.
     */
    @Nonnull
    public <T> T call(GroupsIOApiRequest request, Class<T> type) throws GroupsIOApiException, IOException {
        return call(request, TypeUtils.generateType(typeFactory -> typeFactory.constructType(type)));
    }

    /**
     * Makes a request to the groups.io API, based on the provided
     * request instance.
     *
     * @param request
     *      the request meta info to use for calls to the API.
     * @param type
     *      the type of the expected response body.
     * @return
     *      a parsed response body as a T instance.
     * @throws GroupsIOApiException
     *      on any errors dealing with data.
     * @throws IOException
     *      on any errors calling the API.
     */
    @Nonnull
    public <T> T call(GroupsIOApiRequest request, TypeReference<T> type) throws GroupsIOApiException, IOException {
        return call(request, TypeUtils.generateType(typeFactory -> typeFactory.constructType(type)));
    }

    /**
     * Makes a request to the groups.io API, based on the provided
     * request instance.
     *
     * @param request
     *      the request meta info to use for calls to the API.
     * @param type
     *      the type of the expected response body.
     * @return
     *      a parsed response body as a T instance.
     * @throws GroupsIOApiException
     *      on any errors dealing with data.
     * @throws IOException
     *      on any errors calling the API.
     */
    @Nonnull
    public <T> T call(GroupsIOApiRequest request, JavaType type) throws GroupsIOApiException, IOException {
        HttpUrl.Builder endpointBuilder = this.apiRoot.newBuilder()
            .addPathSegment(request.path());

        for (Map.Entry<String, List<String>> param : request.params().entrySet()) {
            for (String value : param.getValue()) {
                endpointBuilder.addQueryParameter(param.getKey(), value);
            }
        }

        HttpUrl endpoint = endpointBuilder.build();

        Request.Builder requestBuilder = new Request.Builder()
            .url(endpoint)
            .method(request.method(), request.body().orElse(null));

        for (Map.Entry<String, String> header : request.headers().entrySet()) {
            requestBuilder.header(header.getKey(), header.getValue());
        }

        if (!request.headers().containsKey("Authorization")) {
            requestBuilder.header("Authorization", "Basic " + this.apiToken + ":");
        }

        Response response = CLIENT
            .newCall(requestBuilder.build())
            .execute();

        ResponseBody responseBody = response.body();
        Preconditions.checkNotNull(responseBody);

        byte[] bodyBytes = responseBody.bytes();
        if (response.code() != 200) {
            throw createErrorException(bodyBytes);
        }

        try {
            return MAPPER.readValue(bodyBytes, type);
        } catch (IOException e) {
            throw createErrorException(bodyBytes);
        }
    }

    /**
     * Returns the API key configured inside this client.
     *
     * @return the {@link String} API key.
     */
    @Nonnull
    public String getApiKey() {
        return this.apiKey;
    }

    /**
     * Returns the API token configured inside this client.
     *
     * @return the {@link String} API token.
     */
    @Nullable
    public String getApiToken() {
        return this.apiToken;
    }

    /**
     * @return the apiRoot
     */
    @Nonnull
    public HttpUrl getApiRoot() {
        return this.apiRoot;
    }

    /**
     * Carries out initial login call to the remote API.
     *
     * This is required in order to call any other endpoints.
     *
     * @param email
     *      the email to login with.
     * @param password
     *      the password to login with.
     * @throws GroupsIOApiException
     *      on any exceptions handling data values.
     * @throws IOException
     *      on any exceptions inside the HTTPS calls.
     */
    public void login(@Nonnull String email, @Nonnull String password) throws GroupsIOApiException, IOException {
        login(email, password, null);
    }

    /**
     * Carries out initial login call to the remote API.
     *
     * This is required in order to call any other endpoints.
     *
     * @param email
     *      the email to login with.
     * @param password
     *      the password to login with.
     * @param tfa
     *      the two-factor auth code to provide.
     * @throws GroupsIOApiException
     *      on any exceptions handling data values.
     * @throws IOException
     *      on any exceptions inside the HTTPS calls.
     */
    public void login(String email, String password, Integer tfa) throws GroupsIOApiException, IOException {
        GroupsIOApiRequest.Builder requestBuilder = GroupsIOApiRequest
            .builder("GET", "/login")
                .putParam("email", email)
                .putParam("password", password)
                .putHeaders("Authorization", "Basic " + this.apiKey + ":");

        if (tfa != null) {
            requestBuilder.putParam("twofactor", "" + tfa);
        }

        GroupsIOApiRequest request = requestBuilder.build();
        this.apiToken = call(request, Login.class).token();
    }

    /**
     * Paginates through a request, buffering all objects into
     * the defined type before returning.
     *
     * @param request
     *      the request meta info to use for calls to the API.
     * @param type
     *      the type of the expected response body.
     * @return
     *      a parsed response body as a collection of T instances.
     * @throws GroupsIOApiException
     *      on any errors dealing with data.
     * @throws IOException
     *      on any errors calling the API.
     */
    @Nonnull
    public <T> List<T> paginate(GroupsIOApiRequest request, Class<T> type) throws GroupsIOApiException, IOException {
        return paginate(request, TypeUtils.generateType(typeFactory -> typeFactory.constructType(type)));
    }

    /**
     * Paginates through a request, buffering all objects into
     * the defined type before returning.
     *
     * @param request
     *      the request meta info to use for calls to the API.
     * @param type
     *      the type of the expected response body.
     * @return
     *      a parsed response body as a collection of T instances.
     * @throws GroupsIOApiException
     *      on any errors dealing with data.
     * @throws IOException
     *      on any errors calling the API.
     */
    @Nonnull
    public <T> List<T> paginate(GroupsIOApiRequest request, TypeReference<T> type) throws GroupsIOApiException, IOException {
        return paginate(request, TypeUtils.generateType(typeFactory -> typeFactory.constructType(type)));
    }

    /**
     * Paginates through a request, buffering all objects into
     * the defined type before returning.
     *
     * @param request
     *      the request meta info to use for calls to the API.
     * @param type
     *      the type of the expected response body.
     * @return
     *      a parsed response body as a collection of T instances.
     * @throws GroupsIOApiException
     *      on any errors dealing with data.
     * @throws IOException
     *      on any errors calling the API.
     */
    @Nonnull
    public <T> List<T> paginate(GroupsIOApiRequest request, JavaType type) throws GroupsIOApiException, IOException {
        Page<T> page = this.call(request, type);
        List<T> results = new ArrayList<>();
        results.addAll(page.data());

        while (page.hasMore()) {
            request = new GroupsIOApiRequest.Builder()
                .from(request)
                    .putParam("page_token", "" + page.nextPageToken())
                .build();

            page = this.call(request, type);
            results.addAll(page.data());
        }

        return results;
    }

    /**
     * Given an error response, return an {@link Error} object.
     *
     * @param bytes
     *      from a previously completed API request.
     * @return
     *      an {@link Error} representing the APIs response.
     * @throws IOException
     *      on any errors reading the bytes as an error.
     */
    private GroupsIOApiException createErrorException(byte[] bytes) throws IOException {
        return new GroupsIOApiException(MAPPER.readValue(bytes, Error.class));
    }
}
