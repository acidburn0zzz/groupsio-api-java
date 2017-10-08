package com.github.lake54.groupsio.api.domain.enums.user;

public enum UserStatus {
    user_status_notconfirmed,
    user_status_confirmed,
    user_status_inactive,
    user_status_bouncing,
    user_status_bounced;

    public Boolean canSendBounceProbe() {
        return this == user_status_bounced || this == user_status_bouncing;
    }

    public Boolean canSendConfirmationEmail()
    {
        return this == user_status_notconfirmed;
    }
}