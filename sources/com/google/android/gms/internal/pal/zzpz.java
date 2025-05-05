package com.google.android.gms.internal.pal;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzpz {
    private final Class zza;
    private final Class zzb;

    /* synthetic */ zzpz(Class cls, Class cls2, zzpy zzpy) {
        this.zza = cls;
        this.zzb = cls2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzpz)) {
            return false;
        }
        zzpz zzpz = (zzpz) obj;
        if (!zzpz.zza.equals(this.zza) || !zzpz.zzb.equals(this.zzb)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza, this.zzb});
    }

    public final String toString() {
        String simpleName = this.zza.getSimpleName();
        String simpleName2 = this.zzb.getSimpleName();
        return simpleName + " with serialization type: " + simpleName2;
    }
}
