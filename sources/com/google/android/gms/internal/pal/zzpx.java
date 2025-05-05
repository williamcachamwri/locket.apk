package com.google.android.gms.internal.pal;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzpx {
    private final Class zza;
    private final zzyv zzb;

    /* synthetic */ zzpx(Class cls, zzyv zzyv, zzpw zzpw) {
        this.zza = cls;
        this.zzb = zzyv;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzpx)) {
            return false;
        }
        zzpx zzpx = (zzpx) obj;
        if (!zzpx.zza.equals(this.zza) || !zzpx.zzb.equals(this.zzb)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza, this.zzb});
    }

    public final String toString() {
        String simpleName = this.zza.getSimpleName();
        String valueOf = String.valueOf(this.zzb);
        return simpleName + ", object identifier: " + valueOf;
    }
}
