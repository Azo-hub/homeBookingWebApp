package com.bookingWebAppApi.Model;

public class Authority {
    public static final String[] TRAVELLER_USER_AUTHORITIES = { "user:read" };
    public static final String[] OWNER_USER_AUTHORITIES = { "user:create", "user:read", "user:update" };
    public static final String[] ADMIN_AUTHORITIES = { "user:create", "user:read", "user:update", "user:delete" };
}
