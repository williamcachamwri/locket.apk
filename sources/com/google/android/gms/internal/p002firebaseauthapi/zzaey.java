package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.tasks.OnFailureListener;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaey  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzaey implements OnFailureListener {
    zzaey(zzaew zzaew) {
    }

    public final void onFailure(Exception exc) {
        zzaew.zza.e("SmsRetrieverClient failed to start: " + exc.getMessage(), new Object[0]);
    }
}
