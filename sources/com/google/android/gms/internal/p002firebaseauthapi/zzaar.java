package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.firebase.auth.internal.zzaq;
import com.google.firebase.auth.zze;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaar  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzaar implements zzael<zzafu> {
    private final /* synthetic */ zzade zza;
    private final /* synthetic */ zzzk zzb;

    zzaar(zzzk zzzk, zzade zzade) {
        this.zza = zzade;
        this.zzb = zzzk;
    }

    public final void zza(String str) {
        this.zza.zza(zzaq.zza(str));
    }

    public final /* synthetic */ void zza(Object obj) {
        zzafu zzafu = (zzafu) obj;
        this.zzb.zza(new zzagl(zzafu.zzb(), zzafu.zza(), Long.valueOf(zzagn.zza(zzafu.zza())), "Bearer"), (String) null, (String) null, false, (zze) null, this.zza, this);
    }
}
