package com.google.android.recaptcha.internal;

import java.util.HashMap;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzgh {
    private final zzgg zza;
    private final HashMap zzb;
    private final zzfy zzc;
    private final zzci zzd;

    public zzgh(zzfy zzfy, zzci zzci, zzbq zzbq) {
        this.zzc = zzfy;
        this.zzd = zzci;
        zzgg zzgg = new zzgg();
        this.zza = zzgg;
        HashMap hashMap = new HashMap();
        this.zzb = hashMap;
        zzgg.zzd(173, hashMap);
    }

    public final zzgg zza() {
        return this.zza;
    }

    public final void zzb() {
        this.zza.zzc();
        this.zza.zzd(173, this.zzb);
    }

    public final zzci zzc() {
        return this.zzd;
    }

    public final zzfy zzd() {
        return this.zzc;
    }

    public final void zze(int i, Object obj) {
        this.zzb.put(Integer.valueOf(i - 2), obj);
    }
}
