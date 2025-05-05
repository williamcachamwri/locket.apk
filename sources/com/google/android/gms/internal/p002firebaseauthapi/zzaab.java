package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.firebase.auth.internal.zzaq;
import com.google.firebase.auth.zze;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaab  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzaab implements zzael<zzahz> {
    private final /* synthetic */ zzade zza;
    private final /* synthetic */ zzzk zzb;

    zzaab(zzzk zzzk, zzade zzade) {
        this.zza = zzade;
        this.zzb = zzzk;
    }

    public final void zza(String str) {
        this.zza.zza(zzaq.zza(str));
    }

    public final /* synthetic */ void zza(Object obj) {
        zzahz zzahz = (zzahz) obj;
        this.zzb.zza(new zzagl(zzahz.zzd(), zzahz.zzb(), Long.valueOf(zzahz.zza()), "Bearer"), (String) null, (String) null, Boolean.valueOf(zzahz.zzf()), (zze) null, this.zza, this);
    }
}
