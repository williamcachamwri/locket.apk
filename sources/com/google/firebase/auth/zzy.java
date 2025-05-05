package com.google.firebase.auth;

import com.google.firebase.auth.FirebaseAuth;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzy implements Runnable {
    private final /* synthetic */ FirebaseAuth zza;

    zzy(FirebaseAuth firebaseAuth) {
        this.zza = firebaseAuth;
    }

    public final void run() {
        for (FirebaseAuth.AuthStateListener onAuthStateChanged : this.zza.zzd) {
            onAuthStateChanged.onAuthStateChanged(this.zza);
        }
    }
}
