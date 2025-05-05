package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.firebase.auth.internal.zzaq;
import com.google.firebase.auth.zze;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzw  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzzw implements zzael<zzahv> {
    private final /* synthetic */ zzade zza;
    private final /* synthetic */ zzzk zzb;

    zzzw(zzzk zzzk, zzade zzade) {
        this.zza = zzade;
        this.zzb = zzzk;
    }

    public final void zza(String str) {
        this.zza.zza(zzaq.zza(str));
    }

    public final /* synthetic */ void zza(Object obj) {
        zzahv zzahv = (zzahv) obj;
        this.zzb.zza(new zzagl(zzahv.zzc(), zzahv.zzb(), Long.valueOf(zzahv.zza()), "Bearer"), (String) null, (String) null, Boolean.valueOf(zzahv.zzd()), (zze) null, this.zza, this);
    }
}
