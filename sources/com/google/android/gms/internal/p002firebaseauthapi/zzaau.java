package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaau  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzaau implements zzael<zzagb> {
    private final /* synthetic */ zzael zza;
    private final /* synthetic */ zzade zzb;
    private final /* synthetic */ zzagl zzc;

    zzaau(zzaav zzaav, zzael zzael, zzade zzade, zzagl zzagl) {
        this.zza = zzael;
        this.zzb = zzade;
        this.zzc = zzagl;
    }

    public final void zza(String str) {
        this.zza.zza(str);
    }

    public final /* synthetic */ void zza(Object obj) {
        List<zzage> zza2 = ((zzagb) obj).zza();
        if (zza2 == null || zza2.isEmpty()) {
            this.zza.zza("No users");
        } else {
            this.zzb.zza(this.zzc, zza2.get(0));
        }
    }
}
