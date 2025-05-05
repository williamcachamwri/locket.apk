package com.google.android.gms.internal.pal;

import java.util.HashMap;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzdc extends zzbi {
    public long zza = -1;
    public long zzb = -1;

    public zzdc() {
    }

    /* access modifiers changed from: protected */
    public final HashMap zzb() {
        HashMap hashMap = new HashMap();
        hashMap.put(0, Long.valueOf(this.zza));
        hashMap.put(1, Long.valueOf(this.zzb));
        return hashMap;
    }

    public zzdc(String str) {
        HashMap zza2 = zza(str);
        if (zza2 != null) {
            this.zza = ((Long) zza2.get(0)).longValue();
            this.zzb = ((Long) zza2.get(1)).longValue();
        }
    }
}
