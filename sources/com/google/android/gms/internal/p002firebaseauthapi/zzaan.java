package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.firebase.auth.internal.zzaq;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaan  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzaan implements zzael<zzaht> {
    private final /* synthetic */ zzade zza;
    private final /* synthetic */ zzzk zzb;

    zzaan(zzzk zzzk, zzade zzade) {
        this.zza = zzade;
        this.zzb = zzzk;
    }

    public final void zza(String str) {
        this.zza.zza(zzaq.zza(str));
    }

    public final /* synthetic */ void zza(Object obj) {
        zzaht zzaht = (zzaht) obj;
        if (!zzaht.zzl()) {
            zzzk.zza(this.zzb, zzaht, this.zza, (zzaem) this);
        } else {
            this.zza.zza(new zzzl(zzaht.zzf(), zzaht.zzk(), zzaht.zzb()));
        }
    }
}
