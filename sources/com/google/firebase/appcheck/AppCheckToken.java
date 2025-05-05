package com.google.firebase.appcheck;

public abstract class AppCheckToken {
    public abstract long getExpireTimeMillis();

    public abstract String getToken();
}
