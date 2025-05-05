package com.google.android.gms.internal.fido;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-fido@@20.1.0 */
public final class zzdm extends zzdr {
    private final long zza;

    zzdm(long j) {
        this.zza = j;
    }

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        zzdr zzdr = (zzdr) obj;
        if (zza() != zzdr.zza()) {
            return zza() - zzdr.zza();
        }
        int i = (Math.abs(this.zza) > Math.abs(((zzdm) zzdr).zza) ? 1 : (Math.abs(this.zza) == Math.abs(((zzdm) zzdr).zza) ? 0 : -1));
        if (i < 0) {
            return -1;
        }
        return i > 0 ? 1 : 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.zza == ((zzdm) obj).zza;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(zza()), Long.valueOf(this.zza)});
    }

    public final String toString() {
        return Long.toString(this.zza);
    }

    /* access modifiers changed from: protected */
    public final int zza() {
        return zzd(this.zza >= 0 ? (byte) 0 : 32);
    }

    public final long zzc() {
        return this.zza;
    }
}
