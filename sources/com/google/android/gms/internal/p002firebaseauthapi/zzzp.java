package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.firebase.auth.internal.zzaq;
import com.google.firebase.auth.zze;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzp  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzzp implements zzael<zzahx> {
    private final /* synthetic */ zzade zza;
    private final /* synthetic */ zzzk zzb;

    zzzp(zzzk zzzk, zzade zzade) {
        this.zza = zzade;
        this.zzb = zzzk;
    }

    public final void zza(String str) {
        this.zza.zza(zzaq.zza(str));
    }

    public final /* synthetic */ void zza(Object obj) {
        zzahx zzahx = (zzahx) obj;
        if (zzahx.zzf()) {
            this.zza.zza(new zzzl(zzahx.zzc(), zzahx.zze(), (zze) null));
            return;
        }
        this.zzb.zza(new zzagl(zzahx.zzd(), zzahx.zzb(), Long.valueOf(zzahx.zza()), "Bearer"), (String) null, (String) null, false, (zze) null, this.zza, this);
    }
}
