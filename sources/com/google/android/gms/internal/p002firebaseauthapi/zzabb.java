package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.firebase.auth.internal.zzaq;
import com.google.firebase.auth.zze;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzabb  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzabb implements zzael<zzahf> {
    private final /* synthetic */ zzade zza;
    private final /* synthetic */ zzzk zzb;

    zzabb(zzzk zzzk, zzade zzade) {
        this.zza = zzade;
        this.zzb = zzzk;
    }

    public final void zza(String str) {
        this.zza.zza(zzaq.zza(str));
    }

    public final /* synthetic */ void zza(Object obj) {
        zzahf zzahf = (zzahf) obj;
        this.zzb.zza(new zzagl(zzahf.zzc(), zzahf.zzb(), Long.valueOf(zzahf.zza()), "Bearer"), (String) null, (String) null, true, (zze) null, this.zza, this);
    }
}
