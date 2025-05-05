package com.google.firebase.functions;

class HttpsCallableContext {
    private final String appCheckToken;
    private final String authToken;
    private final String instanceIdToken;

    HttpsCallableContext(String str, String str2, String str3) {
        this.authToken = str;
        this.instanceIdToken = str2;
        this.appCheckToken = str3;
    }

    public String getAuthToken() {
        return this.authToken;
    }

    public String getInstanceIdToken() {
        return this.instanceIdToken;
    }

    public String getAppCheckToken() {
        return this.appCheckToken;
    }
}
