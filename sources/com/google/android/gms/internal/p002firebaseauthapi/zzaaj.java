package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.firebase.auth.internal.zzaq;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaaj  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzaaj implements zzael<zzagl> {
    final /* synthetic */ zzzk zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ zzade zzc;

    zzaaj(zzzk zzzk, String str, zzade zzade) {
        this.zzb = str;
        this.zzc = zzade;
        this.zza = zzzk;
    }

    public final void zza(String str) {
        this.zzc.zza(zzaq.zza(str));
    }

    public final /* synthetic */ void zza(Object obj) {
        zzagl zzagl = (zzagl) obj;
        this.zza.zza.zza(new zzagc(zzagl.zzc()), (zzael<zzagb>) new zzaai(this, this, zzagl, this.zzb, this.zzc));
    }
}
