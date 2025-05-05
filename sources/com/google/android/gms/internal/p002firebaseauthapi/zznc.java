package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zznc  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zznc extends zzbp {
    private final zzpc zza;

    public final zzpc zza(@Nullable zzcn zzcn) throws GeneralSecurityException {
        zza(this.zza, zzcn);
        return this.zza;
    }

    @Nullable
    public final Integer zza() {
        return this.zza.zze();
    }

    public zznc(zzpc zzpc, @Nullable zzcn zzcn) throws GeneralSecurityException {
        zza(zzpc, zzcn);
        this.zza = zzpc;
    }

    private static void zza(zzpc zzpc, @Nullable zzcn zzcn) throws GeneralSecurityException {
        int i = zznf.zza[zzpc.zza().ordinal()];
        if (i == 1 || i == 2) {
            zzcn.zza(zzcn);
        }
    }
}
