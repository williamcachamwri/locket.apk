package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.firebase.auth.internal.zzaq;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaag  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzaag implements zzael<zzagl> {
    private final /* synthetic */ zzade zza;
    private final /* synthetic */ zzzk zzb;

    zzaag(zzzk zzzk, zzade zzade) {
        this.zza = zzade;
        this.zzb = zzzk;
    }

    public final void zza(String str) {
        this.zza.zza(zzaq.zza(str));
    }

    public final /* synthetic */ void zza(Object obj) {
        zzagl zzagl = (zzagl) obj;
        zzahb zzahb = new zzahb();
        zzahb.zzd(zzagl.zzc()).zzc((String) null).zzf((String) null);
        zzzk.zza(this.zzb, this.zza, zzagl, zzahb, (zzaem) this);
    }
}
