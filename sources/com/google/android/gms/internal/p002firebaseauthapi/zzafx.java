package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzafx  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public enum zzafx {
    REFRESH_TOKEN("refresh_token"),
    AUTHORIZATION_CODE("authorization_code");
    
    private final String zzd;

    public final String toString() {
        return this.zzd;
    }

    private zzafx(String str) {
        this.zzd = str;
    }
}
