package com.google.android.gms.internal.p002firebaseauthapi;

import javax.annotation.CheckForNull;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzt  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzt {
    private static final zzu zza = new zzw();

    static zzn zza(String str) {
        zzy.zza(str);
        return zza.zza(str);
    }

    static String zzc(@CheckForNull String str) {
        return str == null ? "" : str;
    }

    @CheckForNull
    static String zzb(@CheckForNull String str) {
        if (zzd(str)) {
            return null;
        }
        return str;
    }

    static boolean zzd(@CheckForNull String str) {
        return str == null || str.isEmpty();
    }
}
