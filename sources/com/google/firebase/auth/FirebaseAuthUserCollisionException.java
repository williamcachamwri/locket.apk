package com.google.firebase.auth;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class FirebaseAuthUserCollisionException extends FirebaseAuthException {
    private AuthCredential zza;
    private String zzb;

    public final AuthCredential getUpdatedCredential() {
        return this.zza;
    }

    public final FirebaseAuthUserCollisionException zza(AuthCredential authCredential) {
        this.zza = authCredential;
        return this;
    }

    public final FirebaseAuthUserCollisionException zza(String str) {
        this.zzb = str;
        return this;
    }

    public final String getEmail() {
        return this.zzb;
    }

    public FirebaseAuthUserCollisionException(String str, String str2) {
        super(str, str2);
    }
}
