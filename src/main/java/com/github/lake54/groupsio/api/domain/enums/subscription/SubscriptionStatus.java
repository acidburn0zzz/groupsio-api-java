package com.github.lake54.groupsio.api.domain.enums.subscription;

public enum SubscriptionStatus {
    sub_status_normal,
    sub_status_pending,
    sub_status_banned;

    public Boolean canBan() {
        return this != sub_status_banned;
    }
}