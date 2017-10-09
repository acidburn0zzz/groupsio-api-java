package com.github.lake54.groupsio.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import okhttp3.RequestBody;
import org.immutables.value.Value;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.immutables.value.Value.Style.ImplementationVisibility.PACKAGE;

/**
 * Request representation class to be used when invoking
 * API calls against the backend API.
 *
 * This class should encapsulate all requests that can be
 * currently made against the API. It acts as a very small
 * shim around OkHttp in order to hide the library details.
 */
@Value.Immutable(copy = false)
@Value.Style(visibility = PACKAGE)
@JsonSerialize(as = ImmutableGroupsIOApiRequest.class)
@JsonDeserialize(builder = GroupsIOApiRequest.Builder.class)
public abstract class GroupsIOApiRequest {

    /**
     * Returns the configured request method.
     *
     * @return a {@link String} HTTP method.
     */
    public abstract String method();

    /**
     * Returns the configured request path.
     *
     * @return a {@link String} request path.
     */
    public abstract String path();

    /**
     * Returns the configured request parameters.
     *
     * @return a {@link Map} of request params.
     */
    public abstract Map<String, List<String>> params();

    /**
     * Returns the configured request headers.
     *
     * @return a {@link Map} of request headers.
     */
    public abstract Map<String, String> headers();

    /**
     * Returns the configured request body.
     *
     * This is optional, as some requests do not
     * need a request body.
     *
     * @return an {@link Optional} body instance.
     */
    public abstract Optional<RequestBody> body();

    /**
     * Returns a builder from the provided method and path.
     *
     * @param method
     *      the HTTP method to use for the request.
     * @param path
     *      the API path to use for the request.
     * @return
     *      a new {@link Builder} instance.
     */
    public static Builder builder(@Nonnull String method, @Nonnull String path) {
        return new Builder().method(method).path(path);
    }

    /**
     * Public interface for an internal request builder to hide
     * the implementation classes, but expose all needed methods
     * for building requests.
     */
    public interface RequestBuilder {

        /**
         * Sets the body for the request.
         *
         * @param body
         *      the {@link RequestBody} instance.
         * @return
         *      this {@link RequestBuilder} instance.
         */
        RequestBuilder body(RequestBody body);

        /**
         * Sets the method for the request.
         *
         * @param method
         *      the HTTP method to use.
         * @return
         *      this {@link RequestBuilder} instance.
         */
        RequestBuilder method(String method);

        /**
         * Sets the path for the request.
         *
         * @param path
         *      the API path to request.
         * @return
         *      this {@link RequestBuilder} instance.
         */
        RequestBuilder path(String path);

        /**
         * Sets the headers for the request.
         *
         * @param headers
         *      the headers to use for the request.
         * @return
         *      this {@link RequestBuilder} instance.
         */
        RequestBuilder headers(Map<String, ? extends String> headers);

        /**
         * Adds a header to the request.
         *
         * @param key
         *      the header key field.
         * @param value
         *      the header value field.
         * @return
         *      this {@link RequestBuilder} instance.
         */
        RequestBuilder putHeaders(String key, String value);

        /**
         * Adds a collection of headers to the request.
         *
         * @param headers
         *      a {@link Map} of headers to add.
         * @return
         *      this {@link RequestBuilder} instance.
         */
        RequestBuilder putAllHeaders(Map<String, ? extends String> headers);

        /**
         * Sets the parameters for the request.
         *
         * @param params
         *      the params to use for the request.
         * @return
         *      this {@link RequestBuilder} instance.
         */
        RequestBuilder params(Map<String, ? extends List<String>> params);

        /**
         * Adds a parameter to the request.
         *
         * @param key
         *      the parameter key field.
         * @param value
         *      the parameter value field.
         * @return
         *      this {@link RequestBuilder} instance.
         */
        RequestBuilder putParam(String key, String value);

        /**
         * Adds a parameter to the request.
         *
         * @param key
         *      the parameter key field.
         * @param values
         *      the parameter value field.
         * @return
         *      this {@link RequestBuilder} instance.
         */
        RequestBuilder putParams(String key, List<String> values);

        /**
         * Adds a collection of parameters to the request.
         *
         * @param params
         *      a {@link Map} of parameters to add.
         * @return
         *      this {@link RequestBuilder} instance.
         */
        RequestBuilder putAllParams(Map<String, ? extends List<String>> params);

        /**
         * Constructs a new {@link GroupsIOApiRequest} from
         * this builder.
         *
         * @return
         *      this {@link RequestBuilder} instance.
         */
        GroupsIOApiRequest build();
    }

    /**
     * Overridden builder implementation to add any extra grace methods.
     *
     * This will also implement the {@link RequestBuilder} interface to
     * expose the builder methods to all calling classes.
     */
    public static class Builder extends ImmutableGroupsIOApiRequest.Builder implements RequestBuilder {

        /**
         * Adds a parameter to the request.
         *
         * @param key
         *      the parameter key field.
         * @param value
         *      the parameter value field.
         * @return
         *      this {@link Builder} instance.
         */
        @Override
        public Builder putParam(String key, String value) {
            return super.putParams(key, Collections.singletonList(value));
        }
    }
}
