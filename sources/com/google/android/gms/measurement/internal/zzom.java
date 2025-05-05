package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzfy;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
final class zzom {
    private long zza;
    private zzfy.zzj zzb;
    private String zzc;
    private Map<String, String> zzd;
    private zznt zze;

    public final zzom zza(zzfy.zzj zzj) {
        this.zzb = zzj;
        return this;
    }

    public final zzom zza(long j) {
        this.zza = j;
        return this;
    }

    public final zzom zza(Map<String, String> map) {
        this.zzd = map;
        return this;
    }

    public final zzom zza(zznt zznt) {
        this.zze = zznt;
        return this;
    }

    public final zzom zza(String str) {
        this.zzc = str;
        return this;
    }

    public final zzoj zza() {
        return new zzoj(this.zza, this.zzb, this.zzc, this.zzd, this.zze);
    }

    zzom() {
    }
}
