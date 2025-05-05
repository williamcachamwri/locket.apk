package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.firebase.auth.internal.zzaq;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaai  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzaai implements zzael<zzagb> {
    private final /* synthetic */ zzael zza;
    private final /* synthetic */ zzagl zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ zzade zzd;
    private final /* synthetic */ zzaaj zze;

    zzaai(zzaaj zzaaj, zzael zzael, zzagl zzagl, String str, zzade zzade) {
        this.zza = zzael;
        this.zzb = zzagl;
        this.zzc = str;
        this.zzd = zzade;
        this.zze = zzaaj;
    }

    public final void zza(String str) {
        this.zzd.zza(zzaq.zza(str));
    }

    public final /* synthetic */ void zza(Object obj) {
        List<zzage> zza2 = ((zzagb) obj).zza();
        if (zza2 == null || zza2.isEmpty()) {
            this.zza.zza("No users.");
            return;
        }
        zzahb zzahb = new zzahb();
        zzahb.zzd(this.zzb.zzc()).zza(this.zzc);
        zzzk.zza(this.zze.zza, this.zzd, this.zzb, zza2.get(0), zzahb, (zzaem) this.zza);
    }
}
