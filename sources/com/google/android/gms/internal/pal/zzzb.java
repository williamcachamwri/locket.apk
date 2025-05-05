package com.google.android.gms.internal.pal;

import java.util.Set;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzzb extends zzyy {
    private final zzzr zza = new zzzr();

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzzb) {
            return ((zzzb) obj).zza.equals(this.zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final zzyx zzb(String str) {
        return (zzyx) this.zza.get("key");
    }

    public final zzyy zzc(String str) {
        return (zzyy) this.zza.get(str);
    }

    public final zzzb zze(String str) {
        return (zzzb) this.zza.get("keyData");
    }

    public final Set zzg() {
        return this.zza.entrySet();
    }

    public final void zzh(String str, zzyy zzyy) {
        this.zza.put(str, zzyy);
    }

    public final boolean zzi(String str) {
        return this.zza.containsKey(str);
    }
}
