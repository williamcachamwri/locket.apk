package com.google.android.gms.internal.p002firebaseauthapi;

import android.text.TextUtils;
import com.google.firebase.auth.zze;
import java.util.List;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzs  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzzs implements zzael<zzagb> {
    private final /* synthetic */ zzaem zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ Boolean zzd;
    private final /* synthetic */ zze zze;
    private final /* synthetic */ zzade zzf;
    private final /* synthetic */ zzagl zzg;

    zzzs(zzzk zzzk, zzaem zzaem, String str, String str2, Boolean bool, zze zze2, zzade zzade, zzagl zzagl) {
        this.zza = zzaem;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = bool;
        this.zze = zze2;
        this.zzf = zzade;
        this.zzg = zzagl;
    }

    public final void zza(String str) {
        this.zza.zza(str);
    }

    public final /* synthetic */ void zza(Object obj) {
        List<zzage> zza2 = ((zzagb) obj).zza();
        if (zza2 == null || zza2.isEmpty()) {
            this.zza.zza("No users.");
            return;
        }
        boolean z = false;
        zzage zzage = zza2.get(0);
        zzagu zzf2 = zzage.zzf();
        List<zzagr> zza3 = zzf2 != null ? zzf2.zza() : null;
        if (zza3 != null && !zza3.isEmpty()) {
            if (!TextUtils.isEmpty(this.zzb)) {
                int i = 0;
                while (true) {
                    if (i >= zza3.size()) {
                        break;
                    } else if (zza3.get(i).zzf().equals(this.zzb)) {
                        zza3.get(i).zza(this.zzc);
                        break;
                    } else {
                        i++;
                    }
                }
            } else {
                zza3.get(0).zza(this.zzc);
            }
        }
        Boolean bool = this.zzd;
        if (bool != null) {
            zzage.zza(bool.booleanValue());
        } else {
            if (zzage.zzb() - zzage.zza() < 1000) {
                z = true;
            }
            zzage.zza(z);
        }
        zzage.zza(this.zze);
        this.zzf.zza(this.zzg, zzage);
    }
}
