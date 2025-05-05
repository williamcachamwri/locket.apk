package com.google.android.gms.internal.pal;

import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public abstract class zzou {
    private final zzyv zza;
    private final Class zzb;

    /* synthetic */ zzou(zzyv zzyv, Class cls, zzot zzot) {
        this.zza = zzyv;
        this.zzb = cls;
    }

    public static zzou zzb(zzos zzos, zzyv zzyv, Class cls) {
        return new zzor(zzyv, cls, zzos);
    }

    public abstract zzka zza(zzpu zzpu, @Nullable zzlg zzlg) throws GeneralSecurityException;

    public final zzyv zzc() {
        return this.zza;
    }

    public final Class zzd() {
        return this.zzb;
    }
}
