package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.firebase.auth.MultiFactorInfo;
import com.google.firebase.auth.internal.zzbi;
import com.google.firebase.auth.zze;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzl  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzzl {
    private String zza;
    private List<zzags> zzb;
    private zze zzc;

    public final zze zza() {
        return this.zzc;
    }

    public final String zzb() {
        return this.zza;
    }

    public final List<MultiFactorInfo> zzc() {
        return zzbi.zza(this.zzb);
    }

    public zzzl(String str, List<zzags> list, zze zze) {
        this.zza = str;
        this.zzb = list;
        this.zzc = zze;
    }
}
