package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.IOException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzcf  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzcf {
    public static void zza(zzbt zzbt, zzce zzce, zzcn zzcn) throws IOException {
        if (zzcn != null) {
            zzce.zza(zzbt.zzb());
            return;
        }
        throw new NullPointerException("SecretKeyAccess cannot be null");
    }
}
