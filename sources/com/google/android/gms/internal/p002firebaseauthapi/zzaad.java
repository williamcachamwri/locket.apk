package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.firebase.auth.zze;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaad  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzaad implements zzael<zzahf> {
    private final /* synthetic */ zzade zza;
    private final /* synthetic */ zzaem zzb;
    private final /* synthetic */ zzzk zzc;

    zzaad(zzzk zzzk, zzade zzade, zzaem zzaem) {
        this.zza = zzade;
        this.zzb = zzaem;
        this.zzc = zzzk;
    }

    public final void zza(String str) {
        this.zzb.zza(str);
    }

    public final /* synthetic */ void zza(Object obj) {
        zzahf zzahf = (zzahf) obj;
        this.zzc.zza(new zzagl(zzahf.zzc(), zzahf.zzb(), Long.valueOf(zzahf.zza()), "Bearer"), (String) null, "password", false, (zze) null, this.zza, this);
    }
}
