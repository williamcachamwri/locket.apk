package com.google.android.gms.internal.fido;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-fido@@20.1.0 */
public final class zzdj extends zzdr {
    private final boolean zza;

    zzdj(boolean z) {
        this.zza = z;
    }

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        zzdr zzdr = (zzdr) obj;
        if (zzd((byte) -32) != zzdr.zza()) {
            return zzd((byte) -32) - zzdr.zza();
        }
        zzdj zzdj = (zzdj) zzdr;
        int i = 20;
        int i2 = true != this.zza ? 20 : 21;
        if (true == zzdj.zza) {
            i = 21;
        }
        return i2 - i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.zza == ((zzdj) obj).zza;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(zzd((byte) -32)), Boolean.valueOf(this.zza)});
    }

    public final String toString() {
        return Boolean.toString(this.zza);
    }

    /* access modifiers changed from: protected */
    public final int zza() {
        return zzd((byte) -32);
    }
}
