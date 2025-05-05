package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.firebase.auth.internal.zzaq;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabc  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzabc implements zzael<zzagl> {
    private final /* synthetic */ String zza;
    private final /* synthetic */ zzade zzb;
    private final /* synthetic */ zzzk zzc;

    zzabc(zzzk zzzk, String str, zzade zzade) {
        this.zza = str;
        this.zzb = zzade;
        this.zzc = zzzk;
    }

    public final void zza(String str) {
        this.zzb.zza(zzaq.zza(str));
    }

    public final /* synthetic */ void zza(Object obj) {
        zzagl zzagl = (zzagl) obj;
        zzahb zzahb = new zzahb();
        zzahb.zzd(zzagl.zzc()).zzc(this.zza);
        zzzk.zza(this.zzc, this.zzb, zzagl, zzahb, (zzaem) this);
    }
}
