package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public final class zzbc {
    final String zza;
    final String zzb;
    final String zzc;
    final long zzd;
    final long zze;
    final zzbe zzf;

    /* access modifiers changed from: package-private */
    public final zzbc zza(zzhy zzhy, long j) {
        return new zzbc(zzhy, this.zzc, this.zza, this.zzb, this.zzd, j, this.zzf);
    }

    public final String toString() {
        String str = this.zza;
        String str2 = this.zzb;
        return "Event{appId='" + str + "', name='" + str2 + "', params=" + String.valueOf(this.zzf) + "}";
    }

    zzbc(zzhy zzhy, String str, String str2, String str3, long j, long j2, Bundle bundle) {
        zzbe zzbe;
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        this.zza = str2;
        this.zzb = str3;
        this.zzc = TextUtils.isEmpty(str) ? null : str;
        this.zzd = j;
        this.zze = j2;
        if (j2 != 0 && j2 > j) {
            zzhy.zzj().zzu().zza("Event created with reverse previous/current timestamps. appId", zzgo.zza(str2));
        }
        if (bundle == null || bundle.isEmpty()) {
            zzbe = new zzbe(new Bundle());
        } else {
            Bundle bundle2 = new Bundle(bundle);
            Iterator it = bundle2.keySet().iterator();
            while (it.hasNext()) {
                String str4 = (String) it.next();
                if (str4 == null) {
                    zzhy.zzj().zzg().zza("Param name can't be null");
                    it.remove();
                } else {
                    Object zzb2 = zzhy.zzt().zzb(str4, bundle2.get(str4));
                    if (zzb2 == null) {
                        zzhy.zzj().zzu().zza("Param value can't be null", zzhy.zzk().zzb(str4));
                        it.remove();
                    } else {
                        zzhy.zzt().zza(bundle2, str4, zzb2);
                    }
                }
            }
            zzbe = new zzbe(bundle2);
        }
        this.zzf = zzbe;
    }

    private zzbc(zzhy zzhy, String str, String str2, String str3, long j, long j2, zzbe zzbe) {
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        Preconditions.checkNotNull(zzbe);
        this.zza = str2;
        this.zzb = str3;
        this.zzc = TextUtils.isEmpty(str) ? null : str;
        this.zzd = j;
        this.zze = j2;
        if (j2 != 0 && j2 > j) {
            zzhy.zzj().zzu().zza("Event created with reverse previous/current timestamps. appId, name", zzgo.zza(str2), zzgo.zza(str3));
        }
        this.zzf = zzbe;
    }
}
