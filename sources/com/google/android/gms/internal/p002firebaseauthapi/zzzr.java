package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.firebase.auth.internal.zzaq;
import com.google.firebase.auth.zze;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzr  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzzr implements zzael<zzafq> {
    private final /* synthetic */ zzade zza;
    private final /* synthetic */ zzzk zzb;

    zzzr(zzzk zzzk, zzade zzade) {
        this.zza = zzade;
        this.zzb = zzzk;
    }

    public final void zza(String str) {
        this.zza.zza(zzaq.zza(str));
    }

    public final /* synthetic */ void zza(Object obj) {
        zzafq zzafq = (zzafq) obj;
        if (zzafq.zzf()) {
            this.zza.zza(new zzzl(zzafq.zzc(), zzafq.zze(), (zze) null));
            return;
        }
        this.zzb.zza(new zzagl(zzafq.zzd(), zzafq.zzb(), Long.valueOf(zzafq.zza()), "Bearer"), (String) null, (String) null, Boolean.valueOf(zzafq.zzg()), (zze) null, this.zza, this);
    }
}
