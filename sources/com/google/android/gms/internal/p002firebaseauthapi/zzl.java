package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.firebase.analytics.FirebaseAnalytics;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzl  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzl extends zzm {
    static final zzh zza = new zzl();

    public final int zza(CharSequence charSequence, int i) {
        zzy.zza(i, charSequence.length(), FirebaseAnalytics.Param.INDEX);
        return -1;
    }

    public final boolean zza(char c) {
        return false;
    }

    private zzl() {
        super("CharMatcher.none()");
    }
}
