package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.firebase.analytics.FirebaseAnalytics;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzh  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public abstract class zzh {
    public int zza(CharSequence charSequence, int i) {
        int length = charSequence.length();
        zzy.zza(i, length, FirebaseAnalytics.Param.INDEX);
        while (i < length) {
            if (zza(charSequence.charAt(i))) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public abstract boolean zza(char c);

    protected zzh() {
    }
}
