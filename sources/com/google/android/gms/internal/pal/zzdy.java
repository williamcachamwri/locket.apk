package com.google.android.gms.internal.pal;

import java.util.HashMap;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzdy extends zzbi {
    public Long zza;
    public Long zzb;
    public Long zzc;
    public Long zzd;
    public Long zze;

    public zzdy() {
    }

    /* access modifiers changed from: protected */
    public final HashMap zzb() {
        HashMap hashMap = new HashMap();
        hashMap.put(0, this.zza);
        hashMap.put(1, this.zzb);
        hashMap.put(2, this.zzc);
        hashMap.put(3, this.zzd);
        hashMap.put(4, this.zze);
        return hashMap;
    }

    public zzdy(String str) {
        HashMap zza2 = zza(str);
        if (zza2 != null) {
            this.zza = (Long) zza2.get(0);
            this.zzb = (Long) zza2.get(1);
            this.zzc = (Long) zza2.get(2);
            this.zzd = (Long) zza2.get(3);
            this.zze = (Long) zza2.get(4);
        }
    }
}
