package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.firebase.auth.internal.zzaq;
import com.google.firebase.auth.zze;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaao  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzaao implements zzael<zzafs> {
    private final /* synthetic */ zzade zza;
    private final /* synthetic */ zzaap zzb;

    zzaao(zzaap zzaap, zzade zzade) {
        this.zza = zzade;
        this.zzb = zzaap;
    }

    public final void zza(String str) {
        this.zza.zza(zzaq.zza(str));
    }

    public final /* synthetic */ void zza(Object obj) {
        zzafs zzafs = (zzafs) obj;
        this.zzb.zza.zza(new zzagl(zzafs.zzb(), zzafs.zza(), Long.valueOf(zzagn.zza(zzafs.zza())), "Bearer"), (String) null, (String) null, false, (zze) null, this.zza, this);
    }
}
