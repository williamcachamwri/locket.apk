package com.google.android.gms.internal.pal;

import java.util.HashMap;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzdp extends zzbi {
    public Long zza;
    public Long zzb;

    public zzdp() {
    }

    /* access modifiers changed from: protected */
    public final HashMap zzb() {
        HashMap hashMap = new HashMap();
        hashMap.put(0, this.zza);
        hashMap.put(1, this.zzb);
        return hashMap;
    }

    public zzdp(String str) {
        HashMap zza2 = zza(str);
        if (zza2 != null) {
            this.zza = (Long) zza2.get(0);
            this.zzb = (Long) zza2.get(1);
        }
    }
}
