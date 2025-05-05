package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzq  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzzq implements zzael<zzagb> {
    private final /* synthetic */ zzaem zza;
    private final /* synthetic */ zzade zzb;
    private final /* synthetic */ zzagl zzc;
    private final /* synthetic */ zzahb zzd;
    private final /* synthetic */ zzzk zze;

    zzzq(zzzk zzzk, zzaem zzaem, zzade zzade, zzagl zzagl, zzahb zzahb) {
        this.zza = zzaem;
        this.zzb = zzade;
        this.zzc = zzagl;
        this.zzd = zzahb;
        this.zze = zzzk;
    }

    public final void zza(String str) {
        this.zza.zza(str);
    }

    public final /* synthetic */ void zza(Object obj) {
        List<zzage> zza2 = ((zzagb) obj).zza();
        if (zza2 == null || zza2.isEmpty()) {
            this.zza.zza("No users");
        } else {
            zzzk.zza(this.zze, this.zzb, this.zzc, zza2.get(0), this.zzd, this.zza);
        }
    }
}
