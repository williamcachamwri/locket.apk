package com.google.firebase.auth.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GetTokenResult;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzar implements Runnable {
    final /* synthetic */ zzas zza;
    private final String zzb;

    zzar(zzas zzas, String str) {
        this.zza = zzas;
        this.zzb = Preconditions.checkNotEmpty(str);
    }

    public final void run() {
        FirebaseAuth instance = FirebaseAuth.getInstance(FirebaseApp.getInstance(this.zzb));
        if (instance.getCurrentUser() != null) {
            Task<GetTokenResult> accessToken = instance.getAccessToken(true);
            zzas.zzc.v("Token refreshing started", new Object[0]);
            accessToken.addOnFailureListener(new zzau(this));
        }
    }
}
