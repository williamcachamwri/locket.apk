package com.google.firebase.auth;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public abstract class ActionCodeEmailInfo extends ActionCodeInfo {
    public String getEmail() {
        return super.getEmail();
    }

    public abstract String getPreviousEmail();
}
