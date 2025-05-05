package com.google.firebase.iid;

/* compiled from: com.google.firebase:firebase-iid@@21.1.0 */
final class InstanceIdResultImpl implements InstanceIdResult {
    private final String id;
    private final String token;

    InstanceIdResultImpl(String str, String str2) {
        this.id = str;
        this.token = str2;
    }

    public String getId() {
        return this.id;
    }

    public String getToken() {
        return this.token;
    }
}
