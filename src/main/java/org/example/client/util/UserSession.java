package org.example.client.util;

import org.example.common.dto.UserToken;

public class UserSession {
    public static UserToken userToken;

    private UserSession() {
    }

    public static void setUserToken(UserToken token) {
        userToken = token;
    }

    public static UserToken getUserToken() {
        return userToken;
    }

    public static void clearUserToken() {
        userToken = null;
    }

}
