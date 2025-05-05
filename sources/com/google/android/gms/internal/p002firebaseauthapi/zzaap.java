package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.firebase.auth.internal.zzaq;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaap  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzaap implements zzael<zzagl> {
    final /* synthetic */ zzzk zza;
    private final /* synthetic */ zzafp zzb;
    private final /* synthetic */ zzade zzc;

    zzaap(zzzk zzzk, zzafp zzafp, zzade zzade) {
        this.zzb = zzafp;
        this.zzc = zzade;
        this.zza = zzzk;
    }

    public final void zza(String str) {
        this.zzc.zza(zzaq.zza(str));
    }

    public final /* synthetic */ void zza(Object obj) {
        this.zzb.zza(((zzagl) obj).zzc());
        this.zza.zza.zza(this.zzb, (zzael<zzafs>) new zzaao(this, this.zzc));
    }
}
