package com.github.lake54.groupsio.api.domain.enums.error;

public enum ErrorType {
    unknown,
    unauthorized,
    server,
    rate_limit,
    invalid_value,
    inadequate_permissions,
    expired,
    bad_request,
    authentication;
}